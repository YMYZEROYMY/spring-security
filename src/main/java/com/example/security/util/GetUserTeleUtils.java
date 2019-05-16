package com.example.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetUserTeleUtils {
    public static synchronized String getUserName(){
        Object authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            return ((Authentication) authentication).getName();
        }
        return null;
    }
}
