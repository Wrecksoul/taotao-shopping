package com.taotao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Override
	public TbItem getItemById(Long itemId) throws Exception{
		return itemMapper.selectByPrimaryKey(itemId);
	}
	/*@Override
	public EasyUIResult getItemList(Integer page, Integer rows)
			throws Exception {
		
		return null;
	}*/

}
