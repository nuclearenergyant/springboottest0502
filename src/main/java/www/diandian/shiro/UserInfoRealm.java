package www.diandian.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import www.diandian.biz.UserBiz;
import www.diandian.biz.impl.UserInfoBizImpl;
import www.diandian.entity.UserInfo;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/25
 * \* Time: 10:40
 * \* Description: www.diandian.在线
 * \* 功能说明：shiro桥梁
 * \
 */

public class UserInfoRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoBizImpl userBiz;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权开始了");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户信息,前提是在认证的时候将用户信息放入到Principal中
        //权限的字符串需要从数据库中获取
        UserInfo userInfo=(UserInfo) SecurityUtils.getSubject().getPrincipal();
        String perms=userInfo.getPath();
        System.out.println(userInfo.getUsername()+"该用户有以下权限："+perms);
        if (perms==null){
            return null;
        }
        simpleAuthorizationInfo.addStringPermission(perms);
        //模拟授权
        //simpleAuthorizationInfo.addStringPermission("/adduserInfo");
        return simpleAuthorizationInfo;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证开始了！");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        UserInfo userInfo = userBiz.selectByUsername(usernamePasswordToken.getUsername());
        //System.out.println("userinfo:"+userInfo.getUsername()+"+"+userInfo.getPassword());
        //认证失败
        if(userInfo==null){
            return null;
        }
        //Object principal, Object credentials, String realmName
        //第二个参数是密码，数据库中的密码
        String sqlpassword=userInfo.getPassword();
        //第一个参数一旦放进对象，将被保存，可以用于授权处理
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(userInfo,sqlpassword,"");
        return simpleAuthenticationInfo;
    }

}