package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId) throws Exception;

	EasyUIResult getItemList(Integer page, Integer rows) throws Exception;

	TaotaoResult createItem(TbItem item, String desc,String itemParam);

	TaotaoResult updateItem(TbItem item, String desc);
}
