package com.example.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping("/")
    public String userLogin() {
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @RequestMapping("/afterLogin")
    public String afterLogin(){
        Map<String,String> map=new HashMap<>();
        return "loginSuccess";

    }

//    匹配所有url
    @RequestMapping("*")
    public ModelAndView matchAll(ModelAndView mav) {
        mav.setViewName("redirect:/");
        return mav;
    }

}


