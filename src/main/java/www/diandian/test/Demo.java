package www.diandian.test;

import www.diandian.entity.UserInfo;
import www.diandian.util.ShiroUtilForUserinfo;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/26
 * \* Time: 9:45
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
public class Demo {
    public static void main(String[] args) {
//        UserInfo info=new
//                UserInfo(1,"weige","hasee","","");
//        ShiroUtilForUserinfo utilForUserinfo=new ShiroUtilForUserinfo();
//        UserInfo userInfo_change=utilForUserinfo.changePassword(info);
//        System.out.println(userInfo_change.toString());

        UserInfo info=new
                UserInfo(1,"weige","hasee","","");
        ShiroUtilForUserinfo utilForUserinfo=new ShiroUtilForUserinfo();
        UserInfo userInfo_change=utilForUserinfo.changePassword02(info);
        System.out.println(userInfo_change.toString());
    }
}