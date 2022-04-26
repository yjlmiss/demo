package com.example.demo.service;

import com.example.demo.entity.ScanFile;
import com.example.demo.entity.ScanTask;

import java.util.List;
import java.util.SplittableRandom;

/**
 * 影音文件扫描器
 * @author lushaowei
 */
public interface VideoScanner {
    /**
     *根据任务名获取源路径
     * @return
     */
     String getSoucePath(String taskname);

    /**
     * 根据任务名获取scantask对象
     * @param taskname
     * @return
     */
     ScanTask getScantask(String taskname);

    /**
     * 遍历路径下影音文件
     * @param sourcepath
     * @return List<String>
     */
    List<String> getFoleder(String sourcepath);

    /**
     * 根据任务id获取scanfile对象列表
     * @param scantaskid
     * @return
     */
    List<ScanFile> getScanFile(Integer scantaskid);

    /**
     * 根据任务id获取元数据路径
     * @param scantaskid
     * @return
     */
    String getSourcePath(Integer scantaskid);

    /**
     * 根据任务ID缓存待处理文件
     * @param scantaskid
     */
    void cacheScanFile(Integer scantaskid);


}
