package com.shoponline.notification.repository;

import com.shoponline.notification.dto.PaymentConfirmation;
import com.shoponline.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
