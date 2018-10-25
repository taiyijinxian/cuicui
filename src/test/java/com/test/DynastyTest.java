package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class DynastyTest {

//	@Test
	public void save() {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://localhost:8080/dynasty/save");
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new NameValuePair("name", "汉"));
		param.add(new NameValuePair("startYear", "前206"));
		param.add(new NameValuePair("endYear", "9"));
		param.add(new NameValuePair("age", "215"));
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
//		return stringBuffer.toString();
	}
}
