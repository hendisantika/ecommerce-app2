package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.57
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        log.info("user obje :{}", user.toString());
        user.setRole("ROLE_USER");
        user.setIsEnable(true);
        user.setAccountStatusNonLocked(true);
        user.setAccountFailedAttemptCount(0);
        user.setAccountLockTime(null);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            User saveUser = userRepository.save(user);

            return saveUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    public User getUserByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsersByRole(String role) {
        // TODO Auto-generated method stub
        return userRepository.findByRole(role);
    }

    public Boolean updateUserStatus(Boolean status, Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            User user = userById.get();
            user.setIsEnable(status);
            userRepository.save(user);

            return true;
        } else {
            return false;
        }

    }
}
