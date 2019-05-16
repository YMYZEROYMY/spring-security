package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String userLogin() {
        return "index";
    }

    //    匹配所有url
    @RequestMapping("*")
    public ModelAndView matchAll(ModelAndView mav) {
        mav.setViewName("redirect:/");
        return mav;
    }

}
