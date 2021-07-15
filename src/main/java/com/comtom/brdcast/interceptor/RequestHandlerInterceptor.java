package com.comtom.brdcast.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.common.constants.ResponseCode;
import com.comtom.brdcast.common.util.TokenUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestHandlerInterceptor implements HandlerInterceptor {



    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * 如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
     * 如果设置为true时，请求将会继续执行后面的操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //获得拦截请求的token
        String token = request.getHeader("token");
        boolean result = true;
       if(token!=null){
        //校验token符不符合格式
           result = TokenUtils.verify(token);
       }
        if(!result){
            // 被拦截
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            try {
                writer = response.getWriter();
                writer.print(JSONObject.toJSONString(ApiResult.failure(ResponseCode.SIGN_ERROR)));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
        return result;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}
