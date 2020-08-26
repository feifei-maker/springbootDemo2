package com.whc.springboot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.entity.Role;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * ClassName: RoleService <br/>
 * Description: <br/>
 * date: 2020/8/25 11:19<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface RoleService {
    List<Role> getRoles();
    Result<Role> editRole(Role role);

    Result<Role> deleteRole(int roleId);

    PageInfo<Role> getRoles(SearchVo searchVo);

    List<Role> getRolesByUserId(int userId);

    List<Role> getRolesByResourceId(int resourceId);

    Role getRoleById(int roleId);
}
