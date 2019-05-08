package com.example.security.configure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbacService {
    private AntPathMatcher antPathMatcher;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principle=authentication.getPrincipal();
        boolean hasPermission=false;
        if(principle instanceof UserDetails){
            String username=((UserDetails) principle).getUsername();
            //此处从数据库获取权限可以使用的资源
            Set<String> urls=new HashSet<>();
            urls.add("/whoim");
            urls.add("/afterLogin");
            for(String url:urls){
                //不能使用equals，因为URL可能会有参数
                if(antPathMatcher.match(url,request.getRequestURI())){
                    hasPermission=true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
