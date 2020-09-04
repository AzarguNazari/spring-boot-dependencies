package mailExample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("ityouknow@126.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! how are you doing!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("ityouknow@126.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\tmp\\application.log";
        mailService.sendAttachmentsMail("ityouknow@126.com", "about some topic", "here is the content！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>hello：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";

        mailService.sendInlineResourceMail("ityouknow@126.com", "About somet topic", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("ityouknow@126.com","about somet topic",emailContent);
    }
}
