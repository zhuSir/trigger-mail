//package com.github.zhusir.triggermail;
//
//import com.github.zhusir.triggermail.dto.SimpleMailBean;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//class TriggerMailApplicationTests {
//
//    /**
//     * 实现如下：
//     *  全局拦截器、
//     *  错误拦截判断
//     *  相同错误容忍并过滤（缓存不同数据十条，每日一清）
//     *  邮箱发送列表
//     *  maven 包融入
//     */
//
//    @Autowired
//    JavaMailSender javaMailSender;
//
//    @Test
//    public void testSimpleMail() throws MessagingException {
//        try {
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            //邮件发送人
//            simpleMailMessage.setFrom("daw2019@sina.com");
//            //邮件接收人
//            simpleMailMessage.setTo("aizgw2012@163.com");
//            //邮件主题
//            simpleMailMessage.setSubject("xxxx");
//            //邮件内容
//            simpleMailMessage.setText("mailBean.getContent()  content//");
//            javaMailSender.send(simpleMailMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testHtml(){
//        SimpleMailBean bean = new SimpleMailBean();
//        bean.setContent("错误detail。。");
//        bean.setRecipient("aizgw2012@163.com");
//        bean.setSubject("xxxx");
//        sendTemplateMail(bean);
//    }
//
//    public void sendHTMLMail(SimpleMailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom("aizgw2012@163.com");
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//            StringBuilder sb = new StringBuilder();
//            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
//                    .append("\"<p style='color:#F00'>你是真的太棒了！</p>")
//                    .append("<p style='text-align:right'>右对齐</p>");
//            mimeMessageHelper.setText(sb.toString(), true);
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    private Configuration configuration; //freemarker
//
//    public void sendTemplateMail(SimpleMailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom(new InternetAddress("daw2019@sina.com", "鑫佳网研发部", "UTF-8"));
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("content", mailBean.getContent());
//            model.put("title", "标题Mail中使用了FreeMarker");
//            Template template = configuration.getTemplate("mail.ftl");
//            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//            mimeMessageHelper.setText(text, true);
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
