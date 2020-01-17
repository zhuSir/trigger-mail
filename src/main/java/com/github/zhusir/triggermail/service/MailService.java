package com.github.zhusir.triggermail.service;

public interface MailService {

    void sendTemplateMail(String subject,String content,String title);

}
