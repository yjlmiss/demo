package com.example.demo.utils;

import com.example.demo.entity.ScanFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这是一个获取目录下所有文件夹内的所有文件的封装类
 * 当然前提是你要传进来一个正确路径的文件夹
 *
 * @author lushaowei
 */
public class FileUtils {
    private static final List<String> VIDEOFORMAT= Arrays.asList("mp4", "ts","avi","rmvb","wmv","mov","mkv","flv","webm","iso","mpg","m4v");

    /**
     * 获取文件夹下所有的文件
     * @param sourcepath
     * @param scantaskid
     * @return scanFiles
     */
    public static List<ScanFile> getScanfiles(String sourcepath,Integer scantaskid) {

        File file = new File(sourcepath);
        //创建一个集合用来存放返回值
        List<ScanFile> scanFiles = new ArrayList<>();

        if (file.exists() && file.isDirectory()) {
            //重点！   这里要本身一直遍历
            //把遍历得到的东西存放在files里面
            scanFiles(file, scanFiles,scantaskid);
        }
        return scanFiles;


    }

    /**
     * 递归处理文件夹下所有文件，写入scanfile的对象列表中。
     * @param file
     * @param scanFiles
     * @param scantaskid
     */
    private static void scanFiles(File file, List<ScanFile> scanFiles,Integer scantaskid) {
        //.listFiles()方法的使用
        //把文件夹的所有文件（包括文件和文件名）都放在一个文件类的数组里面
        File[] fillArr = file.listFiles();
        //如果是一个空的文件夹
        if (fillArr == null) {
            //后面的不执行，直接返回
            return;
        }
        //如果文件夹有内容,遍历里面的所有文件（包括文件夹和文件），都添加到集合里面
        for (File file2 : fillArr) {
//            if (!file2.isDirectory() && (FileUtils.subfix(file2.getName()))!=null) {
            if (!file2.isDirectory() && FileUtils.formatCheck(file2)) {

                ScanFile scanFile = new ScanFile();
                scanFile.setFilename(FileUtils.subfix(file2.getName()));
                scanFile.setFilepath(file2.getPath());
                scanFile.setFilesize(file2.length());
                scanFile.setScantaskid(scantaskid);
                //如果只是想要里面的文件或者文件夹或者某些固定格式的文件可以判断下再添加
                scanFiles.add(scanFile);

                //添加到集合后，在来判断是否是文件夹，再遍历里面的所有文件
                //方法的递归
                scanFiles(file2, scanFiles,scantaskid);
            } else {
                scanFiles(file2, scanFiles,scantaskid);
            }
        }
    }
    private static boolean formatCheck (File file){
        //获取后缀
        if (FileUtils.VIDEOFORMAT.contains(FileUtils.getfix(file.getName())) ){
            return true;
        }

        return false;

    };

    /**
     * 去除文件后缀
     */
    private static String  subfix(String filename){
        if (filename.contains(".")) {
            String prefix = filename.substring(0,filename.lastIndexOf("."));
            return prefix;
        }
        return filename;

    };

    /**
     * 获取后缀
     * @param filename
     * @return suffix
     */

    private static String getfix(String filename) {
        if (filename.lastIndexOf(".") != -1 ) {
            String suffix = filename.substring(filename.lastIndexOf(".")+1);
            return suffix;
        }
        return null;
    }
    /**
     * 重点理解，这是一个递归方法，会不断来回调用本身，但是所有获得的数据都会存放在集合files里面
     * @param file
     * @param files
     */
    private static void longErgodic(File file, List files) {

        //.listFiles()方法的使用
        //把文件夹的所有文件（包括文件和文件名）都放在一个文件类的数组里面
        File[] fillArr = file.listFiles();

        //如果是一个空的文件夹
        if (fillArr == null) {
            //后面的不执行，直接返回
            return;
        }

        //如果文件夹有内容,遍历里面的所有文件（包括文件夹和文件），都添加到集合里面
        for (File file2 : fillArr) {
            if (!file2.isDirectory()&& FileUtils.formatCheck(file2)) {


                //如果只是想要里面的文件或者文件夹或者某些固定格式的文件可以判断下再添加
//                files.add(FileUtils.getfix(file2.getName()));
                files.add(file2.getName());


                //添加到集合后，在来判断是否是文件夹，再遍历里面的所有文件
                //方法的递归
                longErgodic(file2, files);
            } else {
                longErgodic(file2, files);
            }
        }
    }

    public static List getAllFiles(String dir) {

        //创建一个集合存放遍历到的File
        List<File> files = new ArrayList();

        File file = new File(dir);
        //文件夹必须存在 并且要是文件夹
        if (file.exists() && file.isDirectory()) {
            //重点！   这里要本身一直遍历
            //把遍历得到的东西存放在files里面
            longErgodic(file, files);
        }
        return files;
    }

}
