package com.wxhao.httptest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxhao.constants.MaterialTypes;
import com.wxhao.util.HttpUtil;

/**
 * @ClassName: HttpTest
 * @Description: Http 发送请求测试类
 * @author zhutulang
 * @date 2016年1月8日
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dongliushui-servlet.xml" })
public class HttpUtilTest {

	@Value("#{weixinProperties['AppId']}")
	private String appId;
	
	@Value("#{weixinProperties['AppSecret']}")
	private String appSecret;
	
	@Value("#{weixinProperties['get_access_token_url']}")
	private String getAccessTokenUrl;
	
	@Value("#{weixinProperties['batchget_material_url']}")
	private String batchgetMaterialUrl;
	
	private String contentType = "application/json";
	
	@Test
	public void doHttpsPost() throws UnsupportedEncodingException{
		String url = getAccessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
		byte[] bytes = HttpUtil.doHttpsGet(url, contentType);
		System.out.println(new String(bytes, "UTF-8"));
	}
	
	@Test
	public void getMaterial() throws UnsupportedEncodingException{
		String url1 = getAccessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
		byte[] bytes1 = HttpUtil.doHttpsGet(url1, contentType);
		String accessTokeJson = new String(bytes1, "UTF-8");
		
		JSONObject accessTokeJSONObject = JSONObject.fromObject(accessTokeJson);
		String accessToke = accessTokeJSONObject.getString("access_token");
		
		String url = batchgetMaterialUrl.replace("ACCESS_TOKEN", accessToke);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("type", MaterialTypes.IMAGE);
		paramMap.put("offset", "0");
		paramMap.put("count", "10");
		System.out.println(new String(HttpUtil.doHttpsPostJson(url, contentType, paramMap),"UTF-8"));
	}
}
