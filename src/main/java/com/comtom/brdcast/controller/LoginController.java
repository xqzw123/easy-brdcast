package com.comtom.brdcast.controller;

import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.common.util.TokenUtils;
import com.comtom.brdcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/safeRest")
public class LoginController {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiResult login(@RequestBody Map<String,String> map) throws Exception{
        String account = map.get("userName");
        String password = map.get("password");
        Boolean exist = accountService.info(account,password).getSuccessful();
        if(exist){
            String token = TokenUtils.token(account,password);
            if(token!=null){
                return ApiResult.success(token);
            }
        }
        return ApiResult.failure();
    }
}
