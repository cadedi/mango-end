package com.github.cadedi;

import com.github.cadedi.config.MybatisConfig;
import com.github.cadedi.config.SwaggerConfig;
import com.github.cadedi.dao.SysConfigMapper;
import com.github.cadedi.model.SysConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * springboot单元测试: 对Mybatis Mapper测试,测试类加载环境有以下几种写法
 * 1.   @SpringBootTest(classes = { MybatisConfig.class })  //加载Mybatis配置类(自定义)
 *      @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //不使用内嵌数据库
 *
 * 2.   @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *      @ContextConfiguration(classes = { MybatisConfig.class })  //加载Mybatis配置类(自定义)
 *      @MybatisTest    //自动配置 MyBatis 必要环境
 *
 * 3.   @Import({ MybatisConfig.class}) //加载Mybatis配置类,也可以直接加载指定的bean(直接在测试类中使用@bean定义bean也可)
 *      @ContextConfiguration(classes = { MybatisConfig.class })
 *      @MybatisTest
 *
 *
 * 3.   @SpringBootTest()   //加载整个spring环境上下文,包括所有的bean定义、配置和组件
 *      @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *
 * 4.   //同方法1,仅说明properties属性的用法
 *      //SpringBootTest注解中的properties属性可以指定properties(优先级比main中配置文件高)或配置文件
 *      @SpringBootTest(properties = {
 *              "spring.datasource.url=jdbc:mysql://localhost:3306/mango",
 *              "spring.datasource.username=root",
 *              "spring.datasource.password=123456"
 *          },classes = { MybatisConfig.class, SwaggerConfig.class })
 *      @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *
 * 5.   @ExtendWith(SpringExtension.class)  //加载Spring单测上下文(main中的上下文不会加载)
 *      @Import({ MybatisConfig.class}) //加载指定配置类,或使用ContextConfiguration
 *      @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *      @MybatisTest
 *
 * ps: @MybatisTest不能同时与其它Test注解直接使用,需要使用@AutoConfigureMybatis 代替
 */
@SpringBootTest(classes = { MybatisConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MybatisTests {
    @Resource
    private SysConfigMapper sysConfigMapper;

    @Test
    public void find(){
        SysConfigMapper o = sysConfigMapper;
        SysConfig sysConfig = sysConfigMapper.selectByPrimaryKey(1L);
        System.out.println(sysConfig);

    }
}
