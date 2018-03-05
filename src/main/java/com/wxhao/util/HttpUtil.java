package com.wxhao.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName: HttpUtil
 * @Description: Http请求工具类
 * @version V1.0
 */
public class HttpUtil {

	/**
	 * <p>Title: doHttpsPost<／p>
	 * <p>Description: 发送https 形式的post请求<／p>
	 * @param url  请求url
	 * @param contentType
	 * @param paramMap 参数map
	 * @return
	 * @version 1.0
	 */
	public static byte[] doHttpsPostJson(String url, String contentType, Map<String, String> paramMap){
		return postJson(1, url, contentType, paramMap);
	}
	
	/**
	 * <p>Title: doHttpsPost<／p>
	 * <p>Description: 发送http 形式的post请求<／p>
	 * @param url  请求url
	 * @param contentType
	 * @param paramMap 参数map
	 * @return
	 * @version 1.0
	 */
	public static byte[] doPostJson(String url, String contentType, Map<String, String> paramMap){
		return postJson(0, url, contentType, paramMap);
	}
	
	/**
	 * <p>Title: doHttpsGet<／p>
	 * <p>Description: 发送https 形式的get请求<／p>
	 * @param url 请求url
	 * @param contentType
	 * @return
	 * @version 1.0
	 */
	public static byte[] doHttpsGet(String url, String contentType){
		return get(1, url, contentType);
	}
	
	/**
	 * <p>Title: doGet<／p>
	 * <p>Description: 发送http 形式的gett请求<／p>
	 * @param url 请求url
	 * @param contentType
	 * @return
	 * @version 1.0
	 */
	public static byte[] doGet(String url, String contentType){
		return get(0, url, contentType);
	}
	
	/**
	 * <p>Title: post<／p>
	 * <p>Description: 发送post请求,表单提交参数<／p>
	 * @param type 0：普通post请求  1：https形式的post请求
	 * @param url 请求url
	 * @param contentType  
	 * @param paramMap 参数map
	 * @return
	 * @version 1.0
	 */
	private static byte[] postCommon(int type, String url, String contentType, Map<String, String> paramMap){
		// 响应内容
	    byte[] bs = null;
	    
	    HttpClient httpClient = null;
	    HttpPost httpPost = null;
	    
	    try {
	    	
	      if(type == 0){
	    	// 创建发送 http 请求的httpClient实例
		 	httpClient = new DefaultHttpClient();
	      }else if(type == 1){
	    	// 创建发送 https 请求的httpClient实例
		 	httpClient = new SSLClient();
	      }
	      
	      // 创建HttpPost
	      httpPost = new HttpPost(url); 
	      httpPost.setHeader("content-type", contentType);
	      //设置参数
	      List<NameValuePair> list = new ArrayList<NameValuePair>(); 
	      if(paramMap != null){
	    	  Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();  
	          while(iterator.hasNext()){  
	              Entry<String,String> elem = (Entry<String, String>) iterator.next();  
	              list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
	          }  
	          if(list.size() > 0){  
	              UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");  
	              httpPost.setEntity(entity);  
	          }
	      }
	      // 执行POST请求
	      HttpResponse response = httpClient.execute(httpPost); 
	      // 获取响应实体
	      HttpEntity entity = response.getEntity(); 
	      if(entity != null){
	    	  bs = EntityUtils.toByteArray(entity);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // 关闭连接,释放资源
	      httpClient.getConnectionManager().shutdown();
	      httpPost = null;
	      httpClient = null;
	    }
	    return bs;
	}
	
	/**
	 * <p>Title: post<／p>
	 * <p>Description: 发送post请求,json方式提交参数<／p>
	 * @param type 0：普通post请求  1：https形式的post请求
	 * @param url 请求url
	 * @param contentType  
	 * @param paramMap 参数map
	 * @return
	 * @version 1.0
	 */
	private static byte[] postJson(int type, String url, String contentType, Map<String, String> paramMap){
		// 响应内容
	    byte[] bs = null;
	    
	    HttpClient httpClient = null;
	    HttpPost httpPost = null;
	    
	    try {
	    	
	      if(type == 0){
	    	// 创建发送 http 请求的httpClient实例
		 	httpClient = new DefaultHttpClient();
	      }else if(type == 1){
	    	// 创建发送 https 请求的httpClient实例
		 	httpClient = new SSLClient();
	      }
	      
	      // 创建HttpPost
	      httpPost = new HttpPost(url); 
	      httpPost.setHeader("content-type", contentType);
	      if(paramMap != null){
	    	  Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();  
	    	  // 接收参数json列表  
              JSONObject jsonParam = new JSONObject();
	          while(iterator.hasNext()){  
	              Entry<String,String> elem = (Entry<String, String>) iterator.next();  
	              jsonParam.put(elem.getKey(), elem.getValue());    
	          }  
	          if(jsonParam.size() > 0){
	              StringEntity entity = new StringEntity(jsonParam.toString(),"UTF-8");
                  entity.setContentEncoding("UTF-8");    
                  entity.setContentType("application/json");    
                  httpPost.setEntity(entity);    
	          }
	      }
	      // 执行POST请求
	      HttpResponse response = httpClient.execute(httpPost); 
	      // 获取响应实体
	      HttpEntity entity = response.getEntity(); 
	      if(entity != null){
	    	  bs = EntityUtils.toByteArray(entity);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // 关闭连接,释放资源
	      httpClient.getConnectionManager().shutdown();
	      httpPost = null;
	      httpClient = null;
	    }
	    return bs;
	}
	
	/**
	 * <p>Title: get<／p>
	 * <p>Description: 发送get请求<／p>
	 * @param type  0：普通get请求  1：https形式的get请求
	 * @param url   请求url
	 * @param contentType
	 * @return
	 * @version 1.0
	 */
	private static byte[] get(int type, String url, String contentType){
		// 响应内容
	    byte[] bs = null;
	    
	    HttpClient httpClient = null;
	    HttpGet httpGet = null;
	    
	    try {
	      if(type == 0){
	    	// 创建发送 http 请求的httpClient实例
		 	httpClient = new DefaultHttpClient();
	      }else if(type == 1){
	    	// 创建发送 https 请求的httpClient实例
		 	httpClient = new SSLClient();
	      }
	      
	      // 创建HttpPost
	      httpGet = new HttpGet(url); 
	      httpGet.setHeader("content-type", contentType);
	      // 执行POST请求
	      HttpResponse response = httpClient.execute(httpGet); 
	      // 获取响应实体
	      HttpEntity entity = response.getEntity(); 
	      if(entity != null){
	    	  bs = EntityUtils.toByteArray(entity);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // 关闭连接,释放资源
	      httpClient.getConnectionManager().shutdown();
	      httpGet = null;
	      httpClient = null;
	    }
	    return bs;
	}

	/**
	 * 根据api访问地址获取地址返回的json数据
	 *
	 * @param apiUrl
	 *
	 * @return json
	 */
	public static String getJsonObjectByUrl(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String charSet = null;
			charSet = "utf-8";
			byte b[] = new byte[200];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			// System.out.println("content:" + content);
			JSONObject json = JSONObject.fromObject(new String(content
					.getBytes(), "utf-8"));

			urlStream.close();
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
