package com.myjava.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Driver;
import java.util.Properties;

public class JDBCUtils {
    private JDBCUtils(){};

    public static QueryRunner getQueryRunner(){
        DataSource dataSource = JDBCUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner;
    }

    /**获取druid数据源*/
    public static DataSource getDataSource(){
        try {
            /**读取数据库的配置信息*/
            Properties properties = new Properties();
            /**获取配置文件路径 一定是classes目录当中文件*/
            String path = JDBCUtils.class.getClassLoader().getResource("db.properties").getPath();
            FileInputStream in = new FileInputStream(path);
            /**读取配置文件信息  */
            properties.load(in);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
