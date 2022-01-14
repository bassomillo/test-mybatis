package com.bassomillo.mapper;

import com.bassomillo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * select all the users
     * @return List<User>
     */
    List<User> UserSelect();

    /**
     * select user by id
     * @param id
     * @return User
     */
    User UserSelectById(Integer id);

    User UserSelect1(@Param("username") String username, @Param("password") String password);

    User UserSelect2(User user);

    List<User> UserSelectByUsername(String name);

    Integer InsertUser(User user);

    Integer UpdateUser(User user);

    Integer DeleteUser(Integer id);

    List<User> UserSelectByUser(User user);

}
