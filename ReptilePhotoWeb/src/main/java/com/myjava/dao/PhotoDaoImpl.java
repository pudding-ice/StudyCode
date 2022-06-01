package com.myjava.dao;

import com.myjava.domain.Photo;
import com.myjava.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhotoDaoImpl implements PhotoDao{
    private QueryRunner queryRunner = JDBCUtils.getQueryRunner();
    @Override
    public  ArrayList<Photo> getPhotoByName(String name,Integer pid) {
        ArrayList<Photo> photos = new ArrayList<>();

        String sql = "select * from photo where p_name=? and u_id=?";
        Object[] parm = {name,pid};
        try {
            photos = (ArrayList<Photo>) queryRunner.query(sql, new BeanListHandler<Photo>(Photo.class), parm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (photos!=null){
            return photos;
        }
        return null;

    }

    @Override
    public ArrayList<Photo> getPhotoByUid(Integer uid) {
        ArrayList<Photo> photos = new ArrayList<>();
        String sql = "select * from photo where u_id=?";
        Object[] parm = {uid};
        try {
            photos = (ArrayList<Photo>) queryRunner.query(sql, new BeanListHandler<Photo>(Photo.class), parm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (photos!=null){
            return photos;
        }
        return null;
    }

    @Override
    public Boolean savePhoto(String p_name, String p_url, Integer u_id) {
        String sql = "Insert into photo (p_url,u_id,p_name) value(?,?,?)";
        int res = 0;
        try {
            res = queryRunner.update(sql, p_url, u_id, p_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Long getPhotoCount(Integer uid) {
        String sql = "select count(*) from photo where u_id = ?";
        Long count = null;
        try {
            count = (Long)queryRunner.query(sql, new ScalarHandler(), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Photo> getPageData(Integer index, Integer pageSize, Integer uid) {
        String sql = "select * from photo where u_id = ? limit ?,?";
        List<Photo> data = null;
        try {
            data = queryRunner.query(sql, new BeanListHandler<>(Photo.class), uid, index, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Photo> getPageData(Integer index, Integer pageSize, Integer u_id, String searchName) {
//        select * from photo where u_id = 1 and p_name like '%高清%' limit 0,5
        String sql = "select * from photo where u_id = ? and p_name like ? limit ?,?";
        List<Photo> data = null;
        try {
            data = queryRunner.query(sql, new BeanListHandler<>(Photo.class), u_id, "%"+searchName+"%",index, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public Long getPhotoCount(String searchName, Integer u_id) {
        String sql = "select count(*) from photo where u_id = ? and p_name like ?";
        Long count = null;
        try {
            count = (Long)queryRunner.query(sql, new ScalarHandler(), u_id,"%"+searchName+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



}
