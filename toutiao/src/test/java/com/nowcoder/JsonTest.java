package com.nowcoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.model.Ticket;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Date;
import java.util.Map;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/22 15:13
 */
public class JsonTest {
    public static void main(String[] args) throws JsonProcessingException {
        JsonParser parser = JsonParserFactory.getJsonParser();
        String toParse = "{\"hash\":\"FnrXFiXgCmPs11khP3zeCMZfa1u8\",\"key\":\"23fbd4b938.jpg\"}";


        Map<String, Object> result = parser.parseMap(toParse);
        System.out.println(result.get("key").toString());

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = new Ticket();
        ticket.setTicket("1dasdasd");
        ticket.setUserId(123);
        ticket.setExpired(new Date());
        ticket.setStatus(200);
        ticket.setId(1);
        String ts = mapper.writeValueAsString(ticket);
        System.out.println(ts);

    }
}
