//package com.github.zhusir.triggermail.config;
//
//import com.github.zhusir.triggermail.dto.MailMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MailConfig {
//
//    @Autowired
//    MailConfigBean config;
//
//    @Bean
//    public MailMessage initBean(){
//        MailMessage message = new MailMessage();
//        message.setFrom(config.getUsername());
//        message.setFromAlias(config.getConfig().getFromAlias());
//        message.setRecipient(config.getConfig().getToMails());
//        return message;
//    }
//
//}
