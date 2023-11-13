package com.github.cadedi.admin.service;

import com.github.cadedi.admin.model.SysConfig;
import com.github.cadedi.core.service.CurdService;

import java.util.List;

/**
 * 系统配置管理
 */
public interface SysConfigService extends CurdService<SysConfig> {

    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysConfig> findByLabel(String lable);
}
