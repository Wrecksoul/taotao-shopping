package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile){
		//js插件的兼容性要求返回json字符串
		//responseBody注解返回的是contentType是application/json，如果返回字符串则contentType是text
		return JsonUtils.objectToJson(pictureService.uploadFile(uploadFile));
	}
}
