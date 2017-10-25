package com.taotao.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSingleClient implements JedisClient {
	@Autowired
	private JedisPool pool;

	@Override
	public String set(String key, String value) {
		Jedis jedis = pool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = pool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = pool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = pool.getResource();
		String value = jedis.hget(hkey, key);
		jedis.close();
		return value;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = pool.getResource();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.hdel(hkey,key);
		jedis.close();
		return result;
	}

}
