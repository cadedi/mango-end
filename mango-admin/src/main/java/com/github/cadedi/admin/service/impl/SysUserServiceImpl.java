package com.github.cadedi.admin.service.impl;

import com.github.cadedi.admin.service.SysUserService;
import com.github.cadedi.admin.dao.SysUserMapper;
import com.github.cadedi.admin.model.SysUser;
import com.github.cadedi.core.page.MybatisPageHelper;
import com.github.cadedi.core.page.PageRequest;
import com.github.cadedi.core.page.PageResult;
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

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper);
    }
}
