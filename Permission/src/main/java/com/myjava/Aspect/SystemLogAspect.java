package com.myjava.Aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjava.domain.SystemLog;
import com.myjava.mapper.SystemLogMapper;
import com.myjava.util.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
public class SystemLogAspect {
    @Autowired
    SystemLogMapper systemLogMapper;

    public void writeLog(JoinPoint joinpoint) throws JsonProcessingException {
        System.out.println("写入系统日志");
        //从工具类中的线程变量中获取到request
        HttpServletRequest request = RequestUtil.getRequest();
        SystemLog log = new SystemLog();
        if (request!=null){
            //设置请求的Ip地址
            log.setIp(request.getRemoteAddr());
        }
        //获取方法的全路径名
        String methodPath = joinpoint.getTarget().getClass().getName();
        //获取方法的签名
        String signature = joinpoint.getSignature().getName();
        //拼接获得方法的唯一标识地址
        String methodName = methodPath + ":" + signature;
        //获取到方法的参数,转为json格式
        Object[] args = joinpoint.getArgs();
        String argsString = new ObjectMapper().writeValueAsString(args);
        log.setFunction(methodName);
        log.setParams(argsString);
        log.setOptime(new Date());
        int insert = systemLogMapper.insert(log);
    }
}
