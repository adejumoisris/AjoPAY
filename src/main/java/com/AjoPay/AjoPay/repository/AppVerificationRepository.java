package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.entity.AppVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVerificationRepository extends JpaRepository<AppVerificationEntity, Long> {
}
