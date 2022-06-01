package com.myjava.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
    public static HttpServletRequest getRequest(){
        return requestThreadLocal.get();
    }
    public static void setRequest(HttpServletRequest request){
        requestThreadLocal.set(request);
    }

}
