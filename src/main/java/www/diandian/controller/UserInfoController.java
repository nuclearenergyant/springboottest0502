package www.diandian.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import www.diandian.biz.impl.UserInfoBizImpl;
import www.diandian.entity.LayUITable;
import www.diandian.entity.UserInfo;
import www.diandian.util.MyConstants;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/24
 * \* Time: 20:41
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
/*@ResetController包含了@Controller和@ResponseBody*/
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoBizImpl userInfoBiz;

    @RequestMapping("/toShowUser")
    public String toShowUser(Model model){
        String name="核能蚂蚁";
        model.addAttribute("name",name);
        return "show";
    }

    @RequestMapping("/toShowUser02")
    public String toShowUser02(HttpSession session){
        String name="核能蚂蚁++session";
        session.setAttribute("name",name);
        return "show";
    }

    @RequestMapping("/toShowAllUser")
    public String toShowAllUser(Model model){
        List<UserInfo> userInfoList=userInfoBiz.selectAllUserInfo();
        model.addAttribute("userInfoList",userInfoList);
        model.addAttribute("name","玉环市");
        return "user";
    }


    @RequestMapping("/show01")
    @ResponseBody
    public LayUITable show01(int page,int limit){
        //开始分页（中间不能有其他代码） 第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        //开始查询
        List<UserInfo> list=userInfoBiz.selectAllUserInfo();
        //结束分页
        PageInfo<UserInfo> pageInfo=new PageInfo(list);

        LayUITable layUITable = new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("成功");
        layUITable.setCount(pageInfo.getTotal());
        layUITable.setData(list);
        return layUITable;
    }

    @RequestMapping("/show02")
    public Object show02(){
        return "layuitest";
    }



    /*保存用户*/
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Object saveUserInfo(UserInfo userInfo){
        int i=userInfoBiz.insertSelective(userInfo);
        Map map= new HashMap<>();
        if (i>0){
            map.put("code", MyConstants.successCode);
            map.put("massage",MyConstants.saveSuccess);
        }else{
            map.put("code",MyConstants.failCode);
            map.put("massage",MyConstants.saveFail);
        }
        return map;
    }


    /*修改用户*/
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(UserInfo userInfo){
        int i = userInfoBiz.updateByPrimaryKeySelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        return map;
    }
    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = userInfoBiz.delUserByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }

    @RequestMapping("/delUserbyid")
    @ResponseBody
    public Object delUserbyid(int id){
        System.out.println(id);
        int i = userInfoBiz.deleteByPrimaryKey(id);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }
}