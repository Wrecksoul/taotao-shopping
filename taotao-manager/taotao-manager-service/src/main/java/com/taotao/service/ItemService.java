package com.taotao.service;

import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long itemId) throws Exception;
	//EasyUIResult getItemList(Integer page, Integer rows) throws Exception;
}
