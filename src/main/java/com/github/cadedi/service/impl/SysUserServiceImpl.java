package com.github.cadedi.service.impl;

import com.github.cadedi.dao.SysUserMapper;
import com.github.cadedi.model.SysUser;
import com.github.cadedi.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查找所有用户
     */
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
