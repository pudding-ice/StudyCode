package com.myjava.web;

import com.myjava.Reptile.Reptile;
import com.myjava.domain.Photo;
import com.myjava.domain.User;
import com.myjava.service.PhotoService;
import com.myjava.service.PhotoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/reptileServlet")
public class ReptileServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        爬取图片数据到本地
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer uid = user.getU_id();
        String url = "http://www.netbian.com/desk/";
        String path ="/images/image/";
        Integer inputNum = Integer.valueOf(request.getParameter("inputNum"));
        Reptile reptile = new Reptile(url, path,inputNum,uid);
        ArrayList<Photo> photos = reptile.run();
        //将爬取到的图片集合保存到session中
        session.setAttribute("photos",photos);
        //持久化到数据库
        PhotoService photoService  = new PhotoServiceImpl();
        Boolean res = photoService.savePhotos(photos);
        response.setHeader("refresh","1;url=/index.jsp");
        request.getRequestDispatcher("/index.jsp");
    }
}
