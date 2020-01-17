package com.github.zhusir.triggermail.interceptor;

import com.github.zhusir.triggermail.service.MailService;
import com.github.zhusir.triggermail.service.MailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 异常处理器
 * 全局拦截并输出
 */
@RestControllerAdvice
@Import({MailServiceImpl.class})
public class MailExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<String> contentList = new ArrayList<>();

    @Autowired
    MailService mailService;

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e){
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

