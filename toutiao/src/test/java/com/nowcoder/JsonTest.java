package com.nowcoder;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.model.Ticket;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.IntPredicate;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/22 15:13
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
//        JsonParser parser = JsonParserFactory.getJsonParser();
//        String toParse = "{\"hash\":\"FnrXFiXgCmPs11khP3zeCMZfa1u8\",\"key\":\"23fbd4b938.jpg\"}";
//
//
//        Map<String, Object> result = parser.parseMap(toParse);
//        System.out.println(result.get("key").toString());
////
//        ObjectMapper mapper = new ObjectMapper();
//        Ticket ticket = new Ticket();
//        ticket.setTicket("1dasdasd");
//        ticket.setUserId(123);
//        ticket.setExpired(new Date());
//        ticket.setStatus(200);
//        ticket.setId(1);
//        String ts = mapper.writeValueAsString(ticket);
//        System.out.println(ts);
//        Ticket t = mapper.readValue(ts, Ticket.class);
//        System.out.println(t.getExpired());
//        System.out.println(ToStringBuilder.reflectionToString(t));

        Runnable runnable = () -> {
            for (int i = 1; i < 10; i++) {
                Thread thread = Thread.currentThread();
                String name = thread.getName();
                System.out.println(name + " " + i);
            }
        };
        Properties properties = new Properties();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(" key=tvalue".getBytes());
        properties.load(byteArrayInputStream);
        System.out.println(properties.getProperty("key"));
        properties.setProperty("ke y", "hh");
        System.out.println(properties.getProperty("ke y"));

//        Thread thread = new Thread(runnable);
//        Thread thread1 = new Thread(runnable);
//        Thread thread3 = new Thread(runnable);
//        thread.start();
//        thread1.start();
//        thread3.start();
    }
}
