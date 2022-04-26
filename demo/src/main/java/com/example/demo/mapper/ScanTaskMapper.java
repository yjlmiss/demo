package com.example.demo.mapper;

import com.example.demo.entity.ScanFile;
import com.example.demo.entity.ScanTask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScanTaskMapper {

    /**
     * 根据任务名查询源路径
     * @param taskname
     * @return scanpath
     */
    @Select("SELECT scanpath from ScanTask where taskname like'%#{taskname}';")
    String findByScanName(String taskname);
    /**
     * 根据任务id查询源路径
     * @param scantaskid
     * @return scanpath
     */
    @Select("SELECT scanpath from ScanTask where id =#{scantaskid};")
    String findByScanTaskId(Integer scantaskid);

    /**
     * 根据任务ID获取scantask对象
     * @param taskid
     * @return
     */
    @Select("SELECT * from ScanTask where id =#{taskid};")
    ScanTask findByTaskId(Integer taskid);

    /**
     * 文件列表持久化
     * @param fileList
     * @return
     */
    @Insert({"<script>",
            "insert into ScanFile (scantaskid, filename, filepath,filesize)  ",
            "SELECT  A.* FROM (",
            "<foreach collection ='list' item='fileList' separator ='union all' >",
            "(SELECT " +
                    "#{fileList.scantaskid} SCANTASKID," +
                    "#{fileList.filename} FILENAME," +
                    "#{fileList.filepath} FILEPATH," +
                    "#{fileList.filesize} FILESIZE FROM DUAL)",
            "</foreach> ) A",
            "</script>"
    })
    void saveScanFile(List<ScanFile> fileList);

    /**
     * 清空该刮削缓存表历史记录
     * @param scantaskid
     * @return
     */
    @Delete("delete from ScanFile where scantaskid=#{scantaskid}")
    void deleteScanFile(Integer scantaskid);






}
