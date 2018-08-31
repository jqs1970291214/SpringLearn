//package com.nowcoder.util;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//
//import com.fasterxml.jackson.core.JsonEncoding;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * summary
// * descripttions
// *
// * @author Junqson
// * @date 2018/8/29 20:58
// */
//public class JacksonUtil {
//
//    private JsonGenerator jsonGenerator = null;
//    private ObjectMapper objectMapper = null;
//
//    /********************** java常见数据类型转JSON ****************************/
//    /**
//     * 1.javaBean转化成json---两种方法writeObject/writeValue均可
//     * jsonGenerator依赖于ObjectMapper创建
//     */
//    @ThreadTest
//    public void javaBeanToJson() {
//
//        try {
//            System.out.println("jsonGenerator");
//            // 方法一
//            jsonGenerator.writeObject(book);
//            System.out.println();
//
//            System.out.println("ObjectMapper");
//            // 方法二
//            objectMapper.writeValue(System.out, book);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * List转化成JSON，三种方式
//     */
//    @ThreadTest
//    public void listToJson() {
//        try {
//            List<Book> list = new ArrayList<Book>();
//            Book bookOne = new Book();
//            bookOne.setAuthor("安徒生");
//            bookOne.setBookId(456);
//            bookOne.setName("安徒生童话");
//            bookOne.setPrice(55);
//            Book bookTwo = new Book();
//            bookTwo.setAuthor("安徒生");
//            bookTwo.setBookId(456);
//            bookTwo.setName("安徒生童话");
//            bookTwo.setPrice(55);
//            list.add(bookOne);
//            list.add(bookTwo);
//            // 方式一
//            System.out.println("方式一jsonGenerator");
//            jsonGenerator.writeObject(list);
//            System.out.println();
//            System.out.println("方式二ObjectMapper");
//            // 方式二
//            System.out.println(objectMapper.writeValueAsString(list));
//            // 方式三
//            System.out.println("方式三直接通过objectMapper的writeValue方法:");
//            objectMapper.writeValue(System.out, list);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * map转化成JSON,两种方式
//     */
//    @ThreadTest
//    public void mapToJSON() {
//        try {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("name", book.getName());
//            map.put("book", book);
//            Book newBook = new Book();
//            newBook.setAuthor("安徒生");
//            newBook.setBookId(456);
//            newBook.setName("安徒生童话");
//            newBook.setPrice(55);
//            map.put("newBook", newBook);
//
//            System.out.println("第一种方式jsonGenerator");
//            jsonGenerator.writeObject(map);
//            System.out.println("");
//
//            System.out.println("第二种方式objectMapper");
//            objectMapper.writeValue(System.out, map);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*********************** JSON数据类型转java数据 ********************************/
//    /**
//     * json'对象'数据转化成javaBean
//     */
//    @ThreadTest
//    public void jsonToJavaBean() {
//        String json = "{\"bookId\":\"11111\",\"author\":\"鲁迅\",\"name\":\"朝花夕拾\",\"price\":\"45\"}";
//        try {
//            Book book = objectMapper.readValue(json, Book.class);
//            System.out.println(book);
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * json'数组'数据转化为ArrayList
//     */
//    @ThreadTest
//    public void jsonToArrayList() {
//        String json = "[{\"bookId\":\"11111\",\"author\":\"鲁迅\",\"name\":\"朝花夕拾\",\"price\":\"45\"},"
//                + "{\"bookId\":\"11111\",\"author\":\"鲁迅\",\"name\":\"朝花夕拾\",\"price\":\"45\"}]";
//        try {
//            Book[] book = objectMapper.readValue(json, Book[].class);
//            for (int i = 0; i < book.length; i++) {
//                // 注意book[i]仅仅是数组，需要通过Arrays.asList()方法转为ArrayList
//                List<Book> list = Arrays.asList(book[i]);
//                System.out.println(list);
//
//            }
//
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * json转换成map
//     */
//    @ThreadTest
//    public void JsonToMap() {
//        String json = "{\"name\":\"book\",\"number\":\"12138\",\"book1\":{\"bookId\":\"11111\",\"author\":\"鲁迅\",\"name\":\"朝花夕拾\",\"price\":\"45\"},"
//                + "\"book2\":{\"bookId\":\"22222\",\"author\":\"易中天\",\"name\":\"祖先\",\"price\":\"25\"}}";
//        try {
//            Map<String, Map<String, Object>> maps = objectMapper.readValue(
//                    json, Map.class);
//            Set<String> key = maps.keySet();
//            Iterator<String> iter = key.iterator();
//            while (iter.hasNext()) {
//                String field = iter.next();
//                System.out.println(field + ":" + maps.get(field));
//            }
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//}
