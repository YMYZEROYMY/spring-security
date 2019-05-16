package com.example.security.service;

import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.util.GetUserTeleUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Transactional
    public User fullInfo(){
        return userRepository.findByUsername(GetUserTeleUtils.getUserName());
    }

    @Transactional
    public HashMap<String, String> whoIm(){
        HashMap<String,String> map=new HashMap<>();
        map.put("name",GetUserTeleUtils.getUserName());
        return map;
    }

    @Transactional
    public boolean login(User user){
        if(user==null){
            return false;
        }
        User temp=userRepository.findByUsername(user.getUsername());
        return temp != null && (temp.getPassword().equals(user.getPassword()));
    }

    @Transactional
    public HashMap<String,String> register(User user){
        HashMap<String,String> map=new HashMap<>();
        if(user==null||(user.getUsername()==null)||(user.getPassword()==null)){
            map.put("flag","false");
            map.put("msg","请输入所有信息");
            return map;
        }
        User temp=userRepository.findByUsername(user.getUsername());
        if(temp!=null){
            map.put("flag","false");
            map.put("msg","账号已存在");
            return map;
        }

        map.put("flag","true");
        map.put("msg","登录成功");
        //添加身份
        Role roleUser=roleRepository.findById(1);
        List<Role> list=new ArrayList<>(1);
        list.add(roleUser);
        user.setRoles(list);

        userRepository.save(user);
        return map;
    }
}
