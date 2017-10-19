package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 列表查询
	 * 虽然是列表查询，但是其实edit的查询也是利用这个方法，只是在页面上直接传递的
	 * 所以此处是把所有字段都查询出来，包括大字段
	 * @param page
	 * @param rows
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult getContentList(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer rows, Long categoryId) {
		return contentService.getContentList(page, rows, categoryId);
	}
	
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createConent(TbContent content){
		return contentService.createContent(content);
	}
}
