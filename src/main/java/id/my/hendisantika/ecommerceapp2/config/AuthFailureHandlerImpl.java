package id.my.hendisantika.ecommerceapp2.config;

import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import id.my.hendisantika.ecommerceapp2.util.AppConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    @Lazy
    private final UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String email = request.getParameter("username");
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            if (user.getIsEnable()) {
                //account is active

                if (user.getAccountStatusNonLocked()) {
                    //Non-locked / Unlocked
                    if (user.getAccountFailedAttemptCount() < AppConstant.ATTEMPT_COUNT) {
                        userService.userFailedAttemptIncrease(user);
                    } else {
                        //
                        userService.userAccountLock(user);
                        exception = new LockedException("Your account is Locked! Failed Attempt 3");
                    }

                } else {
                    //Locked
                    if (userService.isUnlockAccountTimeExpired(user)) {
                        exception = new LockedException("Your account is UnLocked, Now you can login to system");
                    } else {
                        exception = new LockedException("Your account is Locked! Please try after sometimes");
                    }
                }

            } else {
                //account is inactive
                exception = new LockedException("Your account is inactive");
            }
        } else {
            exception = new LockedException("Email & Password Invalid!");
        }

        super.setDefaultFailureUrl("/signin?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
