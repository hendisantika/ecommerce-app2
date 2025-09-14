package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.service.CartService;
import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import id.my.hendisantika.ecommerceapp2.service.ProductService;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import id.my.hendisantika.ecommerceapp2.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.35
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeViewController {
    //without login
    private final ProductService productService;

    private final CategoryService categoryService;

    private final UserService userService;

    private final CommonUtils commonUtils;

    private final CartService cartService;

    //	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;
}
