package com.taotao.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCat(long parentId) {
		CatResult result = new CatResult();
		result.setData(getCatNodeList(parentId));
		return result;
	}

	// 递归方法获取树结构
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<?> getCatNodeList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		example.setOrderByClause("sort_order");
		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		List resultList = new ArrayList<>();

		int count = 0;
		for (TbItemCat tbItemCat : list) {

			// 不是叶子节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/" + tbItemCat.getId()
							+ ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setUrl("/products/" + tbItemCat.getId() + ".html");
				/*
				 * 递归调用
				 */
				node.setItem(getCatNodeList(tbItemCat.getId()));
				resultList.add(node);
				// 是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html" + "|"
						+ tbItemCat.getName());
			}

			count++;
			//第一级只取14层
			if (tbItemCat.getParentId() == 0 && count >= 14) {
				break;
			}
		}
		return resultList;
	}

}
