package com.fdmgroup.ChatProject.service.interfaces;

import com.fdmgroup.ChatProject.model.ChatUser;

public interface IChatUserService {

	ChatUser findById(long userID);

}
