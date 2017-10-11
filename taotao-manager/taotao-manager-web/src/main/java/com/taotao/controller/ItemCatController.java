package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public List<Map<String, Object>> categoryList(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<Map<String, Object>> result = new ArrayList<>();

		try {
			List<TbItemCat> catList = itemCatService.getItemCatList(parentId);
			for (TbItemCat tbItemCat : catList) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("id", tbItemCat.getId());
				map.put("text", tbItemCat.getName());
				map.put("state", tbItemCat.getIsParent() ? "closed" : "open");
				result.add(map);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return result;
	}
}
