package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 查找所有用户
     */
    List<SysUser> findAll();
}
