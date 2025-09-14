package id.my.hendisantika.ecommerceapp2.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.02
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class CommonUtils {

    private final JavaMailSender mailSender;

    public Boolean sendEmail(String url, String reciepentEmail) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom("myplacementjourney@gmail.com", "RED WINGS");
        messageHelper.setTo(reciepentEmail);

        String content = "<p>Hello, </p>" + "<p> You have requested to reset your password. </p> "
                + "<p>Please Click the link to change your password:</p>" + "<p><a href=\"" + url + "\">Change my password</a></p>";

        messageHelper.setSubject("Password Reset for RED WINGS");
        messageHelper.setText(content, true);

        mailSender.send(message);

        return true;
    }

    public static String generateUrl(HttpServletRequest request) {
        String fullUrl = request.getRequestURL().toString();
        return fullUrl.replace(request.getServletPath(), "");
    }
}
