package com.fdmgroup.ChatProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.repository.NotificationRepository;
import com.fdmgroup.ChatProject.service.interfaces.INotificationService;
@Service
public class NotificationSerbvice implements INotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
}
