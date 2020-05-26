package www.diandian.biz.impl;

import org.apache.catalina.mbeans.UserMBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import www.diandian.biz.UserBiz;
import www.diandian.dao.UserInfoMapper;
import www.diandian.entity.UserInfo;

import javax.xml.ws.ServiceMode;
import java.util.List;

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


    @Override
    public List<UserInfo> selectAllUserInfo() {
        return  infoMapper.selectAllUserInfo();
    }

    @Override
    public UserInfo selectByUsername(String name) {
        return infoMapper.selectByUsername(name);
    }
}