package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCatService;

@Controller
@RequestMapping("/content/category")
public class ContentCatController {
	@Autowired
	private ContentCatService contentCatService;

	/**
	 * 内容分类 异步tree 展示
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<TbContentCategory> catList = contentCatService
				.getContentCatList(parentId);
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory cat : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	/**
	 * 新增节点
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContentCat(Long parentId, String name) {
		return contentCatService.createContentCat(parentId, name);
	}

	/**
	 * 删除节点
	 * 
	 * @param parentId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentCat(
			@RequestParam(required = false) Long parentId, Long id) {
		return contentCatService.deleteContentCat(parentId, id);
	}

	/**
	 * 重命名
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult renameContentCat(Long id, String name) {
		return contentCatService.updateContentCat(id, name);
	}
}
