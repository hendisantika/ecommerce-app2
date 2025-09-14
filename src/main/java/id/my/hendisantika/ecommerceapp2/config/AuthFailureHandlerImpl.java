package id.my.hendisantika.ecommerceapp2.config;

import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */

@Service
@RequiredArgsConstructor
public class AuthFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    private final UserRepository userRepository;

    private final UserService userService;
}
