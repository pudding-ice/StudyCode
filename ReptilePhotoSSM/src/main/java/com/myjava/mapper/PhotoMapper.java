package com.myjava.mapper;

import com.myjava.domain.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoMapper {
      List<Photo> getPhotosByNameOrUid(@Param("photoName") String photoName, @Param("u_id") Integer u_id);
      void insertPhotos(@Param("photoList") List<Photo> photos);
//    Long getPhotoCount(Integer uid);
//    List<Photo> getPageData(Integer index, Integer pageSize, Integer uid);
//    Long getPhotoCount(String searchName, Integer u_id);
//    List<Photo> getPageData(Integer index, Integer pageSize, Integer u_id, String searchName);
}
