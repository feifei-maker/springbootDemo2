package com.whc.springboot.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig <br/>
 * Description: <br/>
 * date: 2020/8/27 10:23<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Configuration
//自定义类的注解
@Component
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    /**
     * 由securityManager的配置文件（bean）转换成的实体类
     * <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
     * <property name="realm" ref="myRealm"/>
     * <property name="cacheManager" ref="cacheManager"/>
     * <property name="sessionManager" ref="sessionManager"/>
     * <property name="rememberMeManager" ref="rememberMeManager"/>
     * </bean>
     */
    @Bean
    public DefaultSecurityManager defaultSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    /**
     * 配置shiro过滤器工厂
     * -----------------
     * 拦截权限
     * anon：匿名访问，无需登录
     * authc：登录后才能访问
     * user：登录过能访问
     * logout：登出
     * roles：角色过滤器
     * ------------------
     * URL匹配风格
     * ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/
     * *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1
     * **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     * -----------------------
     * 方法名不能乱写，如果我们定义为别的名称，又没有添加注册过滤器的配置，那么shiro会加载ShiroWebFilterConfiguration过滤器，
     * 该过滤器会寻找shiroFilterFactoryBean，找不到会抛出异常
     * ******************path范围越大的，放后面***************************
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(defaultSecurityManager());
        //设置在未被认证之前都会重定向的url
        shiroFilter.setLoginUrl("/account/login");
        //设置在认证成功之后的url
        shiroFilter.setSuccessUrl("/account/dashboard");
        //设置无法认证执行的url
//        shiroFilter.setUnauthorizedUrl("error/403");
        /**
         * anon:匿名访问（不用认证就可以正常访问的url）
         * authc:认证之后才能正常访问url
         * user:如果使用rememberMe的功能可以直接访问
         * perms:必须得到资源权限才可以访问
         * role:必须得到角色权限才能访问
         */
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "anon");
        map.put("/static/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/plugin/**", "anon");
        map.put("/account/login", "anon");
        map.put("/api/login", "anon");
        map.put("/account/register", "anon");
        map.put("/api/user", "anon");
        map.put("/account/profile", "anon");
        //如果使用“记住我功能”，则采用user规则,如果必须要用户登录，则采用authc规则
        map.put("/test/**", "authc");
        map.put("/**", "user");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

    /**
     * 注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
