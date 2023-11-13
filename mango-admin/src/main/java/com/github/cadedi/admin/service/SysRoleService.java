package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysMenu;
import com.github.cadedi.admin.model.SysRole;
import com.github.cadedi.admin.model.SysRoleMenu;
import com.github.cadedi.core.service.CurdService;

import java.util.List;

public interface SysRoleService extends CurdService<SysRole> {
    /**
     * 查询全部
     * @return
     */
    List<SysRole> findAll();

    /**
     * 查询角色菜单集合
     * @return
     */
    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<SysRole> findByName(String name);

}
