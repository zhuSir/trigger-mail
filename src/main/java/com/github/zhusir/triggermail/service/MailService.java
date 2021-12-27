package com.github.zhusir.triggermail.service;

/**
 * service
 */
public interface MailService {

    void sendTemplateMail(String subject,String content,String title);

    boolean judge(String content);
}
