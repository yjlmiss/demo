package com.example.demo.entity;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Data;

/**
 * 刮削文件表
 * @author lushaowei
 */
@Data
public class ScanFile {
    private String  featurecode;
    private Integer scantaskid;
    private String filename;
    private String filepath;
    private Long filesize;

}
