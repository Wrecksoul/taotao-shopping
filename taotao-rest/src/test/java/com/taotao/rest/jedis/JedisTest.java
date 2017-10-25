package com.taotao.rest.jedis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class JedisTest {
	public void jedisSpringTest(){
		ApplicationContext application = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisCluster client = (JedisCluster)application.getBean("redisCluster");
		client.set("a", "jedis cluser for spring is ok");
		String string = client.get("a");
		System.out.println(string);
		client.close();
	}
	public static void main(String[] args) {
		new JedisTest().jedisSpringTest();
	}
}
