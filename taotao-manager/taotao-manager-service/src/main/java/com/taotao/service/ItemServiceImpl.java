package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(Long itemId) throws Exception {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	/**
	 * 获取商品列表
	 */
	@Override
	public EasyUIResult getItemList(Integer page, Integer rows)
			throws Exception {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		// 添加商品描述
		insertItemDesc(itemId, desc);
		insertItemParam(itemId, itemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品描述
	 */
	private int insertItemDesc(Long id, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		return itemDescMapper.insert(itemDesc);
	}

	/**
	 * 添加商品规格参数
	 */
	private int insertItemParam(Long itemId,String itemParam) {

		TbItemParamItem record = new TbItemParamItem();
		record.setParamData(itemParam);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		record.setItemId(itemId);
		return itemParamItemMapper.insert(record);
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc) {
		return null;
	}

}
