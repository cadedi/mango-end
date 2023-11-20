package com.github.cadedi.backup.service;

/**
 * MySQL命令行备份恢复服务
 */
public interface MysqlBackupService {

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
    boolean backup(String host, String userName, String password,
                   String backupFolderPath, String fileName, String databaseName) throws Exception;

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
    boolean restore(String restoreFilePath, String host, String userName, String password, String databaseName) throws Exception;
}
