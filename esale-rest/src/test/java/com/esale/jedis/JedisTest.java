package com.esale.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esale.rest.component.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisTest {
	@Test
	public void testJedisSingle(){
		Jedis jedis = new Jedis("10.108.250.115",6379);
		jedis.set("test", "what the hell");
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
	}
	
	
	@Test
	public void testJedisPool(){
		JedisPool jedisPool = new JedisPool("10.108.250.115",6379);
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get("test");
		System.out.println(result);
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void testJedisClientSpring() throws Exception {
		//创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从容器中获得JedisClient对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		//jedisClient操作redis
		jedisClient.set("cliet1", "1000");
		String string = jedisClient.get("cliet1");
		System.out.println(string);
	}
}
