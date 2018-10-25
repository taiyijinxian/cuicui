package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class RedisTest {

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random(1000);
		for (int i = 0; i < 100000; i++) {
			Thread.sleep(3000);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					save(random.nextInt());
				}
			}).start();
		}
	}
	public static void save(int i) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://localhost:8080/redis/get");
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new NameValuePair("key", "key" + i));
		NameValuePair[] parametersBody = param.toArray(new NameValuePair[param.size()]);
		method.setRequestBody(parametersBody);
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			client.executeMethod(method);
			br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (HttpException e) {
		} catch (IOException e) {
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			method.releaseConnection();
		}
	}
}
