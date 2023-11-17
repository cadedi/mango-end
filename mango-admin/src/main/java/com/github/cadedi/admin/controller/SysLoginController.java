package com.github.cadedi.admin.controller;

import com.github.cadedi.admin.model.SysUser;
import com.github.cadedi.admin.security.JwtAuthenticatioToken;
import com.github.cadedi.admin.service.SysUserService;
import com.github.cadedi.admin.util.PasswordUtils;
import com.github.cadedi.admin.util.SecurityUtils;
import com.github.cadedi.admin.vo.LoginBean;
import com.github.cadedi.common.utils.IOUtils;
import com.github.cadedi.core.http.HttpResult;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "login")
@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private Producer producer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "获取验证码")
    @GetMapping("captcha.jpg")
    public void captccha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录接口
     */
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request){
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        //从session中获取之前保存的验证码,与前台传来的验证码作匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null){
            return HttpResult.error("验证码已失效");
        }
        if (!captcha.equals(kaptcha)){
            return HttpResult.error("验证码不正确");
        }
        //用户信息
        SysUser user = sysUserService.findByName(username);
        if (user == null){
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(),password,user.getPassword())){
            return HttpResult.error("密码不正确");
        }
        if (user.getStatus() == 0){
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return HttpResult.ok(token);

    }
}
