package com.myjava.service;

import com.myjava.dao.PhotoDao;
import com.myjava.dao.PhotoDaoImpl;
import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhotoServiceImpl implements PhotoService{
    private PhotoDao photoDao = new PhotoDaoImpl();
    @Override
    public ArrayList<Photo> searchPhotoByName(String name,Integer pid) {
        ArrayList<Photo> photoByName = photoDao.getPhotoByName(name, pid);
        return photoByName;
    }

    @Override
    public ArrayList<Photo> getPhotoByUid(Integer uid) {
        ArrayList<Photo> photos = photoDao.getPhotoByUid(uid);
        return photos;
    }

    @Override
    public Boolean savePhotos(ArrayList<Photo> photos) {
        Boolean res = null;
        Iterator<Photo> it = photos.iterator();
        while (it.hasNext()) {
            Photo photo = it.next();
            res = photoDao.savePhoto(photo.getP_name(), photo.getP_url(), photo.getU_id());
        }
        return res;
    }

    @Override
    public PageBean getPageData(Integer currentPage, Integer pageSize,Integer uid) {
        PageBean pageBean = new PageBean();
        /**设置当前页*/
        pageBean.setCurrentPage(Integer.valueOf(currentPage));
        /**查询该分类下有多少条记录*/
        Long count =  photoDao.getPhotoCount(uid);
        pageBean.setTotalCount(count);

        /**计算总页数*/
        double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / Integer.valueOf(pageSize));//对结果向上整  有余数就+1
        /**设置总页数*/
        pageBean.setTotalPage((int)totalPage);

        /**设置当前页数据*/
//        pageBean.setDataList();
        /**计算本次查询的角标*/
        Integer index = (pageBean.getCurrentPage() - 1) * Integer.valueOf(pageSize);
        /**查询本页的显示数据*/
        List<Photo> pageData =  photoDao.getPageData(index,Integer.valueOf(pageSize),uid);
        pageBean.setDataList(pageData);

        return pageBean;
    }

    @Override
    public PageBean getPageData(String searchName, Integer currentPage, Integer pageSize, Integer u_id) {
        PageBean pageBean = new PageBean();
        /**设置当前页*/
        pageBean.setCurrentPage(Integer.valueOf(currentPage));
        /**查询该分类下有多少条记录*/
        Long count =  photoDao.getPhotoCount(searchName,u_id);
        pageBean.setTotalCount(count);

        /**计算总页数*/
        double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / Integer.valueOf(pageSize));//对结果向上整  有余数就+1
        /**设置总页数*/
        pageBean.setTotalPage((int)totalPage);

        /**设置当前页数据*/
//        pageBean.setDataList();
        /**计算本次查询的角标*/
        Integer index = (pageBean.getCurrentPage() - 1) * Integer.valueOf(pageSize);
        /**查询本页的显示数据*/
        List<Photo> pageData =  photoDao.getPageData(index,Integer.valueOf(pageSize),u_id,searchName);
        pageBean.setDataList(pageData);

        return pageBean;
    }


}
