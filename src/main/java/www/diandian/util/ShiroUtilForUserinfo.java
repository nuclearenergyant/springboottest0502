package www.diandian.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import www.diandian.entity.UserInfo;

import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/26
 * \* Time: 21:50
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */

@Mapper
@Repository
public class ShiroUtilForUserinfo {

    /**
     * 传入一个明文返回一个加密之后的密文
     */
    public String getciphertext(String phertext){
        String ciphertext = DigestUtils.md5DigestAsHex(phertext.getBytes());
        return ciphertext;
    }

    /*
    * 加盐加密+二次加密(参考作用，在Demo.java中进行测试)
    * */
    public UserInfo changePassword(UserInfo userInfo){
        //加密的算法
        String algorithmName=MyConstants.algorithmName;
        //传入一个明文返回一个加密之后的密文(第一轮加密)
        String source=getciphertext(userInfo.getPassword());
        //加盐
        String salt= UUID.randomUUID().toString();
        int hashIterations=MyConstants.hashIterations;
        SimpleHash simpleHash= new SimpleHash(algorithmName,  source,  salt,  hashIterations);

        System.out.println("加密之后的密文："+ simpleHash);
        System.out.println("加密时用的盐："+salt);

        UserInfo userInfochangepassword=new UserInfo();
        userInfo.setId(userInfo.getId());
        userInfochangepassword.setUsername(userInfo.getUsername());
        userInfochangepassword.setPassword(simpleHash.toString());
        userInfochangepassword.setSalt(salt);
        userInfochangepassword.setPath(userInfo.getPath());

        return userInfochangepassword;
    }

    /*
     * 加盐加密+一次加密（本次实验使用的）
     * */
    public UserInfo changePassword02(UserInfo userInfo){
        //加密的算法
        String algorithmName=MyConstants.algorithmName;
        //直接获取到密码，进行加密
        String source=userInfo.getPassword();
        //加盐
        String salt= UUID.randomUUID().toString();
        int hashIterations=MyConstants.hashIterations;
        SimpleHash simpleHash= new SimpleHash(algorithmName,  source,  salt,  hashIterations);

        System.out.println("加密之后的密文："+ simpleHash);
        System.out.println("加密时用的盐："+salt);

        UserInfo userInfochangepassword=new UserInfo();
        userInfo.setId(userInfo.getId());
        userInfochangepassword.setUsername(userInfo.getUsername());
        userInfochangepassword.setPassword(simpleHash.toString());
        userInfochangepassword.setSalt(salt);
        userInfochangepassword.setPath(userInfo.getPath());

        return userInfochangepassword;
    }


}