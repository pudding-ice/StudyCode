package com.myjava.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet("/showPhoto")
public class ShowPhotoServelet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            /images/image/small414b618a696f3548fd657cf0d0e8067e1617635628.jpg
            String url=request.getParameter("url");
            System.out.println(url);
            File file;
            BufferedImage im;
            try {
                file = new File("C:/Users/Pudding/Desktop/ReptilePhotoWeb/web" + url);
                im= ImageIO.read(file);
            } catch (Exception e) {
                file = new File("C:/Users/Pudding/Desktop/ReptilePhotoWeb/web/images/list_bg.jpg");
                im= ImageIO.read(file);
            }
            response.setContentType("image/jpeg");
            ServletOutputStream sos=response.getOutputStream();
            ImageIO.write(im, "jpeg", sos);
        } catch (Exception e) {
        }
    }
}
