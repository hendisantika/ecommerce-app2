package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.repository.CartRepository;
import id.my.hendisantika.ecommerceapp2.repository.ProductRepository;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.45
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
}
