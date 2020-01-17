package com.github.zhusir.triggermail.dto;

import java.util.Arrays;
import java.util.List;

/**
 * 简单邮件
 */
public class MailMessage {

    /**
     * 发送方
     */
    private String from;

    /**
     * 发送方别名
     */
    private String fromAlias;

    /**
     * 邮件接收人
     */
    private List<String> recipient;

    public MailMessage from(String from){
        this.from = from;
        return this;
    }

    public MailMessage fromAlias(String fromAlias){
        this.fromAlias = fromAlias;
        return this;
    }

    public MailMessage recipient(String[] recipient){
        List<String> recipientList = Arrays.asList(recipient);
        this.recipient = recipientList;
        return this;
    }

    public List<String> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<String> recipient) {
        this.recipient = recipient;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromAlias() {
        return fromAlias;
    }

    public void setFromAlias(String fromAlias) {
        this.fromAlias = fromAlias;
    }
}
