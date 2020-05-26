package www.diandian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import www.diandian.entity.UserInfo;

import java.util.List;

@Mapper    //结合spring boot和Mybatis，在配置文件添加后，能够定位到数据库
@Repository   //一般运用于dao层，用于标注数据访问组件
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAllUserInfo();

    UserInfo selectByUsername(String username);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}