package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	/**
	 * 返回首页
	 * 大广告位展示
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String json = contentService.getContentListInJson();
		model.addAttribute("ad1", json);
		return "index";
	}

	/**
	 * 用户测试httpClient的方法
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/httpClient/post", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult postTest(String username,String password) {
		return TaotaoResult.ok(username+"|"+password);
	}
}
