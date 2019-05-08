package com.example.security.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage="/";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        //判断是否为ajax请求
        if (httpServletRequest.getHeader("accept").contains("application/json")
                || (httpServletRequest.getHeader("X-Requested-With") != null && httpServletRequest.getHeader("X-Requested-With").equals("XMLHttpRequest"))) {
            //设置状态为403，无权限状态
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");

            Map<String,String> map=new HashMap<>();
            map.put("flag","true");
            map.put("msg","权限不足");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(map));

        } else if (!httpServletResponse.isCommitted()) {//非ajax请求
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                httpServletRequest.setAttribute(WebAttributes.ACCESS_DENIED_403, e);

                // Set the 403 status code.
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

                //重定向
                httpServletResponse.sendRedirect(errorPage);

                // 请求转发
//                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(errorPage);
//                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            }
        }
    }

}

