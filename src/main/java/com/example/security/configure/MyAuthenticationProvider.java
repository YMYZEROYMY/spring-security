package com.example.security.configure;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password =(String) authentication.getCredentials();
        User user= (User) userDetailsService.loadUserByUsername(username);
        if (user == null) {
            logger.info("登录的用户名："+username+"不存在");
            throw new BadCredentialsException("用户名不存在");
        }

        if (!user.getPassword().equals(password)) {
            logger.info("登录的密码："+password+"不正确");
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
