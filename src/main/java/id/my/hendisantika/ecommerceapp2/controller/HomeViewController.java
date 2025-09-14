package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.entity.Category;
import id.my.hendisantika.ecommerceapp2.entity.Product;
import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.service.CartService;
import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import id.my.hendisantika.ecommerceapp2.service.ProductService;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import id.my.hendisantika.ecommerceapp2.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    //to track which user is login right Now
    //by default call this method when any request come to this controller because of @ModelAttribut
    @ModelAttribute
    public void getUserDetails(Principal principal, Model model) {
        if (principal != null) {
            String currenLoggedInUserEmail = principal.getName();
            User currentUserDetails = userService.getUserByEmail(currenLoggedInUserEmail);
            //System.out.println("Current Logged In User is :: HOME Controller :: "+currentUserDetails.toString());
            model.addAttribute("currentLoggedInUserDetails", currentUserDetails);

            //for showing user cart count
            Long countCartForUser = cartService.getCounterCart(currentUserDetails.getId());
            System.out.println("HomeControll Cart Count :" + countCartForUser);
            model.addAttribute("countCartForUser", countCartForUser);

        }

        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        model.addAttribute("allActiveCategory", allActiveCategory);
    }

    @GetMapping("/")
    public String homeIndex(Model model) {
        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        List<Category> latestSixActiveCategory = allActiveCategory.stream()
                .sorted((cat1, cat2) -> cat2.getId().compareTo(cat1.getId()))
                .limit(6).toList();

        List<Product> latestEightActiveProducts = productService.findAllActiveProducts("").stream()
                .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                .limit(8).toList();

        model.addAttribute("latestEightActiveProducts", latestEightActiveProducts);
        model.addAttribute("latestSixActiveCategory", latestSixActiveCategory);
        return "index.html";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/products")
    public String product(Model model, @RequestParam(name = "category", defaultValue = "") String category) {
        //System.out.println("Category="+category);

        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        List<Product> allActiveProducts = productService.findAllActiveProducts(category);
        model.addAttribute("allActiveCategory", allActiveCategory);
        model.addAttribute("allActiveProducts", allActiveProducts);
        model.addAttribute("paramValue", category);
        return "product";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable long id, Model model) {
        Product productById = productService.getProductById(id);
        model.addAttribute("product", productById);
        return "view-product";
    }
}
