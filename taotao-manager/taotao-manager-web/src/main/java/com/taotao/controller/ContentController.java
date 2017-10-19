package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult getContentList(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer rows, Long categoryId) {
		return contentService.getContentList(page, rows, categoryId);
	}
}
