package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;

@Service
public class ContentServiceImpl implements ContentService{
	@Value("${rest_base_url}")
	private String rest_base_url;
	@Value("${rest_index_ad_url}")
	private String rest_index_ad_url;

	@Override
	public String getContentListInJson() {
		String json = HttpClientUtil.doGet(rest_base_url+rest_index_ad_url);
		try {
			TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);
			@SuppressWarnings("unchecked")
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			List<Map<String,Object>> resultList = new ArrayList<>();
			for (TbContent content : list) {
				Map<String,Object> map = new HashMap<>();
				map.put("src", content.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", content.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", content.getUrl());
				map.put("alt", content.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
