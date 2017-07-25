package com.hdg.util.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class HttpClientTool {

	/**
	 * get同步请求，返回请求结果
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, String params) {
		String response = null;
		HttpClient client = new HttpClient();

		client.getHttpConnectionManager().getParams().setConnectionTimeout(300000);// 请求超时5分钟
		HttpMethod method = new GetMethod(url);
		method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");// 模拟浏览器信息
		method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		try {
			if (StringUtils.isNotBlank(params)) {
				method.setQueryString(params);
			}
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = method.getResponseBodyAsStream();
				// BufferedReader br = new BufferedReader(new
				// InputStreamReader(is,"gb2312"));//字符流编码，防止返回数据乱码
				response = IOUtils.toString(is,"utf-8");
				// response = method.getResponseBodyAsString();
			}
		} catch (URIException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();
		}

		return response;

	}
	
	/**
	 * post同步请求数据
	 * @param url
	 * @param data
	 * @return
	 */
	public static String doPost(String url, NameValuePair[] data) {

		String response = "";
		PostMethod method = null;
		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);// 请求超时
			method = new PostMethod(url);
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			method.setRequestBody(data);
			client.executeMethod(method);
			// if (method.getStatusCode() == HttpStatus.SC_OK)
			// {
			response = method.getResponseBodyAsString();
			// }
		} catch (URIException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();
		}
		return response;

	}


	/**
	 * post同步请求数据,大数据请求，基于流
	 * @param url
	 * @param data
	 * @return
	 */
	public static String doBigPost(String url, NameValuePair[] data) {

		String response = "";
		PostMethod method = null;
		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);// 请求超时
			method = new PostMethod(url);
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			method.setRequestBody(data);
			client.executeMethod(method);

			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));

			StringBuffer stringBuffer = new StringBuffer();

			String str = "";

			while((str = reader.readLine())!=null){

				stringBuffer.append(str);
			}

			response= stringBuffer.toString();
		} catch (URIException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();
		}
		return response;

	}
	 
}
