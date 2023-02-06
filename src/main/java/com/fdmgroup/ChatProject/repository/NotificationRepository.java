package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.ChatProject.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
