package com.nowcoder.dao;

import com.nowcoder.model.Message;
import com.nowcoder.model.Ticket;
import org.apache.ibatis.annotations.*;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/25 21:22
 */
@Mapper
public interface MessageDao {

    String TABLE_NAME = "message";
    String INSERT_FIELDS = " from_id,to_id,content,created_date,has_read,conversation_id ";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{fromId},#{toId},#{content},#{createdDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where user_id = #{userId} and status = 0"})
//    Ticket selectBy(int userId);
//
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket = #{ticket}"})
//    Ticket selectByTicket(String ticket);
//
//    //只修改status，ticket，expired三个字段
//    @Update({"update ", TABLE_NAME, "set status = #{status}, ticket = #{ticket}  where ticket = #{ticket} "})
//    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);


}
