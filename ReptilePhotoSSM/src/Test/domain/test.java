package domain;

import com.myjava.domain.User;
import com.myjava.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

import java.io.*;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;

    @Test
    public void codeTest() throws FileNotFoundException {


    }

    public static void main(String[] args) {
//        String s1 ="hello";
//        String s2 ="hello";
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));
//        System.out.println(s1 == s2);
//        User user = new User();
//        user.setU_id(2);
//        System.out.println("hello "+user);
//        System.out.println("hello "+user.toString());
    }
    public static void method(String s){
        switch (s){
            case "sth":
                System.out.println("something");
            case "null":
                System.out.println("null");
            default:
                System.out.println("others");
        }
    }
}
