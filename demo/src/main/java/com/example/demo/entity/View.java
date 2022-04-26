package com.example.demo.entity;

import lombok.Data;

/**
 * video实体类，与数据库表每个字段一一对应
 * @author lushaowei
 */
@Data
public class View {
    private Integer id;
    private String name;
    private String year;
    private Float score;
    private Double size;
    private Boolean isimg;
    private Boolean isinfo;
    private Boolean iscomment;
    private String details;
    private Integer scantaskid;
}
