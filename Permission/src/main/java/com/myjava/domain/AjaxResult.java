package com.myjava.domain;

import lombok.Data;
import org.aspectj.weaver.loadtime.Aj;

@Data
public class AjaxResult {
    private Boolean success;
    private String msg;
    //私有构造方法
    private AjaxResult(){}
    private static AjaxResult singleBeanAjax = new AjaxResult();
    private static AjaxResult getSingleBeanAjax(){
        return singleBeanAjax;
    }
    public static AjaxResult getAjaxResult(Integer res,String successMsg,String failMsg){
        AjaxResult ajaxResult = getSingleBeanAjax();
        if (res>0){
            ajaxResult.setSuccess(true);
            ajaxResult.setMsg(successMsg);
        }else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg(failMsg);
        }
        return ajaxResult;
    }
}
