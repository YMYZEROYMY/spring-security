package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/bug")
    @ResponseBody
    public Map<String, String> bug() {

        HashMap<String, String> temp = new HashMap<>();
        temp.put("flag", "true");
        temp.put("message", "购买成功");
        return temp;
    }
}
