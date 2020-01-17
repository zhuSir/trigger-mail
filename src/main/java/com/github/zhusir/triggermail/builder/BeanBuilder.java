package com.github.zhusir.triggermail.builder;

import com.github.zhusir.triggermail.dto.MailMessage;
import com.github.zhusir.triggermail.service.MailServiceImpl;
import org.springframework.context.annotation.Import;

@Import({MailServiceImpl.class})
public class BeanBuilder {

    public MailMessage getMailMessage(){
        return new MailMessage();
    }

}
