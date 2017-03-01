package cc.sion.config;

import cc.sion.base.auth.shiro.IAccountService;
import cc.sion.base.auth.shiro.ShiroDbRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    private static Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
    private static Map<String, Filter> LogoutFilterMap  = new HashMap<String, Filter>();


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }


    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm shiroRealm() {
        ShiroDbRealm realm = new ShiroDbRealm();
        realm.setCredentialsMatcher(initCredentialsMatcher());
        return realm;
    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = null;

        matcher = new HashedCredentialsMatcher(IAccountService.HASH_ALGORITHM);
        matcher.setHashIterations(IAccountService.HASH_INTERATIONS);
        return matcher;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionMap.put("/**", "user");

        filterChainDefinitionMap.put("/health", "anon");//查看应用健康指标
        filterChainDefinitionMap.put("/info", "anon");//查看应用信息
        filterChainDefinitionMap.put("/env", "anon");//查看所有环境变量
        filterChainDefinitionMap.put("/metrics", "anon");//查看应用基本指标
        filterChainDefinitionMap.put("/trace", "anon");//查看基本追踪信息
        filterChainDefinitionMap.put("/dump", "anon");//打印线程栈


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");

        return shiroFilterFactoryBean;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return aasa;
    }

}
