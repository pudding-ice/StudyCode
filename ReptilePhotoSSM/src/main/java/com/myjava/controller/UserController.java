package com.myjava.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import com.myjava.domain.User;
import com.myjava.mapper.PhotoMapper;
import com.myjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    PageBean pageBean;
    @Autowired
    UserService userService;
    @Autowired
    PhotoMapper photoMapper;
    @RequestMapping("/login")
    public ModelAndView userLogin(User user, HttpSession session){
        ModelAndView mv = new ModelAndView();
        User getUser = userService.userLogin(user);
        if(getUser == null || getUser.getU_id()==null){
            mv.addObject("loginInfo","用户名或密码错误");
            mv.setViewName("forward:/login.jsp");
            return mv;
        }else {
            Page<Object> page = PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
            List<Photo> photos = photoMapper.getPhotosByNameOrUid(null, getUser.getU_id());
            PageInfo<Photo> photoPageInfo = new PageInfo<>(photos,pageBean.getCurrentPage());
            List<Photo> photoList = photoPageInfo.getList();
            pageBean.setTotalPage(photoPageInfo.getPages());
            pageBean.setTotalCount(photoPageInfo.getTotal());
            mv.addObject("user",getUser);
            mv.addObject("photos",photoList);
            mv.setViewName("index");
            session.setAttribute("user",getUser);
            session.setAttribute("pageBean",pageBean);
            return mv;
        }
    }

    @RequestMapping("/register")
    public ModelAndView register(User user){
        ModelAndView mv = new ModelAndView();
        //通过动态SQL,获取到所有的user列表,遍历寻找其中是否有重复的名字
        boolean res = userService.userRegister(user);
        if (res){
            //如果没有就执行注册操作持久化到数据库,返回到注册页面
            mv.addObject("loginInfo","注册成功请登录");
            mv.setViewName("forward:/login.jsp");
            return mv;
        }else{
            //如果名字重复就返回错误信息并且刷新页面
            mv.addObject("registerInfo","用户名重复");
            mv.setViewName("forward:/regist.jsp");
            return mv;
        }
    }
}
