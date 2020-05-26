package www.diandian.shiro;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/25
 * \* Time: 11:00
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
@Controller
public class ShiroController {
    @RequestMapping("/toShiro")
    public String toShiro(){
        return "shiro";
    }

    @RequestMapping("/adduserInfo")
    public String adduserInfo(){
        return "adduser";
    }
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(){
        return "updateuser";
    }


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String username,String password, Model model){
        System.out.println(username+"=+="+password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            return "login";
        }

        /*进入添加、修改用户界面*/
        return "shiro";
        /*进入用户界面*/
        //return "layuitest";
    }

    @RequestMapping("/failQ")
    public String failQ(){
        return "unau";
    }
}