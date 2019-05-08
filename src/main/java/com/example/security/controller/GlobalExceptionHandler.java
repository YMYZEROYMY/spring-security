package com.example.security.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(Exception e, HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
