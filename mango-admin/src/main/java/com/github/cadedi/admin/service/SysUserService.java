package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysUser;
import com.github.cadedi.admin.model.SysUserRole;
import com.github.cadedi.core.page.PageRequest;
import com.github.cadedi.core.page.PageResult;
import com.github.cadedi.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SysUserService extends CurdService<SysUser> {
    /**
     * 查找所有用户
     */
    List<SysUser> findAll();

    /**
     * 查找所有用户
     */
    PageResult findPage(PageRequest pageRequest);

    SysUser findByName(String username);

    /**
     * 查找用户的菜单权限标识集合
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     */
    List<SysUserRole> findUserRoles(Long userId);

    /**
     * 生成用户信息Excel文件
     * @param pageRequest 要导出的分页查询参数
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);
}
