package com.github.cadedi.admin.util;

import com.alibaba.fastjson2.JSONObject;
import com.github.cadedi.core.http.HttpResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP工具类
 */
public class HttpUtils {


    /**
     * 获取HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 输出信息到浏览器
     * @param response
     * @param msg
     * @throws IOException
     */
    public static void print(HttpServletResponse response, int code, String msg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        HttpResult result = HttpResult.error(code, msg);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
