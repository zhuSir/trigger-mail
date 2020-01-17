//package com.github.zhusir.triggermail.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@ConfigurationProperties(prefix = "spring.mail")
//public class MailConfigBean {
//
//    private String username;
//
//    private Config config;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Config getConfig() {
//        return config;
//    }
//
//    public void setConfig(Config config) {
//        this.config = config;
//    }
//}
//
//class Config{
//    /**
//     * 接收方邮箱号
//     */
//    List<String> toMails;
//
//    /**
//     * 发送方别名
//     */
//    String fromAlias;
//
//    /**
//     * 自定义内容tfl文件名
//     */
//    String tflName = "mail.tfl";
//
//    public List<String> getToMails() {
//        return toMails;
//    }
//
//    public void setToMails(List<String> toMails) {
//        this.toMails = toMails;
//    }
//
//    public String getFromAlias() {
//        return fromAlias;
//    }
//
//    public void setFromAlias(String fromAlias) {
//        this.fromAlias = fromAlias;
//    }
//
//    public String getTflName() {
//        return tflName;
//    }
//
//    public void setTflName(String tflName) {
//        this.tflName = tflName;
//    }
//}