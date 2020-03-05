# trigger-mail
此项目为 错误拦截并发送错误报告小工具；
错误会进行缓存十个不同错误，在十个错误缓存之内,不会发送重复的错误；


>### maven地址:	
  https://mvnrepository.com/artifact/com.github.zhusir/trigger-mail


配置如下：

````

#邮箱配置
spring
  mail:
    host: smtp.sina.com
    #邮箱账号
    username: daw2019@sina.com
    #邮箱的授权码
    password: xxxxxxx
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          port: 465
          auth: true
          ssl:
            enable: true
            trust: smtp.sina.com
          starttls:
            enable: true
            required: true
          socketFactory:
            port: 465
        socketFactory:
          class: javax.net.ssl.SSLSocketFactory
    #邮箱发送等信息配置
    config:
      toMails: daw2019@sina.com,xxxxx@qq.com,xxxxx@163.com
      fromAlias: xxxx研发部

````
加上如下配置config就可自动发送
````

import com.github.zhusir.triggermail.builder.BeanBuilder;
import com.github.zhusir.triggermail.dto.MailMessage;
import com.github.zhusir.triggermail.service.MailHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 错误拦截，邮件通知
 */
@Configuration
@Import({MailHandler.class})
public class MailConfig {

    @Value("${spring.mail.username}")
    public String from;

    @Value("${spring.mail.config.fromAlias: xxxx研发部}")
    public String alias;

    @Value("#{'${spring.mail.config.toMails}'.split(',')}")
    public String[] mails;

    @Bean
    public BeanBuilder getBeanBuilder(){
        return new BeanBuilder();
    }

    @Bean
    public MailMessage initBean(){
        MailMessage message = getBeanBuilder()
                .getMailMessage().from(from)
                .fromAlias(alias).recipient(mails);
        return message;
    }
}

````


