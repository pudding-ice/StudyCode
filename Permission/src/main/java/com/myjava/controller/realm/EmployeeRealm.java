package com.myjava.controller.realm;

import com.myjava.domain.Employee;
import com.myjava.domain.Permission;
import com.myjava.domain.Role;
import com.myjava.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.sonatype.guice.plexus.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class EmployeeRealm extends AuthorizingRealm {
    @Autowired
    EmployeeService employeeService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("开始认证");
        //获取表单提交的用户名
        String username = (String) token.getPrincipal();
        Employee employee = employeeService.getEmployeeByUsername(username);
        //如果为null,说明数据库中没有这个用户名,返回null会报账号错误
        if (employee == null){
            return null;
        }
        //参数 主体身份,正确凭证(密码),盐(暂时没有),realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee,
                employee.getPassword(),
                ByteSource.Util.bytes(username),
                this.getName());
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权");
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //获取用户的角色
        Set<String> roles = employeeService.getEmployeeRolesByEid(employee.getId());
        //获取用户的权限
        Set<String> permissions = employeeService.getEmployeePermissionsByEid(employee.getId());
        //判断用户是不是管理员,如果是管理员,授予所有权限
        if (employee.getAdmin()){
            permissions.add("*:*");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }


}
