package com.ftofstudios.wentros.manager;

import org.springframework.stereotype.Component;

import com.ftofstudios.wentros.vo.UserVO;

@Component
public class UserManager {
	
	
	public UserVO getUserForAuthentication(String userName) {
		
		return new UserVO();
	}

}
