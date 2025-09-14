package id.my.hendisantika.ecommerceapp2.repository;

import id.my.hendisantika.ecommerceapp2.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
