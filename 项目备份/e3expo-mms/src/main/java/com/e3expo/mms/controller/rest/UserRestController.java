package com.e3expo.mms.controller.rest;


import com.e3expo.mms.bean.service.UserService;
import com.e3expo.mms.controller.ParentController;
import com.e3expo.mms.controller.message.MmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRestController  extends ParentController {

	private final UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/update/isResigned")
	public MmsMessage updateIsResignedByUserID(@RequestParam("userID")int userID,
										   	   @RequestParam("userIsResigned") byte isResigned) {
		userService.updateIsResignedByUserID(userID, isResigned);
		return new MmsMessage(200, "success");
	}
	
	@GetMapping("/checkPhoneNumber/{phoneNumber}")
	public MmsMessage checkUserByName(@PathVariable String phoneNumber) throws Exception {
		if (userService.checkExistsUserByPhoneNumber(phoneNumber)) {
			// 存在
			return new MmsMessage(500,"phone number has been used");
		} else {
			return new MmsMessage(200,"phone number can be used");
		}
	}
	
	/*
	@PostMapping("/submit-login")
	public HouseMessage login(
			@RequestParam("username") String username, 
			@RequestParam("password") String password) throws Exception {
		
		if ( username.trim().length() <= 0 ) {
			throw new AccountIsNullException();
		}
		
		if ( password.trim().length() <= 0 ) {
			throw new PasswordCannotBeNullException();
		}
		
		userService.login(username, password);
		
		return new HouseMessage(HouseMessageCode.MSG_CODE_LOGIN_SUCCESS, 
				HouseMessageMessage.MSG_STRING_LOGIN_SUCCESS
				);
		
	}
	
	
	
	@PostMapping("/submit-change-password")
	public HouseMessage changePassword(
			@RequestParam String oldPassword,
			@RequestParam String newPassword,
			@RequestParam String verifyPassword
			) throws Exception {
		
		if( !userService.checkPassword(oldPassword) ) {
			throw new IncorrectCredentialsException();
		}
		
		//如果密码验证通过了。
		if ( newPassword.trim().length() <= 0 || verifyPassword.length() <= 0 ) {
			throw new PasswordIsNotSameException();
		}
		
		if ( !newPassword.trim().equals(verifyPassword.trim()) ) {
			throw new PasswordIsNotSameException();
		}
		
		
		userService.updatePassword(newPassword);
		
		return new HouseMessage(HouseMessageCode.MSG_CODE_UPDATE_PASSWORD_SUCCESS, 
					HouseMessageMessage.MSG_STRING_UPDATE_PASSWORD_SUCCESS
				);

		
		
	}
	
	*/

}
