package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * restful 服务 大广告位查询
	 * @param catId
	 * @return 200 success obj ;500 error stackTrace
	 */
	@RequestMapping("/list/{contentCatId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable(value="contentCatId")Long catId){
		try {
			List<TbContent> contentList = contentService.getContentList(catId);
			return TaotaoResult.ok(contentList);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	public TaotaoResult delContentCache(){
		return TaotaoResult.ok();
	}
}
