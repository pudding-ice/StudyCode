package com.myjava.dao;

import com.myjava.domain.User;
import com.myjava.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    private QueryRunner queryRunner = JDBCUtils.getQueryRunner();
    @Override
    public int addUser(User user) {
        String sql = "Insert into user (username,password) value(?,?)";
        int res = 0;
        try {
            res = queryRunner.update(sql, user.getUsername(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User getUser(User user) {
        //从数据库中查询该数据
        String sql = "select * from user where username=? and password =?";
        User u = null;
        try {
            u = queryRunner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public int queryUserByName(String username) {
        String sql = "select u_id from user where username=?";
        Object[] parm = {username};
        Object[] res = null;
        try {
            res = queryRunner.query(sql, new ArrayHandler(), parm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res!=null){
            return 1;
        }
        return 0;
    }
}
