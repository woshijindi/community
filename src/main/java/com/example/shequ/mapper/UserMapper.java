package com.example.shequ.mapper;

import com.example.shequ.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into t_user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from t_user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from t_user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from t_user where account_id = #{accountId}")
    User findByAccounTId(@Param("accountId") String accountId);

    @Update("update t_user set token =#{token},name=#{name},avatar_url=#{avatarUrl},gmt_modified=#{gmtModified}")
    void update(User dbUser);
}
