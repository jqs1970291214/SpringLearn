//package com.nowcoder.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.velocity.app.VelocityEngine;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Properties;
//
///**
// * 发送邮件
// * descripttions
// *
// * @author Junqson
// * @date 2018/8/30 14:58
// */
//@Slf4j
//@Service
//public class MailSender implements InitializingBean {
//
//    private JavaMailSenderImpl javaMailSender;
//
//    @Autowired
//    private VelocityEngine velocityEngine;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setUsername("mail");
//        javaMailSender.setPassword("pass");
//        javaMailSender.setHost("smtp.mxhichina.com");
//        javaMailSender.setPort(465);
//        javaMailSender.setProtocol("smtps"); //simple mail transport protocol over ssl(secure socket layer)
//        javaMailSender.setDefaultEncoding("utf-8");
//    }
//
//
//    public boolean sendWithHTMLTemplate(String to, String subject,
//                                        String template, Map<String, Object> model) {
//
//
//        return true;
//
//    }
//}
