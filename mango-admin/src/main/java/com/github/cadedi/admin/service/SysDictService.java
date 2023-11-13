package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysDict;
import com.github.cadedi.core.service.CurdService;

import java.util.List;

/**
 * 字典管理
 */
public interface SysDictService extends CurdService<SysDict> {

    /**
     * 根据名称查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);
}
