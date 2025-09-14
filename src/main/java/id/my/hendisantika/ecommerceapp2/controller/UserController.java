package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.entity.Cart;
import id.my.hendisantika.ecommerceapp2.entity.Category;
import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.service.CartService;
import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * Time: 06.44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final CategoryService categoryService;

    private final UserService userService;

    private final CartService cartService;


    //to track which user is login right Now
    //by default call this method when any request come to this controller because of @ModelAttribut
    @ModelAttribute
    public void getUserDetails(Principal principal, Model model) {
        if (principal != null) {
            String currenLoggedInUserEmail = principal.getName();
            User currentUserDetails = userService.getUserByEmail(currenLoggedInUserEmail);
            //System.out.println("Current Logged In User is :: USER Controller :: "+currentUserDetails.toString());
            model.addAttribute("currentLoggedInUserDetails", currentUserDetails);

            //for showing user cart count
            Long countCartForUser = cartService.getCounterCart(currentUserDetails.getId());
            //System.out.println("User Cart Count :"+countCartForUser);
            model.addAttribute("countCartForUser", countCartForUser);
        }

        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        model.addAttribute("allActiveCategory", allActiveCategory);
    }

    @GetMapping("/")
    public String home() {
        return "user/user-home";
    }

    //ADD TO CART Module
    @GetMapping("/add-to-cart")
    String addToCart(@RequestParam Long productId, @RequestParam Long userId, HttpSession session) {
        System.out.println("INSIDE ITS");
        Cart saveCart = cartService.saveCart(productId, userId);

        //System.out.println("save Cart is :"+saveCart);
        if (ObjectUtils.isEmpty(saveCart)) {
            System.out.println("INSIDE Error");
            session.setAttribute("errorMsg", "Failed Product add to Cart");
        } else {
            session.setAttribute("successMsg", "Successfully, Product added to Cart");
        }
        System.out.println("pid" + productId + " uid:" + userId);
        return "redirect:/product/" + productId;
    }

    @GetMapping("/cart")
    String loadCartPage(Principal principal, Model model) {
        //when load cart, it is showing logged in user cart details:

        User user = getLoggedUserDetails(principal);
        List<Cart> carts = cartService.getCartsByUser(user.getId());
        model.addAttribute("carts", carts);
        if (carts.size() > 0) {
            Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
            model.addAttribute("totalOrderPrice", totalOrderPrice);
        }

        return "/user/cart";
    }

    @GetMapping("/cart-quantity-update")
    public String updateCartQuantity(@RequestParam("symbol") String symbol, @RequestParam("cartId") Long cartId) {
        System.out.println(symbol + " " + cartId);
        Boolean f = cartService.updateCartQuantity(symbol, cartId);
        return "redirect:/user/cart";
    }
}
