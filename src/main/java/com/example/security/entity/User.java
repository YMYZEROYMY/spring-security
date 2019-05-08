package com.example.security.entity;

import org.hibernate.engine.internal.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @ManyToMany(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;
    //账户是否过期
//    private boolean accountNonExpired;
    //账户是否被冻结
//    private boolean accountNonLocked;
    //账户密码是否过期
//    private boolean credentialsNonExpired;
    //账户是否可用
//    private boolean enabled;

    public User() {
        super();
    }

//    public UserInfo(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.accountNonExpired = accountNonExpired;
//        this.accountNonLocked = accountNonLocked;
//        this.credentialsNonExpired = credentialsNonExpired;
//        this.enabled = enabled;
//    }

    //    这是权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=new ArrayList<>();
        List<Role> roles=this.getRoles();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
