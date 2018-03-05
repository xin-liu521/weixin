package com.wxhao.controller;

import com.wxhao.constants.MaterialTypes;
import com.wxhao.quartz.AccessTokenTaker;
import com.wxhao.util.HttpUtil;
import com.wxhao.util.MenuUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/28.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger log = Logger.getLogger(MenuController.class);
    /**
     * 创建菜单
     * */
    @RequestMapping("/createMenu")
    public void createMenu(){
        //调用UTI执行创建菜单的动作
        int status = MenuUtil.createMenu(MenuUtil.initMenu());
        if(status==0){
            System.out.println("菜单创建成功！");
        }else{
            System.out.println("菜单创建失败！");
        }
    }



    /**
     * @desc创建菜单
     */
    public void createMenus() {
        // 调用接口获取access_token
        int status = MenuUtil.createMenu(MenuUtil.initMenu());
        if(status==0){
            System.out.println("菜单创建成功！");
        }else{
            System.out.println("菜单创建失败！");
        }
    }

    /**
     * @desc 跳转注册页面
     * @return
     */
    @RequestMapping("/registerPage")
    public ModelAndView weixinOAuth(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        //得到code
        String CODE = request.getParameter("code");
        log.info("CODE========"+CODE);
        String APPID = MaterialTypes.APP_ID;
        String SECRET = MaterialTypes.APP_SECRET;
        //换取access_token 其中包含了openid
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code".replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", CODE);
        JSONObject json = JSONObject.fromObject(HttpUtil.getJsonObjectByUrl(URL));
        //System.out.println(jsonStr);
        //out.print(jsonStr);
        String openid = json.get("openid").toString();
        log.info("openid========"+openid);

        String access_token = AccessTokenTaker.getFromCache();
        //有了用户的opendi就可以的到用户的信息了
        String userURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        //得到用户信息之后返回到一个页面
        JSONObject userJson = JSONObject.fromObject(HttpUtil.getJsonObjectByUrl(userURL));
        log.info("userJson========"+userJson);
        log.info("access_token========"+access_token);
        view.addObject("openid", openid);
        view.addObject("access_token", access_token);
        view.setViewName("/register");
        return view;
    }
}
