package com.github.cadedi;

import com.github.cadedi.dao.SysConfigMapper;
import com.github.cadedi.model.SysConfig;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
