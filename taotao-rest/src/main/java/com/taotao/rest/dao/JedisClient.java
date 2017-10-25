package com.taotao.rest.dao;

public interface JedisClient {
	String set(String key,String value);
	String get(String key);
	Long hset(String hkey,String key,String value);
	String hget(String hkey,String key);
	Long incr(String key);
	Long expire(String key,int seconds);
	Long ttl(String key);
	Long del(String key);
	Long hdel(String hkey,String key);
}
