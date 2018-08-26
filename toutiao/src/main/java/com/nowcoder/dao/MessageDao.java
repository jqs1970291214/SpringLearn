package com.nowcoder.dao;

import com.nowcoder.model.Message;
import com.nowcoder.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/25 21:22
 */
@Repository
@Mapper
public interface MessageDao {

    String TABLE_NAME = "message";
    String INSERT_FIELDS = " from_id,to_id,content,created_date,has_read,conversation_id ";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{fromId},#{toId},#{content},#{createdDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);

    //获取会话的消息列表
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where conversation_id = #{conversationId} order by id desc limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    /*
    select id,from_id,to_id,content,created_date,has_read,conversation_id,count from
        (
           select max(id) as maxid,count(id) as count
           from message where from_id=12 or to_id=12
           group by conversation_id
        )t
    inner join message msg on t.maxid = msg.id
    order by id desc
    limit 0,100
     */
    //获取用户的会话列表
    //将count作为id传回
    @Select({"select count as id,",INSERT_FIELDS," from (select max(id) as maxid,count(id) as count\n" +
            "           from ", TABLE_NAME," where from_id=#{userId} or to_id=#{userId}  group by conversation_id\n" +
            "        )t\n"," inner join ",TABLE_NAME, " msg on t.maxid = msg.id\n" +
            "    order by created_date desc\n" +
            "    limit #{offset},#{limit}"})
    List<Message> getConversationList(@Param("userId") String userId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

}
