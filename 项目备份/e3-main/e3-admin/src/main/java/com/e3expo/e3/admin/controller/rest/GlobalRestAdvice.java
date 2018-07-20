package com.e3expo.e3.admin.controller.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.common.ResultCode;
import com.e3expo.e3.exceptions.BaseErrorCode;
import com.e3expo.e3.exceptions.FileException;
import com.e3expo.e3.exceptions.InvalidMobileFormatException;
import com.e3expo.e3.exceptions.ShiroUserException;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;

@RestControllerAdvice
public class GlobalRestAdvice {

	/**
	 * 手机号码无效格式异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidMobileFormatException.class)
	@ResponseStatus(BAD_REQUEST)
	public ErrorCode invalidMobileFormat(InvalidMobileFormatException e) {
		// TODO 提出一个ENUM
		return ErrorCode.USER_MOBILE_INVALID;
	}

	/**
	 * 所有的参数不正确的异常都这么设置
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(BAD_REQUEST)
	public BaseErrorCode illegalArgs(IllegalArgumentException e) {
		// todo
		return new WebappErrorCode(400, e.getMessage());
	}

	@ExceptionHandler(value = { ShiroUserException.class })
	public ModelAndView ShiroUserException(ShiroUserException ex) {
		ex.printStackTrace();
		return RestfulJsonModelAndView.buildError(ex.getCode().getValue(), ex.getCode().getDesc(), ex.getMessage());
	}

	@ExceptionHandler(value = { UserException.class })
	public ModelAndView UserException(UserException ex) {
		ex.printStackTrace();
		return RestfulJsonModelAndView.buildError(ex.getCode().getValue(), ex.getCode().getDesc(), ex.getMessage());
	}

	/**
	 * 文件相关异常处理
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = { FileException.class })
	public ModelAndView FileException(FileException ex) {
		ex.printStackTrace();
		return RestfulJsonModelAndView.buildError(ex.getCode().getValue(), ex.getCode().getDesc(), ex.getMessage());
	}

	@ExceptionHandler(value = { Exception.class })
	public ModelAndView defaultExceptionHandle(Exception ex) {
		ex.printStackTrace();
		return RestfulJsonModelAndView.buildError(ResultCode.SYSTEM_ERROR.getValue(), ResultCode.SYSTEM_ERROR.getDesc(),
				ex.getMessage());
	}
}
