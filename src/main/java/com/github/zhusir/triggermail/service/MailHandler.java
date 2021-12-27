package com.github.zhusir.triggermail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * send email class
 */
@Import({MailServiceImpl.class})
public class MailHandler {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MailService mailService;
    
    /**
     * Message decorator add custom error message
     */
    public static class MessageDecorator extends Exception {
        
        public static MessageDecorator getMsg(String cusMsg,Long millis, Exception e){
            return new MessageDecorator(String.format("%s, %s ",cusMsg,millis) ,e);
        }
        
        String message = "";
        
        private MessageDecorator(String message, Exception e) {
            super(e);
            this.message += message + " ";
        }
        
        @Override
        public String getMessage() {
            return this.message + super.getMessage();
        }
    }
    
    
    /**
     * send email
     * @param customMsg
     * @param exception
     * @param millis
     */
    public void sendMail(String customMsg,Exception exception,Long millis){
        if(null != exception){
            logger.info(" -->> Send error report ..");
            String content = getStackTrace(exception);
            if(mailService.judge(content)){
                //Package all for message
                exception = MessageDecorator.getMsg(customMsg,millis,exception);
                exception.printStackTrace();
                mailService.sendTemplateMail(
                        (exception.getMessage() == null || "".equals(exception.getMessage())) ? "错误信息 " : exception.getMessage(),
                        content,
                        "错误报告"
                );
                logger.error(exception.getMessage(), exception);
            }
        }
    }
    
    /**
     * Get exception stack message
     * @return
     */
    public String getStackTrace(Exception e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        }finally {
            printWriter.close();
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

