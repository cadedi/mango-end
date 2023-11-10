package com.github.cadedi.core.service;

import com.github.cadedi.core.page.PageRequest;
import com.github.cadedi.core.page.PageResult;

import java.util.List;

/**
 * 通用CURD接口
 */
public interface CurdService<T> {

    /**
     * 保存操作
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除操作
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param pageRequest 自定义,统一分页查询请求对象
     * @return PageResult 自定义,统一分页查询结果
     */
    PageResult findByPage(PageRequest pageRequest);
}
