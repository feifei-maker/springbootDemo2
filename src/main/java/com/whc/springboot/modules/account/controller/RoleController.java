package com.whc.springboot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.entity.Role;
import com.whc.springboot.modules.account.service.RoleService;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: RoleController <br/>
 * Description: <br/>
 * date: 2020/8/25 11:21<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     *127.0.0.1:81/api/roles----get
     */
    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    @PostMapping(value = "/roles", consumes = "application/json")
    public PageInfo<Role> getRoles(@RequestBody SearchVo searchVo) {
        return roleService.getRoles(searchVo);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }

    @PutMapping(value = "/role", consumes = "application/json")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }

    @RequestMapping("/role/{roleId}")
    public Role getRole(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }

    @DeleteMapping("/role/{roleId}")
    public Result<Role> deletRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }
}
