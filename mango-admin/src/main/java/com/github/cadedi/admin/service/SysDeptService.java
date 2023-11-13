package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysDept;
import com.github.cadedi.core.service.CurdService;

import java.util.List;

/**
 * 机构管理
 */
public interface SysDeptService extends CurdService<SysDept> {

    /**
     * 查询机构树
     */
    List<SysDept> findTree();
}
