package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.49
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminRestController {

    private final CategoryService categoryService;
}
