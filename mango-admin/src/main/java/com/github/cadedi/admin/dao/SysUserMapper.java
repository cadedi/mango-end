package com.github.cadedi.admin.dao;

import com.github.cadedi.admin.model.SysUser;
import com.github.cadedi.admin.model.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查询全部
     */
    List<SysUser> findAll();

    /**
     * 分页查询
     * service调用pageHelper后,mapper的返回类型被修改为分页对象PageInfo
     */
    List<SysUser> findPage();

    SysUser findByName(@Param(value="name") String name);

    List<SysUser> findPageByName(@Param(value="name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);
}