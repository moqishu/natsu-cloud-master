package com.natsucloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.natsucloud.common.model.PageData;
import com.natsucloud.common.model.WhereEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis工具类
 * @author moqishu
 *
 * */
public class IBatisUtils {

    private IBatisUtils(){}

    public static Wrapper parseWrapper(String conditionJson) {

        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, Object> map = (Map) JSON.parse(conditionJson);
        for (Map.Entry<String, Object> m : map.entrySet()){
            String colName = m.getValue().toString();
            String[] keyAndType = m.getKey().split("&");
            String key = keyAndType[0];
            String type = keyAndType.length>1?keyAndType[1]:"";
            switch (type){
                case "eq":queryWrapper.eq(key,colName);break;
                case "ne":queryWrapper.ne(key,colName);break;
                case "like": queryWrapper.like(key,colName);break;
                case "likeLeft": queryWrapper.likeLeft(key,colName);break;
                case "likeRight": queryWrapper.likeRight(key,colName);break;
                case "notLike": queryWrapper.notLike(key,colName);break;
                case "gt": queryWrapper.gt(key,colName);break;
                case "lt": queryWrapper.lt(key,colName);break;
                case "ge": queryWrapper.ge(key,colName);break;
                case "le": queryWrapper.le(key,colName);break;
                default:queryWrapper.eq(key,colName);break;
            }
        }

        return queryWrapper;
    }

    public static WhereEntity parseWhere(String conditionJson) {

        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, Object> map = (Map) JSON.parse(conditionJson);
        for (Map.Entry<String, Object> m : map.entrySet()){
            String colName = m.getValue().toString();
            String[] keyAndType = m.getKey().split("&");
            String key = keyAndType[0];
            String type = keyAndType.length>1?keyAndType[1]:"";
            if(key.equals("currentPage") || key.equals("pageSize")){
                continue;
            }
            switch (type){
                case "eq":queryWrapper.eq(key,colName);break;
                case "ne":queryWrapper.ne(key,colName);break;
                case "like": queryWrapper.like(key,colName);break;
                case "likeLeft": queryWrapper.likeLeft(key,colName);break;
                case "likeRight": queryWrapper.likeRight(key,colName);break;
                case "notLike": queryWrapper.notLike(key,colName);break;
                case "gt": queryWrapper.gt(key,colName);break;
                case "lt": queryWrapper.lt(key,colName);break;
                case "ge": queryWrapper.ge(key,colName);break;
                case "le": queryWrapper.le(key,colName);break;
                default:queryWrapper.eq(key,colName);break;
            }
        }
        int currentPage = (int)map.get("currentPage");
        int pageSize = (int)map.get("pageSize");

        WhereEntity model = new WhereEntity();
        model.whereSql = queryWrapper;
        model.page = new Page(currentPage,pageSize);

        return model;
    }

}

