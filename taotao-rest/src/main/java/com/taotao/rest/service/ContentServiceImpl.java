package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<TbContent> getContentList(Long catId) {
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(catId);
		return contentMapper.selectByExample(example);
	}

}
