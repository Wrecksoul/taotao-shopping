package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	private Logger LOG = LoggerFactory.getLogger(ItemCatController.class);
	@Autowired
	private ItemCatService itemCatService;

	@ResponseBody
	@RequestMapping("/list")
	public List<EasyUITreeNode> categoryList(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EasyUITreeNode> result = new ArrayList<>();

		try {
			List<TbItemCat> catList = itemCatService.getItemCatList(parentId);
			for (TbItemCat tbItemCat : catList) {
				EasyUITreeNode node = new EasyUITreeNode();
				node.setId(tbItemCat.getId());
				node.setText(tbItemCat.getName());
				node.setState(tbItemCat.getIsParent() ? "closed" : "open");
				result.add(node);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return result;
	}
}
