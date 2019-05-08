package com.example.security.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider provider;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //设置禁止访问403,权限不足时可用
                .exceptionHandling().accessDeniedHandler(getAccessDeniedHandler())
                .and()
                //设置登陆
                .formLogin().loginPage("/").loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .permitAll()
                .and()
                //设置记住我
                .rememberMe()
                .rememberMeParameter("remember-me").userDetailsService(myUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60)
                .key("ymy")
                .and()
                //设置登出
                .logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/whoim").authenticated()
                //限制角色对应的访问
//                .anyRequest().access("@rbacService.hasPermission(httpServletRequest,authentication)")
                .anyRequest().permitAll()//允许所有请求
//                .anyRequest().authenticated()//登陆后方可访问
                .and().csrf().disable();

//        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.authenticationProvider(provider);

//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("admin").password(encoder.encode("123123")).roles("USER")
//                .and()
//                .withUser("test").password("test").roles("ADMIN");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }
}
