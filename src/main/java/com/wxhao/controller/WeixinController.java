package com.wxhao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxhao.constants.MsgTypes;
import com.wxhao.util.WechatMessageUtil;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxhao.util.SignUtil;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: WeixinController
 * @Description: 响应Controller
 * @version V1.0
 */
@Controller  
@RequestMapping("/weixinCon") 
public class WeixinController {
	
	 private Logger log = Logger.getLogger(WeixinController.class);
	 
	 @RequestMapping(value = "/wechat", method = RequestMethod.GET)
	 public void get(HttpServletRequest request, HttpServletResponse response) {  
		    log.info("请求进来了...");
	        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
	        String signature = request.getParameter("signature");  
	        // 时间戳  
	        String timestamp = request.getParameter("timestamp");  
	        // 随机数  
	        String nonce = request.getParameter("nonce");  
	        // 随机字符串  
	        String echostr = request.getParameter("echostr");  
	  
	        PrintWriter out = null;  
	        try {  
	            out = response.getWriter();  
	            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败  
	            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
	                out.print(echostr);  
	            } 
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            out.close();  
	            out = null;  
	        }  
	    }

	@RequestMapping(value = "/wechat", method = RequestMethod.POST)
	@ResponseBody
	public void post(HttpServletRequest request, HttpServletResponse response)throws ServletException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		try {
			Map<String , String> map = WechatMessageUtil.parseXml(request);

			String ToUserName = map.get("ToUserName");

			String FromUserName = map.get("FromUserName");

			String CreateTime = map.get("CreateTime");

			String MsgType = map.get("MsgType");

			String Content = map.get("Content");

			String MsgId  = map.get("MsgId ");
			log.info("map="+map);
			String message = null;
			if (MsgType.equals(MsgTypes.TEXT)) {//判断是否为文本消息类型
				if (Content.equals("1")) {
					message = MsgTypes.initText(ToUserName, FromUserName,
							"对啊！我也是这么觉得！刘鑫帅哭了！");
				} else if(Content.equals("2")){
					message = MsgTypes.initText(ToUserName, FromUserName,
							"好可怜啊！你年级轻轻地就瞎了！");
				} else if(Content.equals("?") || Content.equals("？")){

					message = MsgTypes.initText(ToUserName, FromUserName,
							"好可怜啊！你年级轻轻地就瞎了！");
				} else {
					message = MsgTypes.initText(ToUserName, FromUserName,
							"没让你选的就别瞎嘚瑟！！！");
				}
			}else if(MsgType.equals(MsgTypes.EVENT)){//判断是否为事件类型
				//从集合中，或许是哪一种事件传入
				String eventType = map.get("Event");
				//关注事件
				if (eventType.equals(MsgTypes.EVENT_TYPE_SUBSCRIBE)) {
					message = MsgTypes.initText(ToUserName, FromUserName, WechatMessageUtil.menuText());
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MsgTypes.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = map.get("EventKey");

					if (eventKey.equals("11")) {
						message = MsgTypes.initText(ToUserName, FromUserName,
								"天气预报菜单项被点击！！！");
					} else if (eventKey.equals("12")) {
						message = MsgTypes.initText(ToUserName, FromUserName,
								"公交查询菜单项被点击！！！");
					}
				}
			}



			System.out.println(message);
			out.print(message);
		} catch (DocumentException e) {

			e.printStackTrace();

		}finally{

			out.close();

		}
	}




}
