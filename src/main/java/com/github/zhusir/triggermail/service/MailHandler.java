package com.github.zhusir.triggermail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送邮件
 */
@Import({MailServiceImpl.class})
public class MailHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<String> contentList = new ArrayList<>();

    @Autowired
    MailService mailService;

    public void sendMail(Exception e){
        e.printStackTrace();
        if(null != e){
            logger.info("发送错误报告..");
            String content = getStackTrace(e);
            for (String s : contentList) {
                if(s.equals(content)){
                    return;
                }
            }
            if(contentList.size() > 10){
                contentList.remove(contentList.size()-1);
            }
            contentList.add(content);
            mailService.sendTemplateMail(e.getMessage() == null ? "错误信息" : e.getMessage(), content,"错误报告");
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取错误的堆栈信息
     * @return
     */
    public String getStackTrace(Exception e){
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);
        try {
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        }finally {
            printWriter.close();
        }
    }

}

