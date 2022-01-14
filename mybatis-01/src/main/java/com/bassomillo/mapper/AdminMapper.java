package com.bassomillo.mapper;

import com.bassomillo.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminMapper {
    /**
     * 保存管理员
     * @param admin
     * @return
     */
    @Insert("insert into admin (username,password) values (#{username},#{password})")
    int saveAdmin(Admin admin);
    @Update("update admin set username=#{username} , password=#{password} where id = #{id}")
    int updateAdmin(Admin admin);
    /**
     * 删除管理员
     * @param id
     * @return
     */
    @Delete("delete from admin where id=#{id}")
    int deleteAdmin(int id);
/**
 * 根据id查找管理员
 * @param id
 * @return
 */
    @Select("select id,username,password from admin where id=#{id}")
    Admin findAdminById(@Param("id") int id);
/**
 * 查询所有的管理员
 * @return
 */
    @Select("select id,username,password from admin")
    List<Admin> findAllAdmins();
}
