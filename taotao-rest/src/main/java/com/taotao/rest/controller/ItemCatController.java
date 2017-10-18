package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	
	/**
	 * 实现方式一
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemCatTree(String callback) {
		String json = JsonUtils.objectToJson(itemCatService.getItemCat(0));
		return callback + "(" + json + ");";
	}
	
	@RequestMapping(value = "/itemcat/all1")
	@ResponseBody
	public Object getItemCatTree1(String callback) {
		MappingJacksonValue value = new MappingJacksonValue(itemCatService.getItemCat(0));
		value.setJsonpFunction(callback);
		return value;
	}

}
