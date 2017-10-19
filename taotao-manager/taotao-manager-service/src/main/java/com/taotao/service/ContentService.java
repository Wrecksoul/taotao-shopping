package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EasyUIResult getContentList(int pageNum,int pageSize,Long categoryId);
	
	TaotaoResult createContent(TbContent content);
}
