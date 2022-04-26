package com.example.demo.entity;

import lombok.Data;

/**
 * 刮削任务实体类
 * @author lushaowei
 */
@Data
public class ScanTask {
    private Integer id;
    private String taskname;
    private String scanpath;
}
