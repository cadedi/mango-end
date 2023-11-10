package com.github.cadedi.admin.controller;

import com.github.cadedi.admin.service.SysUserService;
import com.github.cadedi.core.http.HttpResult;
import com.github.cadedi.core.page.PageRequest;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }
}
