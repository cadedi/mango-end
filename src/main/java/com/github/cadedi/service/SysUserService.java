package com.github.cadedi.service;

import com.github.cadedi.model.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 查找所有用户
     */
    List<SysUser> findAll();
}
