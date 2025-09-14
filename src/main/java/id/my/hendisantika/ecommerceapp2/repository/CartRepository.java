package id.my.hendisantika.ecommerceapp2.repository;

import id.my.hendisantika.ecommerceapp2.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByProductIdAndUserId(Long productId, Long userId);
    //public Cart findByProductAndUser(Product product, User user);

    Long countByUserId(Long userId);

    List<Cart> findByUserId(Long userId);
}
