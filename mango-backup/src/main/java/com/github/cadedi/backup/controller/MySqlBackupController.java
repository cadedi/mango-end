package com.github.cadedi.backup.controller;

import com.github.cadedi.backup.constants.BackupConstants;
import com.github.cadedi.backup.dataSource.BackupDataSourceProperties;
import com.github.cadedi.backup.service.MysqlBackupService;
import com.github.cadedi.backup.util.HttpResult;
import com.github.cadedi.common.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统数据库备份,恢复接口
 */
@RestController
@RequestMapping("/backup")
public class MySqlBackupController {

    @Autowired
    MysqlBackupService mysqlBackupService;
    @Autowired
    BackupDataSourceProperties properties;

    /**
     * 备份
     *
     * @return
     */
    @GetMapping("/backup")
    public HttpResult backup() {
        String backupFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_"
                + (new SimpleDateFormat(BackupConstants.DATE_FORMAT).format(new Date()));
        return backup(backupFolderName);

    }

    private HttpResult backup(String backupFolderName) {

        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFolderName + File.separator;
        String fileName = BackupConstants.BACKUP_FILE_NAME;
        try {
            boolean success = mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
            if (!success) {
                return HttpResult.error("数据备份失败");
            }
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }


    /**
     * 恢复备份
     *
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name) throws IOException {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
        try {
            mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

    /**
     * 备份展示
     *
     * @return
     */
    @GetMapping("/findRecords")
    public HttpResult findBackupRecords() {
        if (!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
            //初始默认备份文件
            backup(BackupConstants.DEFAULT_BACKUP_NAME);
        }
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("name", file.getName());
                backup.put("title", file.getName());

                if (BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
                    backup.put("title", "系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        //排序,默认备份最前,其余按时间戳排序,新备份在前面
        backupRecords.sort((o1, o2) ->
                BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ?
                        -1 :
                        BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name")) ?
                                1 :
                                o2.get("name").compareTo(o1.get("name"))
        );
        return HttpResult.ok(backupRecords);
    }

    /**
     * 删除备份
     * @param name
     * @return
     */
    @GetMapping("/delete")
    public HttpResult deleteBackupRecord(@RequestParam String name) {
        if (BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {
            return HttpResult.error("系统默认备份无法删除!");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
        try {
            FileUtils.deleteFile(new File((restoreFilePath)));
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }
}
