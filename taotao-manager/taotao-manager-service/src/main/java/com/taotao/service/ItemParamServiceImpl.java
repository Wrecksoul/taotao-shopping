package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public EasyUIResult getItemParamModeList(Integer page, Integer size) {
		TbItemParamExample example = new TbItemParamExample();
		Page<Object> pageInfo = PageHelper.startPage(page, size);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
