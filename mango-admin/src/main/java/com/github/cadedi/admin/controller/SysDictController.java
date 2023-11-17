package com.github.cadedi.admin.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.cadedi.admin.model.SysDict;
import com.github.cadedi.admin.service.SysDictService;
import com.github.cadedi.core.http.HttpResult;
import com.github.cadedi.core.page.PageRequest;



/**
 * 字典控制器
 */
@Api(tags = "SysDict")
@RestController
@RequestMapping("dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;

	@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDict record) {
		return HttpResult.ok(sysDictService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.delete(records));
	}

	@ApiOperation(value = "分页")
	@PreAuthorize("hasAuthority('sys:dict:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:dict:view')")
	@GetMapping(value="/findByLable")
	public HttpResult findByLabel(@RequestParam String label) {
		return HttpResult.ok(sysDictService.findByLabel(label));
	}
}
