package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.entity.Category;
import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.service.CartService;
import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import id.my.hendisantika.ecommerceapp2.service.ProductService;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminViewController {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final UserService userService;

    private final CartService cartService;

    //to track which user is login right Now
    //by default call this method when any request come to this controller because of @ModelAttribut
    @ModelAttribute
    public void getUserDetails(Principal principal, Model model) {
        if (principal != null) {
            String currenLoggedInUserEmail = principal.getName();
            User currentUserDetails = userService.getUserByEmail(currenLoggedInUserEmail);
            //System.out.println("Current Logged In User is :: ADMIN Controller :: "+currentUserDetails.toString());
            model.addAttribute("currentLoggedInUserDetails", currentUserDetails);

            //for showing user cart count
            Long countCartForUser = cartService.getCounterCart(currentUserDetails.getId());
            log.info("Admin Cart Count :{}", countCartForUser);
            model.addAttribute("countCartForUser", countCartForUser);

        }
        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        model.addAttribute("allActiveCategory", allActiveCategory);

    }
}
