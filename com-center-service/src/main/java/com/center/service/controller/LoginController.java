package com.center.service.controller;

import com.center.api.dto.request.LoginRequest;
import com.center.common.dto.ResponseVO;
import com.center.common.utils.PasswordUtil;
import com.center.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author heyutang
 * @Title:
 * @Package
 * @Description:
 * @Company
 * @date 2020/7/811:33
 */

@Api(tags = "登录接口")
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

  /**
   * @Description: 登录接口
   * @param loginRequest 登录请求信息
   * @return 返回结果信息
   * @throws
   * @author heyutang
   * @date 2020/7/8 15:14
   */
    @PostMapping("/signin")
    @ResponseBody
    public ResponseVO submitLogin(@RequestBody LoginRequest loginRequest) {

        // 初始化登录密码
        UsernamePasswordToken token = new UsernamePasswordToken();
        try {
            // 密码解码
            String password = PasswordUtil.decrypt(loginRequest.getPassword(),loginRequest.getUsername());
            Boolean rememberMe = loginRequest.getRememberMe() == 0 ? false : true;
            token = new UsernamePasswordToken(loginRequest.getUsername(), password, rememberMe);
            // 获取当前的Subject
            Subject currentUser = SecurityUtils.getSubject();
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return ResultUtil.success("登录成功！");
        } catch (Exception e) {
            logger.error("登录失败，用户名[{}]", loginRequest.getUsername(), e);
            token.clear();
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
     *
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return ResultUtil.redirect("index");
    }
}
