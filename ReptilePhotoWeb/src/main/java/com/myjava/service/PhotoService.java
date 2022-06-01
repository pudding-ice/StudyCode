package com.myjava.service;

import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;

import java.util.ArrayList;

public interface PhotoService {
    //根据pid和用户输入的内容来查询数据库中的图片
    ArrayList<Photo> searchPhotoByName(String name, Integer pid);
    //查询指定用户ID的对应图片
    ArrayList<Photo> getPhotoByUid(Integer uid);
    //保存爬取的图片
    Boolean savePhotos(ArrayList<Photo> photos);
    PageBean getPageData(Integer currentPage, Integer pageSize,Integer uid);

    PageBean getPageData(String searchName, Integer currentPage, Integer pageSize, Integer u_id);
}
