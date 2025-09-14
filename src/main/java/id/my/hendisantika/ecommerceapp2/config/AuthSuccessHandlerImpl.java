package id.my.hendisantika.ecommerceapp2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.05
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //this will provides OUR ROLES
        Set<String> roles = AuthorityUtils.authorityListToSet(authorities);
        log.info("ROLES :{}", roles);
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/");
        } else {//else by default ROLE_USER//currently we have only two ROLES USER and ADMIN
            response.sendRedirect("/");
        }
    }
}
