package com.myjava.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import com.myjava.domain.Reptile;
import com.myjava.domain.User;
import com.myjava.mapper.PhotoMapper;
import com.myjava.service.PhotoService;
import com.myjava.service.PhotoServiceImpl;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PhotoController {
    @Autowired
    PageBean pageBean;
    @Autowired
    PhotoService photoService;
    @RequestMapping("/reptile")
    public ModelAndView reptilePhoto(@RequestParam("inputNum")Integer inputNum, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        User user = (User)session.getAttribute("user");
        Integer uid = user.getU_id();
        String url = "http://www.netbian.com/index_";
        String path ="/images/image/";
        Reptile reptile = new Reptile(url, path,inputNum,uid);
        ArrayList<Photo> reptilePhotos = reptile.run();
        //持久化到数据库
        Boolean res = photoService.savePhotos(reptilePhotos);
        //获取数据库中的图片
        Page<Photo> page = PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        List<Photo> photos = photoService.searchPhoto(null,user.getU_id());
        PageInfo<Photo> pageInfo = new PageInfo<>(photos,pageBean.getCurrentPage());
        List<Photo> photoList = pageInfo.getList();
        //保存数据到session
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setTotalCount(pageInfo.getTotal());
        session.setAttribute("pageBean",pageBean);
        mv.addObject("photos",photoList);
        mv.setViewName("forward:/WEB-INF/view/index.jsp");
        return mv;
    }

    @RequestMapping("/showPhoto")
    public void showPhoto(@RequestParam("url") String photo_url, HttpServletResponse response){
        try {
            File file;
            BufferedImage im;
            try {
                file = new File("D:/myProject/ReptilePhotoSSM/src/main/webapp" + photo_url);
                im= ImageIO.read(file);
            } catch (Exception e) {
                file = new File("D:/myProject/ReptilePhotoSSM/src/main/webapp/images/list_bg.jpg");
                im= ImageIO.read(file);
            }
            response.setContentType("image/jpeg");
            ServletOutputStream sos=response.getOutputStream();
            ImageIO.write(im, "jpeg", sos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/searchPhoto")
    public ModelAndView searchPhoto(@RequestParam("searchText")String searchText,
                                    HttpSession session){
        ModelAndView mv = new ModelAndView();
        //先设置分页,再进行查询
        Page<Photo> page = PageHelper.startPage(1, pageBean.getPageSize());
        User user = (User) session.getAttribute("user");
        List<Photo> photos = photoService.searchPhoto(searchText, user.getU_id());
        PageInfo<Photo> pageInfo = new PageInfo<>(photos,1);
        List<Photo> photoList = pageInfo.getList();
        pageBean.setCurrentPage(1);
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setTotalCount(pageInfo.getTotal());
        session.setAttribute("pageBean",pageBean);
        session.setAttribute("searchText",searchText);
        mv.addObject("photos",photoList);
        mv.setViewName("forward:/WEB-INF/view/index.jsp");
        return mv;
    }
    @RequestMapping("/changePage/{pageNum}")
    public ModelAndView changePage(HttpSession session,
                                    @PathVariable String pageNum
    ){
        User user = (User) session.getAttribute("user");
        String searchText = (String) session.getAttribute("searchText");
        pageBean.setCurrentPage(Integer.valueOf(pageNum));
        List<Photo> photoList = photoService.changePhotoPage(searchText, user.getU_id());
        session.setAttribute("pageBean",pageBean);
        ModelAndView mv = new ModelAndView();
        mv.addObject("photos",photoList);
        mv.setViewName("forward:/WEB-INF/view/index.jsp");
        return mv;
    }
}
