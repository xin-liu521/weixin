package com.wxhao.quartz;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxhao.util.HttpUtil;

/**
 * @ClassName: AccessTokenTaker
 * @Description: 获取access_token
 * @version V1.0
 */
@Component
public class AccessTokenTaker {

	@Value("#{weixinProperties['AppId']}")
	private  String appId;
	
	@Value("#{weixinProperties['AppSecret']}")
	private  String appSecret;
	
	@Value("#{weixinProperties['get_access_token_url']}")
	private  String getAccessTokenUrl;
	
	/**
	 * access_token
	 */
	private static  String ACCESS_TOKEN = null;
	/**
	 * 上次更新access_token时间
	 */
	private static  Long LAST_ACCESS_TOKEN_UPDATE_TIME = null;
	
	private static Logger log = Logger.getLogger(AccessTokenTaker.class);
	
	/**
	 * <p>Title: get<／p>
	 * <p>Description: 每隔一个小时去获取一次access_token<／p>
	 * @version 1.0
	 */
	@Scheduled(fixedRate=3600000)
	private void getTask(){
		get();
	}
	
	/**
	 * <p>Title: getFromCache<／p>
	 * <p>Description: 从缓存中获取access_token<／p>
	 * @return
	 * @version 1.0
	 */
	public static String getFromCache(){
		return ACCESS_TOKEN;
	}
	
	/**
	 * <p>Title: getNew<／p>
	 * <p>Description: 强制更新、获取access_token<／p>
	 * <p>如果发现现在的时间戳和上次更新的时间戳间隔小于5分钟，那么不更新</p>
	 * @return
	 * @version 1.0
	 */
	public  synchronized  String getNew(){
		long timeNow = System.currentTimeMillis();
		if(LAST_ACCESS_TOKEN_UPDATE_TIME == null){
			get();
		}else if(timeNow - LAST_ACCESS_TOKEN_UPDATE_TIME < 300000){
			//如果是5分钟以内
			return ACCESS_TOKEN;
		}else{
			get();
		}
		return ACCESS_TOKEN;
	}
	
	/**
	 * <p>Title: get<／p>
	 * <p>Description: 调用获取access_token接口<／p>
	 * @version 1.0
	 */
	 synchronized  void get(){
		String url = getAccessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
		String contentType = "application/json";
		byte[] bytes = HttpUtil.doHttpsGet(url, contentType);
		try {
			String accessToken = new String(bytes, "UTF-8");
			long timeNow = System.currentTimeMillis();
			ACCESS_TOKEN = accessToken;
			LAST_ACCESS_TOKEN_UPDATE_TIME = timeNow;
			log.info("执行获取access_token任务，access_token="+ACCESS_TOKEN);
	        log.info("时间戳="+LAST_ACCESS_TOKEN_UPDATE_TIME);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getGetAccessTokenUrl() {
		return getAccessTokenUrl;
	}

	public void setGetAccessTokenUrl(String getAccessTokenUrl) {
		this.getAccessTokenUrl = getAccessTokenUrl;
	}
}
