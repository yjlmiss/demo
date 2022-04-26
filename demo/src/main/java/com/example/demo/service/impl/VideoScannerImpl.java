package com.example.demo.service.impl;

import com.example.demo.entity.ScanFile;
import com.example.demo.entity.ScanTask;
import com.example.demo.mapper.ScanTaskMapper;
import com.example.demo.service.VideoScanner;
import com.example.demo.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lushaowei
 */
@Service("VideoScanner")
public class VideoScannerImpl implements VideoScanner {
    @Resource
    ScanTaskMapper scanTaskMapper;
    @Resource
    VideoScannerImpl videoScanner;

    /**
     * 通过任务名获取该任务设定的源路径
     * @param taskname
     * @return
     */
    @Override
    public String getSoucePath(String taskname){

        return scanTaskMapper.findByScanName(taskname);

    }

    @Override
    public ScanTask getScantask(String taskname) {

        return null;
    }

    ;

    /**
     * 通过路径返回该路径下所有文件名的list集合
     * @param sourcepath
     * @return filelist
     */
    @Override
    public List<String> getFoleder(String sourcepath) {

        List<String> filelist  =FileUtils.getAllFiles(sourcepath);

        return filelist;
    }

    /**
     * 通过刮削任务id获取刮削文件对象列表
     * @param scantaskid
     * @return
     */
    @Override
    public List<ScanFile> getScanFile(Integer scantaskid) {


        return FileUtils.getScanfiles(videoScanner.getSourcePath(scantaskid),scantaskid);
    }

    /**
     * 根据任务id获取元数据路径
     *
     * @param scantaskid
     * @return
     */
    @Override
    public String getSourcePath(Integer scantaskid) {


        return scanTaskMapper.findByScanTaskId(scantaskid);
    }

    /**
     * 根据任务ID缓存待处理文件
     *
     * @param scantaskid
     */
    @Override
    @Transactional
    public void cacheScanFile(Integer scantaskid) {
        try {
            //首先清空缓存记录
            scanTaskMapper.deleteScanFile(scantaskid);
            //获取scanfile列表
            List<ScanFile> scanfiles = videoScanner.getScanFile(scantaskid);
            //持久化文件缓存
            scanTaskMapper.saveScanFile(scanfiles);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }
}
