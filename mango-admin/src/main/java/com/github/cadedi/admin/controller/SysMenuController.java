package com.github.cadedi.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.cadedi.admin.model.SysMenu;
import com.github.cadedi.admin.service.SysMenuService;
import com.github.cadedi.core.http.HttpResult;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysMenu record) {
		return HttpResult.ok(sysMenuService.save(record));
	}

	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysMenu> records) {
		return HttpResult.ok(sysMenuService.delete(records));
	}

	@GetMapping(value="/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(sysMenuService.findTree(userName, 1));
	}
	
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(sysMenuService.findTree(null, 0));
	}
}
