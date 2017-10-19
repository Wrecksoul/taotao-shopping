package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCatServiceImpl implements ContentCatService {
	@Autowired
	private TbContentCategoryMapper contentCatMapper;

	/**
	 * 获取分类列表
	 */
	@Override
	public List<TbContentCategory> getContentCatList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		return contentCatMapper.selectByExample(example);
	}

	/**
	 * 新建分类
	 */
	@Override
	public TaotaoResult createContentCat(long parentId, String name) {
		//需要在mapper设置主键返回selectkey,select last_insert_id();
		TbContentCategory category = new TbContentCategory();
		category.setName(name);
		category.setParentId(parentId);
		category.setIsParent(false);
		category.setStatus(1);//1正常 2删除
		category.setSortOrder(1);//默认 1
		category.setCreated(new Date());
		category.setUpdated(new Date());
		contentCatMapper.insert(category);
		
		/**
		 * 查看父节点的isParent是否为true，为false就更新为true
		 */
		TbContentCategory parent = contentCatMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			contentCatMapper.updateByPrimaryKey(parent);
		}
		
		return TaotaoResult.ok(category);
	}

	/**
	 * 删除分类
	 */
	@Override
	public TaotaoResult deleteContentCat(Long parentId, Long id) {
		if(parentId==null){
			parentId = contentCatMapper.selectByPrimaryKey(id).getParentId();
		}
		/*
		 * 级联删除此节点及下级节点
		 */
		deleteCategoryCascade(id);
		/*
		 * 如果父节点没有其他节点，把父节点isparent设置为false
		 */
		TbContentCategoryExample countExample = new TbContentCategoryExample();
		countExample.createCriteria().andParentIdEqualTo(parentId);
		int i = contentCatMapper.countByExample(countExample);
		if(i<=0){
			TbContentCategoryExample parentExample = new TbContentCategoryExample();
			parentExample.createCriteria().andIdEqualTo(parentId);
			TbContentCategory record = new TbContentCategory();
			record.setIsParent(false);
			contentCatMapper.updateByExampleSelective(record, parentExample);
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 私有方法 级联删除节点
	 * @param id
	 */
	private void deleteCategoryCascade(long id){
		TbContentCategory category = contentCatMapper.selectByPrimaryKey(id);
		if(category.getIsParent()){
			TbContentCategoryExample example = new TbContentCategoryExample();
			example.createCriteria().andParentIdEqualTo(id);
			List<TbContentCategory> list = contentCatMapper.selectByExample(example);
			for (TbContentCategory cat : list) {
				deleteCategoryCascade(cat.getId());
			}
		}
		contentCatMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TaotaoResult updateContentCat(Long id, String name) {
		TbContentCategory record = new TbContentCategory();
		record.setId(id);
		record.setName(name);
		record.setUpdated(new Date());
		contentCatMapper.updateByPrimaryKeySelective(record);
		return TaotaoResult.ok();
	}
	
}
