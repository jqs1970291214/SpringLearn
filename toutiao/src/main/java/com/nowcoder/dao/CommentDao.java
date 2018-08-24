package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/24 22:19
 */
@Mapper
public interface CommentDao {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = " content,user_id,entity_id,entity_type,created_date,status ";
    String SELECT_FIELDS = " id,content,user_id,entity_id,entity_type,created_date,status ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{content},#{userId},#{entityId},#{entityType},#{createdDate},#{status})"})
    int addComment(Comment comment);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_id = #{entityId} and entity_type = #{entityType} order by id desc"})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int type);

    @Select({"select count(id) from ", TABLE_NAME, " where entity_id = #{entityId} and entity_type = #{entityType} order by id desc"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int type);


    //
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name = #{name}"})
//    User selectByName(String name);
//
//    @Update({"update ", TABLE_NAME, " set password = #{password} where id = #{id}"})
//    void updatePassword(User user);
//
//    //企业里面一般不会删除，而是使用update修改flag
//    @Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
//    void deleteById(int id);
}
