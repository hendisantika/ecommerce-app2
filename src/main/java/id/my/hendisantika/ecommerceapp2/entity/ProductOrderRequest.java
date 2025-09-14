package id.my.hendisantika.ecommerceapp2.entity;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.41
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ProductOrderRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String address;

    private String city;

    private String state;

    private String pinCode;

    private String paymentType;
}
