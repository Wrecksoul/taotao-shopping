package com.taotao.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedis;
	@Value("${index_content_redis_key}")
	private String index_content_redis_key;
	
	@Override
	public List<TbContent> getContentList(Long catId) {
		try {
			String cacheString = jedis.hget(index_content_redis_key,catId+"");
			if(StringUtils.isNotBlank(cacheString)){
				List<TbContent> list = JsonUtils.jsonToList(cacheString, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		try {
			String cache = JsonUtils.objectToJson(list);
			jedis.hset(index_content_redis_key,catId+"", cache);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
