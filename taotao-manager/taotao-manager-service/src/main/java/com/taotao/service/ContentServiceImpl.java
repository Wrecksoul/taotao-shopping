package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContentExample;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EasyUIResult getContentList(int pageNum,int pageSize,Long categoryId) {
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<?>  rows= contentMapper.selectByExample(example);
		EasyUIResult result = new EasyUIResult();
		result.setRows(rows);
		result.setTotal(page.getTotal());
		return result;
	}

}
