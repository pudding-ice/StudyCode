package com.myjava.util.springShiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.Md5Hash;

public class GetMD5Password {
    public static String getPassword(Object source, Object salt,int hashIterations){
        Md5Hash md5Hash = new Md5Hash(source,salt,hashIterations);
        return md5Hash.toString();
    }
    public static String getPassword(Object source, int hashIterations){
        Md5Hash md5Hash = new Md5Hash(source,hashIterations);
        return md5Hash.toString();
    }
    public static String getPassword(Object source){
        Md5Hash md5Hash = new Md5Hash(source);
        return md5Hash.toString();
    }
}
