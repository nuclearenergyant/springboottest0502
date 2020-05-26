package www.diandian.biz;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import www.diandian.entity.UserInfo;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/5/24
 * \* Time: 21:19
 * \* Description: www.diandian.在线
 * \* 功能说明：用户相关的业务方法接口
 * \
 */

@Mapper
@Repository
public interface UserBiz {

    List<UserInfo> selectAllUserInfo();

    UserInfo selectByUsername(String name);
}