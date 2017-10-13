package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult getItemParamModeList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="20")Integer rows){
		
		return itemParamService.getItemParamModeList(page, rows);
	}
}
