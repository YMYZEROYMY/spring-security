package com.example.security.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("MyAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        这是默认方法，什么都不做，直接调用
//        super.onAuthenticationSuccess(request, response, authentication);

//        跳转到页面

        //添加token到header，但后继的访问头不包含它
//        Object principal=authentication.getPrincipal();
//        if(principal instanceof UserDetails){
//            String username=((UserDetails) principal).getUsername();
//
//            String token=Jwts.builder()
//                    .setSubject(username)
//                    .setExpiration(new Date(System.currentTimeMillis()+60*60))
//                    .signWith(SignatureAlgorithm.HS512,"YMYSecret")
//                    .compact();
//            response.addHeader("Authorization","ymy"+token);
//        }


//        new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");

//        返回json格式
        Object principle=authentication.getPrincipal();
        String name="";
        if(principle instanceof UserDetails){
            name=((UserDetails) principle).getUsername();
        }
        Map<String,String> map=new HashMap<>();
        map.put("flag","true");
        map.put("msg","登录成功，欢迎您，"+name);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
