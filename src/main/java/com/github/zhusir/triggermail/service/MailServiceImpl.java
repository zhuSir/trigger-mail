package com.github.zhusir.triggermail.service;

import com.github.zhusir.triggermail.dto.MailMessage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailMessage mailMessage;

    @Autowired
    private Configuration configuration;

    @Override
    public void sendTemplateMail(String subject,String content,String title) {
        try {
            MimeMessage mimeMailMessage =javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(mailMessage.getFrom(), mailMessage.getFromAlias(), "UTF-8"));
            mimeMessageHelper.setSubject(subject);
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("content",content);
            model.put("title", title);
            Template template = configuration.getTemplate("mail.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            List<String> toMails = mailMessage.getRecipient();
            toMails.forEach(mailAddress->{
                try {
                    mimeMessageHelper.setTo(mailAddress);
                    mimeMessageHelper.setText(text, true);
                    javaMailSender.send(mimeMailMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    logger.error("发送邮件失败！",e.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送邮件失败！",e.getMessage());
        }
    }


}
