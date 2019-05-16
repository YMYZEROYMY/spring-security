package com.example.security.configure;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    private org.slf4j.Logger logger=LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        logger.info("用户的用户名:"+s);

        //此处查询数据库获取用户信息
        User user= userRepository.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在:"+s);
        }
        return user;
    }

}
