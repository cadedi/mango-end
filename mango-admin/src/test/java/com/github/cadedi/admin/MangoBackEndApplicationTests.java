package com.github.cadedi.admin;

import com.github.cadedi.admin.dao.SysConfigMapper;
import com.github.cadedi.admin.model.SysConfig;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
@AutoConfigureMybatis
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@MybatisTest
class MangoBackEndApplicationTests {

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Test
    public void find(){
        SysConfigMapper o = sysConfigMapper;
        SysConfig sysConfig = sysConfigMapper.selectByPrimaryKey(1L);
        System.out.println(sysConfig);
    }

    @Test
    void contextLoads() {
    }

}
