package com.github.cadedi.backup.service.impl;

import com.github.cadedi.backup.service.MysqlBackupService;
import com.github.cadedi.backup.util.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    /**
     * 备份数据库
     *
     * @param host             地址
     * @param userName         用户名
     * @param password         密码
     * @param backupFolderPath 备份路径
     * @param fileName         备份文件名
     * @param databaseName     数据库名称
     * @return
     * @throws Exception
     */
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String databaseName) throws Exception {
        return MySqlBackupRestoreUtils.backup(host,userName,password,backupFolderPath,fileName,databaseName);
    }

    /**
     * 恢复数据库
     *
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host            IP地址
     * @param userName        用户名
     * @param password        密码
     * @param databaseName    数据库名称
     * @return
     * @throws Exception
     */
    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String databaseName) throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath,host,userName,password,databaseName);
    }
}
