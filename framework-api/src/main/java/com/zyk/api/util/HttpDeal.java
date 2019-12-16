package com.zyk.api.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

/**
 * 在java中处理http请求.
 *
 */
public class HttpDeal {
	private static final Logger logger = LoggerFactory.getLogger(HttpDeal.class);

	/**
	 * 处理get请求.
	 *
	 * @return json
	 */
	public static String get(String url) {
		String methodName = "get";
		logger.info("[开始get请求, URL={}]", url);
		// 实例化httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 实例化get方法
		HttpGet httpget = new HttpGet(url);
		// 请求结果
		CloseableHttpResponse response = null;
		String content = "";
		try {
			// 执行get方法
			response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity(), StringPool.UTF_8);
			}
		} catch (Exception e) {
			logger.error("调用异常...", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("调用异常...", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("调用异常...", e);
				}
			}
			logger.info("[结束get请求]");
		}
		return content;
	}

	/**
	 * 处理post请求.
	 *
	 * @param params
	 *            参数
	 * @return json
	 */
	public static String post(String url, Map<String, String> params) {
		String methodName = "post";
		logger.info("[开始post请求, URL={}, 参数={}]", url, params);

		// （1）实例化 一个 客户发请求类httpClient，用来发送下面的post请求
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// （2）实例化 一个 post方法，用来当做容器存放具体传的参数
		HttpPost httpPost = new HttpPost(url);
		// （3）处理参数，将传过来的map类型的键值对，通过循环 做成 NameValuePair 泛型 的
		// list，也就是实际上最终传的是list
		List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
		// 结果
		CloseableHttpResponse response = null;
		String content = "";
		try {
			// （4）提交的参数：将转化后的list进行编码过滤处理
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, StringPool.UTF_8);
			// （5）将参数给post方法
			httpPost.setEntity(uefEntity);
			// （6）执行post方法
			response = httpclient.execute(httpPost);
			if ("OK".equals(response.getStatusLine().getReasonPhrase())
					|| response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// （7）最终返回结果是个string返回来，传到上面一层方法中去。
				content = EntityUtils.toString(response.getEntity(), StringPool.UTF_8);
			}
		} catch (Exception e) {
			logger.error("调用异常...", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("调用异常...", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("调用异常...", e);
				}
			}

			logger.info("[结束post请求]");
		}
		return content;
	}

	/**
	 * 处理传入Json参数的post请求.
	 *
	 * @param json
	 *            参数
	 * @return json
	 * @author liubin
	 */
	public static String postJson(String url, String json) {
		String methodName = "post";
		logger.info("[开始post请求, URL={}, 参数={}]", url, json);

		// 实例化httpClient
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 实例化post方法
		HttpPost httpPost = new HttpPost(url);
		// 结果
		CloseableHttpResponse response = null;
		String content = "";
		try {
			// 将参数给post方法
			httpPost.setEntity(new StringEntity(json, StringPool.UTF_8));
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			// 执行post方法
			response = httpclient.execute(httpPost);
			if ("OK".equals(response.getStatusLine().getReasonPhrase())
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				content = EntityUtils.toString(response.getEntity(), StringPool.UTF_8);
			}
		} catch (Exception e) {
			logger.error("调用异常...", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("调用	异常...", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("调用	异常...", e);
				}
			}
			logger.info("[结束post请求]");
		}
		return content;
	}
}