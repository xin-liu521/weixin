package com.wxhao.propertiestest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: WeiXinProTest
 * @Description: 读取配置文件测试
 * @author zhutulang
 * @date 2016年1月10日
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dongliushui-servlet.xml" })
public class WeiXinProTest {
	
	@Value("#{weixinProperties['AppId']}")
	private String appId;
	
	@Value("#{weixinProperties['AppSecret']}")
	private String appSecret;
	
	@Value("#{weixinProperties['get_access_token_url']}")
	private String getAccessTokenUrl;

	@Test
	public void readProperties(){
		System.out.println("appId="+appId+" ,appSecret="+appSecret);
		System.out.println("getAccessTokenUrl="+getAccessTokenUrl);
	}
}
