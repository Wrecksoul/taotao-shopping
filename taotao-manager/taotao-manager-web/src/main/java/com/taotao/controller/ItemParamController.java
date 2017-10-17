package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
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
	

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult queryItemParamModeByCatId(@PathVariable Long itemCatId){
		return itemParamService.queryItembyCid(itemCatId);
	}
	
	@RequestMapping(value = "/save/{itemCatId}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItemParam(String paramData/*根据页面名称*/,@PathVariable Long itemCatId){
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(itemCatId);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}
}
