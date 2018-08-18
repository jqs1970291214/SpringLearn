package com.nowcoder.dao;

import ch.qos.logback.classic.db.names.TableName;
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * author:Junqson
 * create:2018/3/28 20:34
 * des:
 */
@Repository
@Mapper
public interface UserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = " name,password,salt,head_url ";
    String SELECT_FIELDS = " id,name,password,salt,head_url ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{name},#{password},#{salt},#{headUrl})"})
    int addUer(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id = #{id}"})
    User selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name = #{name}"})
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password = #{password} where id = #{id}"})
    void updatePassword(User user);

    //企业里面一般不会删除，而是使用update修改flag
    @Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
    void deleteById(int id);
}
