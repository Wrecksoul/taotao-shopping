package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;

public interface ContentService {
	EasyUIResult getContentList(int pageNum,int pageSize,Long categoryId);
}
