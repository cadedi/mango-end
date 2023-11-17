package com.github.cadedi.admin.util;

import com.github.cadedi.admin.security.JwtAuthenticatioToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Security相关操作
 */
public class SecurityUtils {

    /**
     * 系统登录认证
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticatioToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        //authenticationManager.authenticate 会调用UserDetailsServiceImpl#loadUserByUsername的逻辑
/*        AuthenticationManager的默认实现类是ProviderManager,其中authenticate调用AuthenticationProvider的authenticate方法完成认证
        可定制 AuthenticationProvider 可以完成各种自定义的需求
        Spring Security 提供了多个默认的 AuthenticationProvider 实现
        默认情况下，Spring Security 使用 DaoAuthenticationProvider 作为主要的身份验证提供者(可以继承扩展)。
        它使用 UserDetailsService 加载用户详细信息并进行身份验证
        UserDetailsService的实现类(一般是自定义继承扩展的)在配置类的configure(AuthenticationManagerBuilder auth)中配置
        UserDetailsService 加载好用户认证信息后会封装认证信息到一个 UserDetails 的实现类(默认实现是 User 类,可以继承扩展)*/
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌进行认证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationeFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}
