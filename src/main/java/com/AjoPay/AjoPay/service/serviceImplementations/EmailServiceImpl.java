package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.EmailDetails;
import com.AjoPay.AjoPay.service.serviceInterface.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender; // this is used to send an email ( this is the pipeline that send the mail)
    @Value("${spring.mail.username}") // this is used to access the username at application.properties files which is the mail that will send informations to users
    private String sender;
    @Override
    public String sendSimpleMail(EmailDetails emailDetails) {
        try {
            System.out.println("sending mail");
            SimpleMailMessage mailMessage = new SimpleMailMessage();  // this is an object which helps to send mails from where is coming to where its going .
            mailMessage.setFrom(sender); // this is the person sending the mail
            mailMessage.setTo(emailDetails.getRecipient()); // this is the app user receiving the mail
            mailMessage.setText(emailDetails.getMessageBody()); // this is the information I am passing to the app user
            mailMessage.setSubject(emailDetails.getSubject()); // this is the subject matters of the mail

            javaMailSender.send(mailMessage); // this is the pipeline that send the mail
            System.out.println(" mail sent ");
            return "mail sent successfully";

        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // this help to create MimeMessageHelper object
        MimeMessageHelper mimeMessageHelper; // MimeMessageHelper help to create attachment features

        try {
            System.out.println(" sending mail");
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setText(emailDetails.getMessageBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment())); // sending file with attachment
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            System.out.println("mail sent ");
            return "Mail sent succesfully ";
        } catch (MessagingException e) {

            return "Error while sending mail";
        }

    }
}
