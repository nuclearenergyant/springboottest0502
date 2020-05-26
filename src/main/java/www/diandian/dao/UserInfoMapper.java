package www.diandian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import www.diandian.entity.UserInfo;

import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);
    UserInfo selectByUsername(String username);
    List<UserInfo> selectAllUserInfo();

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}