package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void userFailedAttemptIncrease(User user) {
        int userAttempt = user.getAccountFailedAttemptCount() + 1;
        user.setAccountFailedAttemptCount(userAttempt);
        userRepository.save(user);
    }

    public void userAccountLock(User user) {
        user.setAccountStatusNonLocked(false);
        user.setAccountLockTime(new Date());
        userRepository.save(user);
    }

    public boolean isUnlockAccountTimeExpired(User user) {
        long accountLockTime = user.getAccountLockTime().getTime();
        System.out.println("Account LockTime: " + accountLockTime);
        long accountUnlockTime = accountLockTime + AppConstant.UNLOCK_DURATION_TIME;
        System.out.println("Account Unlock Time :" + accountUnlockTime);

        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime :" + currentTime);

        if (accountUnlockTime < currentTime) {
            user.setAccountStatusNonLocked(true);
            user.setAccountFailedAttemptCount(0);
            user.setAccountLockTime(null);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public void userFailedAttempt(int userId) {
        // TODO Auto-generated method stub
    }
}
