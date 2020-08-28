package com.whc.springboot.config.shiro;

import com.whc.springboot.modules.account.entity.Resource;
import com.whc.springboot.modules.account.entity.Role;
import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.account.service.ResourceService;
import com.whc.springboot.modules.account.service.RoleService;
import com.whc.springboot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: MyRealm <br/>
 * Description: <br/>
 * date: 2020/8/27 10:22<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //PrincipalCollection中数据是由SimpleAuthenticationInfo包装得到
        //授权类
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //从前端传过来的PrincipalCollection中获取用户信息
        User user = (User) principalCollection.getPrimaryPrincipal();
        //通过用户信息得到角色信息
        List<Role> roleList = user.getRoles();
        roleList.stream().forEach(role -> {
            //将角色名字封装到授权对象中
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //通过角色信息从数据库中查询相对应的资源信息
            List<Resource> resourceList = resourceService.getResourcesByRoleId(role.getRoleId());
            if (resourceList != null && !resourceList.isEmpty()) {
                resourceList.stream().forEach(resource -> {
                    //将通过资源信息得到的权限信息封装到授权对象中
                    simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                });
            }
        });
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthenticationToken：封装的是从前端传过来的数据
        //authenticationToken.getCredentials()-->password
        //authenticationToken.getPrincipal()-->userName
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            //UnknownAccountException-0->登录请求无法捕获异常
            throw new UnknownAccountException("The account do not exist.");
        } else {
            //身份认证类：SimpleAuthenticationInfo参数（用户对象，从数据库中获取的密码，盐，当前的realm名）
            //使用四个参数比使用三个参数更安全，可以防止两个用户初始化密码是一样的。
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
    }
}
