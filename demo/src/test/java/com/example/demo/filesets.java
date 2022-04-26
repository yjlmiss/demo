package com.example.demo;

import com.example.demo.service.impl.VideoScannerImpl;
import com.example.demo.utils.FileUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class filesets {
    public static void main(String[] args) {
        String sourcePath = "/Users/lushaowei/Downloads/test";
        File f=new File(sourcePath);
//        new filesets().fileList(f);

        List<String> filelist  = FileUtils.getAllFiles(sourcePath);
        System.out.println(filelist);


    }
    public  void fileList(File fl){
        try{
            File[] fs=fl.listFiles();
            for(File file:fs){
                if(file.isDirectory()){
                    System.out.println(file.getName());
                    fileList(file);
                }else{
                    System.out.println(file.getName());
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}