package id.my.hendisantika.ecommerceapp2.config;

import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.21
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /*
     * private final UserRepository userRepository;
     *
     * @Autowired public UserDetailsServiceImpl(UserRepository userRepository) {
     * this.userRepository = userRepository; }
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username {}", username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User NOT Found for :" + username);
        }
        return new CustomUser(user);
    }
}
