package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public HashMap<String, String> register(User user) {
        return userService.register(user);
    }
}


