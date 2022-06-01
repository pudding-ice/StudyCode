package com.myjava.service;

import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface PhotoService {
    Boolean savePhotos(ArrayList<Photo> photos);
    List<Photo> searchPhoto(String photoName,Integer u_id);
    List<Photo> changePhotoPage(String searchText, Integer u_id);
}
