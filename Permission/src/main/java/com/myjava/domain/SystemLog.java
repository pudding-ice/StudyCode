package com.myjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SystemLog {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date optime;

    private String ip;

    private String function;

    private String params;
}