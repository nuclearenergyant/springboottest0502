package www.diandian.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/25
 * \* Time: 10:48
 * \* Description: www.diandian.在线
 * \* 功能说明：shiro安全框架的配置类，相当于之前的shiro.xml
 * \
 */
//标识符,相当于之前的xml
@Configuration
public class ShiroConfig {


    /*realm*/
    @Bean
    public UserInfoRealm userInfoRealm(){
        return new UserInfoRealm();
    }

    /*securityManager*/
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(userInfoRealm());
        return manager;
    }


    /*使用认证过滤器
     * 过滤器的分类
     * anon:无需认证
     * authc:必须认证
     * user:使用rememberme的时候用
     * perms:访问的资源需要某个权限才能到达
     * roles:访问的资源需要某个角色才能到达
     * */


    /*shiroFilterFactorybean
    * shiro安全过滤器
    * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());

        //过滤器链
        Map<String,String> map=new LinkedHashMap<>();   //有序的
        //放行login.html
        map.put("/login","anon");
        //添加页面需要有添加的权限才能到达
        map.put("/adduserInfo","perms[/adduserInfo]");
        map.put("/updateUserInfo","perms[/updateUserInfo]");
        //所有的请求都需要验证
        map.put("/*","authc");  //为了其他实验，先暂时修改
        //map.put("/*","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //修改登录页面，所有的未认证的请求，都要返回tologin,从而继续登录的动作
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //指定未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/failQ");
        return shiroFilterFactoryBean;
    }


    /**
     * 设置shiro的方言
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}