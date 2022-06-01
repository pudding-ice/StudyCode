package com.myjava.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import com.myjava.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PageBean pageBean;
    @Autowired
    PhotoMapper photoMapper;

    @Override
    public Boolean savePhotos(ArrayList<Photo> photos) {
        photoMapper.insertPhotos(photos);
        return null;
    }

    @Override
    public List<Photo> searchPhoto(String photoName, Integer u_id) {
        List<Photo> photos = photoMapper.getPhotosByNameOrUid(photoName, u_id);
        return photos;
    }

    @Override
    public List<Photo> changePhotoPage(String searchText, Integer u_id) {
        List<Photo> photoList = null;
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        if (searchText != null && searchText != "") {
            List<Photo> photos = searchPhoto(searchText, u_id);
            PageInfo<Photo> pageInfo = new PageInfo<>(photos, pageBean.getCurrentPage());
            pageBean.setTotalPage(pageInfo.getPages());
            pageBean.setTotalCount(pageInfo.getTotal());
            photoList = pageInfo.getList();
        } else {
            List<Photo> userPhotos = photoMapper.getPhotosByNameOrUid(null, u_id);
            PageInfo<Photo> pageInfo = new PageInfo<>(userPhotos, pageBean.getCurrentPage());
            pageBean.setTotalPage(pageInfo.getPages());
            pageBean.setTotalCount(pageInfo.getTotal());
            photoList = pageInfo.getList();
        }
        return photoList;
    }
}
