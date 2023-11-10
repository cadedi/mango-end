package com.github.cadedi.admin.controller;

import com.github.cadedi.admin.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }
}
