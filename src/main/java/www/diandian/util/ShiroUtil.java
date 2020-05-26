package www.diandian.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/26
 * \* Time: 0:14
 * \* Description: www.diandian.在线
 * \* 功能说明：加密功能
 * \
 */
public class ShiroUtil {
    /**
     * 传入一个明文返回一个加密之后的密文
     */
    public static  String getciphertext(String phertext){
        String ciphertext = DigestUtils.md5DigestAsHex(phertext.getBytes());
        return ciphertext;
    }

    public static void main(String[] args) {
       /* String s = getciphertext("I miss you");
        System.out.println(s);*/
        //shiro加密
        //String algorithmName, Object source, Object salt, int hashIterations
        String algorithmName="MD5";
        String source="I love you";
        String salt= UUID.randomUUID().toString();
        int hashIterations=1000;
        SimpleHash simpleHash= new SimpleHash(algorithmName,  source,  salt,  hashIterations);
        System.out.println("加密之后的密文："+ simpleHash);
        System.out.println("加密时用的盐："+salt);
    }
}
