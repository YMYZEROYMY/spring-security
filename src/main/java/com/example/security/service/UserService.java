package com.example.security.service;

import com.example.security.DTO.DTOInvoice;
import com.example.security.entity.Invoice;
import com.example.security.entity.Movie;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.InvoiceRepository;
import com.example.security.repository.MovieRepository;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.util.DTOChange;
import com.example.security.util.GetUserTeleUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private InvoiceRepository invoiceRepository;

    @Transactional
    public User fullInfo() {
        return userRepository.findByUsername(GetUserTeleUtils.getUserName());
    }

    @Transactional
    public ArrayList<DTOInvoice> getInvoice(){
        List<Invoice> invoices=invoiceRepository.findInvoicesByUser_UsernameOrderByIdDesc(GetUserTeleUtils.getUserName());
        return DTOChange.invoicesToDTO(invoices);
    }

    @Transactional
    public HashMap<String, String> bug(int movieId, int number) {
        System.out.println("准备购买电影id:"+movieId+"数量："+number);
        String flag = "false";
        String msg = "";
        String username = GetUserTeleUtils.getUserName();
        HashMap<String, String> map = new HashMap<>();
        Movie movie = movieRepository.findById(movieId);
        if (movie == null) {
            msg = "电影不存在";
        } else if (movie.getTicket() <= 0 || movie.getTicket() < number) {
            msg = "电影票数量不足";
        } else if (username == null) {
            msg = "请先登录";
        } else {
            User user = userRepository.findByUsername(username);
            Date date = new Date();
            Invoice invoice = new Invoice(user, movie, date, number,movie.getPrice()*number);
            movie.setTicket(movie.getTicket() - number);
            movie.setPopularity(movie.getPopularity()+100*number);
            movieRepository.save(movie);
            invoiceRepository.save(invoice);
            flag = "true";
            msg = "购买成功";
        }
        map.put("flag", flag);
        map.put("msg", msg);
        return map;
    }

    @Transactional
    public HashMap<String, String> whoIm() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", GetUserTeleUtils.getUserName());
        return map;
    }

    @Transactional
    public boolean login(User user) {
        if (user == null) {
            return false;
        }
        User temp = userRepository.findByUsername(user.getUsername());
        return temp != null && (temp.getPassword().equals(user.getPassword()));
    }

    @Transactional
    public HashMap<String, String> register(User user) {
        HashMap<String, String> map = new HashMap<>();
        if (user == null || (user.getUsername() == null) || (user.getPassword() == null)) {
            map.put("flag", "false");
            map.put("msg", "请输入所有信息");
            return map;
        }
        User temp = userRepository.findByUsername(user.getUsername());
        if (temp != null) {
            map.put("flag", "false");
            map.put("msg", "账号已存在");
            return map;
        }

        map.put("flag", "true");
        map.put("msg", "登录成功");
        //添加身份
        Role roleUser = roleRepository.findById(1);
        List<Role> list = new ArrayList<>(1);
        list.add(roleUser);
        user.setRoles(list);

        userRepository.save(user);
        return map;
    }
}
