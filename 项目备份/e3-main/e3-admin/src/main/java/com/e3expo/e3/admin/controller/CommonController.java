package com.e3expo.e3.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.admin.controller.rest.RestfulJsonModelAndView;
import com.e3expo.e3.admin.services.FileService;
@Controller
public class CommonController extends BaseController {
	@Autowired
	private FileService fileService;
	/**
	 * @api {post} /fileUpload
	 * @apiGroup FileOperation
	 * @apiDescription 上传单个文件(api)
	 * @apiParam {MultipartFile} attachment 文件的媒体数据
	 * @apiSuccess {string} data 图片在服务器的路径
	 * @apiSuccess {int} code 200成功
	 * 
	 */
	@PostMapping("/fileUpload")
    public ModelAndView fileUpload(MultipartFile attachment) {
    	String filePath=this.fileService.fileUpload(attachment);
		return RestfulJsonModelAndView.buildJsonModelAndView(filePath);
    }
	/**
	 * @api {post} /fileUploads
	 * @apiGroup FileOperation
	 * @apiDescription 上传多个文件(api)
	 * @apiParam {MultipartFile[]} attachment 文件的媒体数据(数组，多个文件)
	 * @apiSuccess {string[]} data 数组.图片在服务器的路径
	 * @apiSuccess {int} code 200成功
	 * 
	 */
	@PostMapping("/fileUploads")
    public ModelAndView fileUploads(MultipartFile[] attachment) {
    	List<String> filePaths=this.fileService.fileUploads(attachment);
		return RestfulJsonModelAndView.buildJsonModelAndView(filePaths);
    }
}
