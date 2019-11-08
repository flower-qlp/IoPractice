package demo.java.project.demo.Mapper.dsMapper;

import demo.java.project.demo.Eneity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author itoutsource.cz10
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * @param userId
     * @return
     * 根据ID查找用户详情
     * **/
    @Select("select id,user_code as userCode,user_name as userName,pass_word as passWord from usr where enable=1 ")
    User selectById(@Param("userId")Integer userId);


    List<User>selectAll();
}
