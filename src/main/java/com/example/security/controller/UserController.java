package com.example.security.controller;

import com.example.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/bug")
    @ResponseBody
    public Map<String, String> bug(int id,int number) {
        return userService.bug(id,number);
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public HashMap<String,String> whoIm() {
        return userService.whoIm();
    }

}
