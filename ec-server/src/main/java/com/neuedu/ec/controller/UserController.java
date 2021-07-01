package com.neuedu.ec.controller;

import com.alibaba.fastjson.JSON;
import com.neuedu.ec.common.CommonResult;
import com.neuedu.ec.common.HttpsClientRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户授权
 */
@RequestMapping("/user")
@RestController
public class UserController {

    String url = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.grant_type}")
    private String grant_type;




    /**
     * code换Session
     * @return
     */
    @RequestMapping("/code2Session")
    public CommonResult<Map> code2Session(String js_code){


        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        Map<String, Object> paramMap = new HashMap<String, Object>();

        url = url+"?appid="+appid
                +"&secret="+secret
                +"&grant_type="+grant_type
                +"&js_code="+js_code;

        String response = restTemplate.getForObject(url,String.class);

        System.out.println("response:"+response);

        Map result = JSON.parseObject(response,Map.class);

        return CommonResult.success(result);
    }
}