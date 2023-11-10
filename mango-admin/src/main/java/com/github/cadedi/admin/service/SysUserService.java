package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysUser;
import com.github.cadedi.core.page.PageRequest;
import com.github.cadedi.core.page.PageResult;

import java.util.List;

public interface SysUserService {
    /**
     * 查找所有用户
     */
    List<SysUser> findAll();

    /**
     * 查找所有用户
     */
    PageResult findPage(PageRequest pageRequest);
}
