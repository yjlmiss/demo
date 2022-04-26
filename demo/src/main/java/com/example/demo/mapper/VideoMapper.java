package com.example.demo.mapper;

import com.example.demo.entity.View;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * dao层
 * 数据传输对象
 * @author lushaowei
 */
public interface VideoMapper {

    /**
     * findAll 方法简述
     * <p>输出所有tmp_view表数据</p>
     * @return 返回一组Video数组
     */
    @Select("SELECT scantaskid,id,name,year,score,details,size,isinfo,isimg,iscomment from v_tmp_view ;")
    List<View> findAll();

}
