package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.Cart;
import id.my.hendisantika.ecommerceapp2.entity.ProductOrder;
import id.my.hendisantika.ecommerceapp2.entity.ProductOrderRequest;
import id.my.hendisantika.ecommerceapp2.repository.CartRepository;
import id.my.hendisantika.ecommerceapp2.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.53
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    private final CartRepository cartRepository;

    public ProductOrder saveProductOrder(Long userId, ProductOrderRequest productOrderRequest) {
        //get which products are order by user
        List<Cart> listOfCarts = cartRepository.findByUserId(userId);
        for (Cart cart : listOfCarts) {

            ProductOrder order = new ProductOrder();

            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(new Date());

            order.setProduct(cart.getProduct());
            order.setPrice(cart.getProduct().getDiscountPrice());
            order.setQuantity(cart.getQuantity());

            order.setUser(cart.getUser());
            order.setStatus("In Progress");
        }

        return null;
    }
}
