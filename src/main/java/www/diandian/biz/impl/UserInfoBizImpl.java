package www.diandian.biz.impl;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import www.diandian.biz.UserBiz;
import www.diandian.dao.UserInfoMapper;
import www.diandian.entity.UserInfo;
import www.diandian.util.ShiroUtilForUserinfo;

import java.util.List;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/24
 * \* Time: 21:20
 * \* Description: www.diandian.在线
 * \* 功能说明：业务方法类
 * 请求->controller->service(biz)->dao->DB(数据库、database)
 * \
 */

/*标注是业务方法类*/
@Service

@Mapper
@Repository
public class UserInfoBizImpl implements UserBiz {

    @Autowired
    private UserInfoMapper infoMapper;

    @Autowired
    private ShiroUtilForUserinfo shiroUtilForUserinfo;


    @Override
    public List<UserInfo> selectAllUserInfo() {
        return  infoMapper.selectAllUserInfo();
    }

    @Override
    public UserInfo selectByUsername(String name) {
        return infoMapper.selectByUsername(name);
    }

    @Override
    public int insertSelective(UserInfo userInfo) {
        //在插入用户数据的时候，修改用户的密码，使得用户的'明文'密码变为密文'密码'
        ShiroUtilForUserinfo shiroUtilForUserinfo=new ShiroUtilForUserinfo();
        UserInfo newUserInfobychange=shiroUtilForUserinfo.changePassword02(userInfo);
        return infoMapper.insertSelective(newUserInfobychange);
    }
}