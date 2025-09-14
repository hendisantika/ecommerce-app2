package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.Category;
import id.my.hendisantika.ecommerceapp2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.49
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        // TODO Auto-generated method stub
        return categoryRepository.save(category);
    }
}
