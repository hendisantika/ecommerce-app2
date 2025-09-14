package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.Cart;
import id.my.hendisantika.ecommerceapp2.entity.Product;
import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.repository.CartRepository;
import id.my.hendisantika.ecommerceapp2.repository.ProductRepository;
import id.my.hendisantika.ecommerceapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public Cart saveCart(Long productId, Long userId) {
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);

        Cart cart = null;

        if (ObjectUtils.isEmpty(cartStatus)) {
            //cart Is empty - so add product to Cart
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setTotalPrice(1 * product.getDiscountPrice());
        } else {
            //cart Is holding product. so increases the quantity of the existing product.
            cart = cartStatus;
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
        }
        Cart saveCart = cartRepository.save(cart);

        return saveCart;
    }

    public List<Cart> getCartsByUser(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        //System.out.println("CARTS :"+carts.toString());

        Double totalOrderPrice = 0.0;

        // because of my totalPrice colum is transient, so we dont get totalPrice
        // directly from database table.
        // we need to fetch it
        List<Cart> updatedCartList = new ArrayList<>();
        for (Cart cart : carts) {
            Double totalPrice = (cart.getProduct().getDiscountPrice() * cart.getQuantity());
            cart.setTotalPrice(totalPrice);
            log.info("totalPrice is :{}", totalPrice);

            totalOrderPrice = totalOrderPrice + totalPrice;

            cart.setTotalOrderPrice(totalOrderPrice);
            log.info("totalOrderPrice is :{}", totalOrderPrice);
            updatedCartList.add(cart);
        }
        return updatedCartList;
    }
}
