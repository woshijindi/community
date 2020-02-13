package com.example.shequ.mapper;

import com.example.shequ.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into t_question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from t_question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from t_question")
    Integer count();

    @Select("select * from t_question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listById(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from t_question where creator = #{userId}")
    Integer countById(@Param("userId") Integer userId);

    @Select("select * from t_question where id = #{id}")
    Question getById(@Param("id") Integer id);


    @Update("update t_question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id}")
    void update(Question question);
}
