package com.example.demo.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name="getShiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        * Shiron内置过滤器
        *   常用过滤器：
        *       anon:无需认证（登录）可以访问
        *       authc:必须认证才可以访问
        *       User：如果使用rememberMe可以直接访问
        *       perms：该资源必须获得资源权限才可以访问
        *       role：该资源必须获得角色权限才可以访问
        * */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();

        filterMap.put("/publish","authc");
        filterMap.put("/articleDetail/*","authc");
        filterMap.put("/personal","authc");
        filterMap.put("/myCollection","authc");
        filterMap.put("/myRemark","authc");
        filterMap.put("/myMessage","authc");
        filterMap.put("/admin/*","authc");
        filterMap.put("/dialogue/*","authc");


        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/checkRegister","anon");
        filterMap.put("/checkLogin","anon");

        filterMap.put("/admin/*","perms[admin]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPerms");

        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userReaml")UserReaml userReaml){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userReaml);
        return securityManager;
    }

    @Bean(name = "userReaml")
    public UserReaml getReaml(){
        return new UserReaml();
    }

    /*
    * 配置ShiroDialect，用于thymeleaf和shiro配合使用
    * */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    /***
     * shiro的在进行密码验证的时候，将会在此进行匹配
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }
}
