package com.nowcoder.dao;

import com.nowcoder.model.News;
import com.nowcoder.model.Ticket;
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.xml.ws.soap.MTOM;
import java.io.File;
import java.util.List;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/14 17:30
 */
@Repository
@Mapper
public interface TicketDao {
    String TABLE_NAME = "login_ticket";
    String INSERT_FIELDS = " user_id,ticket,expired,status ";
    String SELECT_FIELDS = " id,user_id,ticket,expired,status ";

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{userId},#{ticket},#{expired},#{status})"})
    int addTicket(Ticket ticket);

    //这个基本不用，ticket是惟一的，但是userId却可能查到多条
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where user_id = #{userId} and status = 0"})
    Ticket selectByUserId(int userId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket = #{ticket}"})
    Ticket selectByTicket(String ticket);

    //只修改status，ticket，expired三个字段
    @Update({"update ", TABLE_NAME, "set status = #{status}, ticket = #{ticket}  where ticket = #{ticket} "})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);


}
