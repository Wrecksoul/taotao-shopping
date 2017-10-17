package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	EasyUIResult getItemParamModeList(Integer page, Integer size);
	
	TaotaoResult queryItembyCid(long cid);
	
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
