package com.example.demo.utils;


import com.fasterxml.jackson.core.io.InputDecorator;
import com.sun.codemodel.internal.JForEach;
import org.python.antlr.ast.For;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatNameUtils {


    /**
     * 规则常量
     * key：规则类别
     * value：规则明细
     */
    public static final Map<String, String> RULES_MAP = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("tokyo.*hot", "(cz|gedo|k|n|red-|se)\\d{2,4}");
            put("carib", "\\d{6}(-|_)\\d{3}");
            put("1pon|mura|paco", "\\d{6}(-|_)\\d{3}");
            put("10mu", "\\d{6}(-|_)\\d{2}");
            put("x-art", "x-art\\.\\d{2}\\.\\d{2}\\.\\d{2}");
            put("xxx-av", "xxx-av[^\\d]*(\\d{3,5})[^\\d]*");
            put("heydouga", "(\\d{4})[\\-_](\\d{3,4})[^\\d]*");
            put("heyzo", "heyzo[^\\d]*(\\d{4})");
        }
    });


    /**
     * @param filename
     * @return
     */
    public static String getFeaturecodes(String filename) {
        List<String> featurecodes = new ArrayList<>();
        featurecodes = FormatNameUtils.formatByDictList(filename);

        //  可优化
        if (featurecodes != null) {
            for (String featurecode : featurecodes) {
                return featurecode;
            }


        } else if (filename.contains("-") || (filename.contains("_"))) {

        }


        return null;

    }

    public static String getFeatured(String filename) {
        String featurecode = FormatNameUtils.formatByDict(filename);

        //  可优化
        if (featurecode != null) {
            return featurecode;
        } else if (filename.contains("-") || (filename.contains("_"))) {
            //去除文件名中的多余修饰
            filename = filename.replaceAll("^22-sht\\.me|-fhd|_fhd|^fhd_|^fhd-|-hd|_hd|^hd_|^hd-|-sd|_sd|-1080p|_1080p|-720p|_720p|^\\w+\\.(cc|com)@|-uncensored|_uncensored|-leak|_leak|-4K|_4K","");
            //去除文件名中的时间
            filename = filename.replaceAll("\\[\\d{4}-\\d{1,2}-\\d{1,2}\\] - ","");
            //去除文件名中的集数标签
            filename = filename.replaceAll("(-|_)cd\\d{1,2}","");
            //去除文件名中的中文字幕标签(文件名中有两个-的)
            filename = FormatNameUtils.formatByRules("\\w+(-|_)\\w+",filename);
            //去除文件名中的中文字幕标签（文件名中有一个-的）
            filename = filename.replaceAll("(-|_)(?i)c$","");
            return filename.toUpperCase();
        }else {
            //处理文件名中没有-的数据

            filename = FormatNameUtils.formatByRules("(.+?)\\.",filename);
            filename = filename.replaceAll("\\.","");
            return filename;
        }

    }

    /**
     * 根据文件名匹配规则，击中规则后，初步格式化名称
     * @param filename
     * @return String
     */
    public static String formatByDict(String filename) {
        String role = FormatNameUtils.getruleByDict(filename);
        if (role != null) {
            Matcher m = Pattern.compile(role, Pattern.CASE_INSENSITIVE).matcher(filename);
            List<String> results = new ArrayList<String>();
            while (m.find()) {
                results.add(m.group());
            }
            String result = String.join("", results);
            return result;
        }
        return null;
    }



    /**
     * 根据文件名获取匹配的格式化规则
     * @param filename:文件名
     * @return String:rules or null
     */
    public static String getruleByDict(String filename) {
        for (String rules : FormatNameUtils.RULES_MAP.keySet()) {
            Matcher m = Pattern.compile(rules, Pattern.CASE_INSENSITIVE).matcher(filename);
            while (m.find()) {
                return FormatNameUtils.RULES_MAP.get(rules);
            }
        }
        return null;
    }

    /**
     * 根据文件名获取匹配的格式化规则
     * @param filename
     * @return List<String>
     */
    public static List<String> formatByDictList(String filename) {
        String rules = "\\d{6}(-|_)\\d{3}";
        String rules2 = "1pon|mura|paco";

        Matcher m = Pattern.compile(rules2, Pattern.CASE_INSENSITIVE).matcher(filename);
        List<String> result = new ArrayList<String>();

        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }

    /**
     * 根据文件名和规则进行格式化，并返回结果
     * @param rule
     * @param filename
     * @return String
     */
    public static String formatByRules(String rule ,String filename) {
        if (rule != null) {
            Matcher m = Pattern.compile(rule, Pattern.CASE_INSENSITIVE).matcher(filename);
            List<String> results = new ArrayList<String>();
            while (m.find()) {
                results.add(m.group());
            }
            String result = String.join("", results);
            return result;
        }
        return null;
    }

}
