package com.example.demo.config;

import com.example.demo.pojo.User;
import com.example.demo.service.UserSerivice;
import com.example.demo.util.ShiroEncryption;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserReaml extends AuthorizingRealm {
    /*
    * 执行授权逻辑
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if (user.getIsAdmin()==1){
            info.addStringPermission("admin");
        }
        return info;
    }

    @Autowired
    private UserSerivice userService;
    /*
    * 执行认证逻辑
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //获取数据库的数据

        //编写shiro判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userService.findById(token.getUsername());
        //输入的密码
        String oringnPassword = new String((char[]) token.getCredentials());
        String salt = user.getSalt();
        //加密后的密码
        String encodedPassword = ShiroEncryption.shiroEncryption(oringnPassword,salt);

        if (user == null){
            //用户名不存在
            //return null;//shiro底层会抛出UnKonwAccontException
            throw new AccountException("用户名不正确");
        } else if (!(userService.findById(token.getUsername()).getUserPassword()).equals(encodedPassword)) {
            throw new AccountException("密码不正确");
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,oringnPassword, ByteSource.Util.bytes(salt) ,"");
    }
}
