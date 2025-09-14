package id.my.hendisantika.ecommerceapp2.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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
}
