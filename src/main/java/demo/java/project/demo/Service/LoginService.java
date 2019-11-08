package demo.java.project.demo.Service;

import demo.java.project.demo.Eneity.User;
import demo.java.project.demo.Mapper.dsMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itoutsource.cz10
 */
@Service
public class LoginService {

      @Autowired
      private UserMapper userMapper;

      public User findUserById(Integer id){
          return userMapper.selectById(id);
      }
}
