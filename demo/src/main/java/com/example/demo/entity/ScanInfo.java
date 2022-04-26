package com.example.demo.entity;

import lombok.Data;

/**
 * 刮削内容详情
 * @author lushaowei
 */
@Data
public class ScanInfo {
    private Integer id;
    private Integer scanfileid;
    private String infotype;
    private String infopath;
    private String infoname;

}
