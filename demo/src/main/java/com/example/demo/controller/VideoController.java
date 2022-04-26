package com.example.demo.controller;

import com.example.demo.entity.ScanFile;
import com.example.demo.entity.View;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.service.impl.VideoScannerImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lushaowei
 */
@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Resource
    VideoMapper videoMapper;

    @Resource
    VideoScannerImpl videoScanner;

    @GetMapping("/view")
    public List<View> getVideo(){

        return videoMapper.findAll();
    }
    @GetMapping("/path")
    public String getPath(){
        return videoScanner.getSourcePath(2);
    };

    @GetMapping("/filelist")
    public List<ScanFile> getScanFileList(){
        return videoScanner.getScanFile(2);
    };

    @PostMapping("/cache")
    public void cacheScanFile(@RequestBody JsonNode jsonParam) throws JSONException {
        Integer scantaskid = jsonParam.get("scantaskid").asInt();

        videoScanner.cacheScanFile(scantaskid);
    }


}
