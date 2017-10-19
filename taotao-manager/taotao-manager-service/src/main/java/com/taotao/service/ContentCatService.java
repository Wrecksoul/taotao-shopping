package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCatService {
	List<TbContentCategory> getContentCatList(long parentId);
	
	TaotaoResult createContentCat(long parentId,String name);
	
	TaotaoResult deleteContentCat(Long parentId,Long id);
	
	TaotaoResult updateContentCat(Long id ,String name);
}
