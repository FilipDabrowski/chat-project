package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
