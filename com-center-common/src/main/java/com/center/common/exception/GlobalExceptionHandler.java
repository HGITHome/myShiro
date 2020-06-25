package com.center.common.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.center.common.response.RestResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常
 */
@ControllerAdvice
public class GlobalExceptionHandler implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ApplicationContext applicationContext;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        if(e instanceof UnauthenticatedException){
            return new RestResponse().returnFail(401,"认证失败");
        }else if(e instanceof UnauthorizedException){
            return new RestResponse().returnFail(401,"无权限访问");
        }
        return new RestResponse().returnFail(e.getMessage());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}