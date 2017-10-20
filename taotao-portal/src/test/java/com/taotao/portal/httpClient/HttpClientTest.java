package com.taotao.portal.httpClient;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	//@Test
	public void doGet() {
		URIBuilder uriBuilder = null;
		HttpGet get = null;
		try {
			uriBuilder = new URIBuilder("http://www.sogou.com/web");
			get = new HttpGet(uriBuilder.build());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// 构建参数
		uriBuilder.addParameter("query", "花千骨");
		// 创建一个get对象

		// 创建httpclient对象
		// 执行get请求
		// 获取返回结果
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(get)) {

			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void doPost(){
		HttpPost post = new HttpPost("http://localhost:8082/httpClient/post.html");
		List<NameValuePair> list = new ArrayList<>();
		BasicNameValuePair username = new BasicNameValuePair("username","张");
		BasicNameValuePair pwd = new BasicNameValuePair("password", "1234");
		list.add(username);
		list.add(pwd);
		HttpEntity form = null;
		try {
			form = new UrlEncodedFormEntity(list, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		post.setEntity(form);
		//post.addHeader("accept", "application/json");
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response =httpClient.execute(post);) {
			
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
