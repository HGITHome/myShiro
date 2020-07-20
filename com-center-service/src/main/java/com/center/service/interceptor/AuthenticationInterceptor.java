package com.center.service.interceptor;

import com.center.api.dto.UserDto;
import com.center.common.constants.SessionConst;
import com.center.common.utils.PasswordUtil;
import com.center.service.biz.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author heyutang
 * @Title:
 * @Package
 * @Description:
 * @Company
 * @date 2020/7/8 11:38
 */


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前的登录用户，判断是否是已经登录了
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return true;
        }
        // 记住我的方式登录，直接通过
        if (subject.isRemembered()) {
            return true;
        }
        // 获取session值
        Session session = subject.getSession(true);
        if (session.getAttribute(SessionConst.USER_SESSION_KEY) != null) {
            return true;
        }
        // 如果前面两个判断都没有通过，则判断是否勾选记住我，实现自动登录
        if(!subject.isRemembered()) {
            logger.warn("未设置“记住我”,跳转到登录页...");
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }
        try {
            Long userId = Long.parseLong(subject.getPrincipal().toString());
            UserDto user = userService.selectByPrimaryKey(userId);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), PasswordUtil.decrypt(user.getPassword(), user.getUsername()), true);
            subject.login(token);
            // 将登录认证信息存在session中
            session.setAttribute(SessionConst.USER_SESSION_KEY, user);
            logger.info("[{}] - 已自动登录", user.getUsername());
        } catch (Exception e) {
            logger.error("自动登录失败", e);
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
