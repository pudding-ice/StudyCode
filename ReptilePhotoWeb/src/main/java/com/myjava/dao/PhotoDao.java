package com.myjava.dao;

import com.myjava.domain.Photo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface PhotoDao {
    ArrayList<Photo> getPhotoByName(String name,Integer pid);
    ArrayList<Photo> getPhotoByUid(Integer uid);

    Boolean savePhoto(String p_name, String p_url, Integer u_id);

    Long getPhotoCount(Integer uid);
    List<Photo> getPageData(Integer index, Integer pageSize, Integer uid);

    Long getPhotoCount(String searchName, Integer u_id);

    List<Photo> getPageData(Integer index, Integer pageSize, Integer u_id, String searchName);
}
