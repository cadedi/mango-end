package com.github.cadedi.core.page;

import com.github.cadedi.common.utils.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Mybatis分页查询助手
 */
public class MybatisPageHelper {
    
    public static final String findPage = "findPage";


    /**
     * 分页查询,传入查询方法名"findPage"
     * @param pageRequest 分页请求
     * @param mapper Dao对象,Mybatis的Mapper
     * @return PageResult 分页结果
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper){
        return findPage(pageRequest,mapper,findPage);
    }

    /**
     * 调用分页插件进行分页查询
     * @param pageRequest 分页请求
     * @param mapper Dao对象,Mybatis的Mapper
     * @param queryMethodName 分页查询方法名
     * @param args 方法参数
     * @return PageResult 分页结果
     */
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        //设置分页参数
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        //反射调用查询方法
        Object result = ReflectionUtils.invoke(mapper,queryMethodName,args);
        return getPageResult(pageRequest,new PageInfo((List)result));
    }

    /**
     * 封装分页信息到统一接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult(PageRequest pageRequest,PageInfo<?> pageInfo){
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
