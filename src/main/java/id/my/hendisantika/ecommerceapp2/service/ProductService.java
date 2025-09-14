package id.my.hendisantika.ecommerceapp2.service;

import id.my.hendisantika.ecommerceapp2.entity.Product;
import id.my.hendisantika.ecommerceapp2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/09/25
 * Time: 05.54
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return productRepository.findAll();
    }

    public Boolean deleteProduct(long id) {
        // TODO Auto-generated method stub
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(product.get().getId());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Product> findById(long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    public Product getProductById(long id) {
        // TODO Auto-generated method stub
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProductById(Product product, MultipartFile file) {
        Product dbProductById = getProductById(product.getId());

        String imageName = file.isEmpty() ? dbProductById.getProductImage() : file.getOriginalFilename();
        dbProductById.setProductImage(imageName);
        dbProductById.setProductTitle(product.getProductTitle());
        dbProductById.setProductDescription(product.getProductDescription());
        dbProductById.setProductCategory(product.getProductCategory());
        dbProductById.setProductPrice(product.getProductPrice());
        dbProductById.setProductStock(product.getProductStock());
        dbProductById.setCreatedAt(product.getCreatedAt());
        dbProductById.setIsActive(product.getIsActive());
        //discount logic
        dbProductById.setDiscount(product.getDiscount());
        Double discount = product.getProductPrice() * (product.getDiscount() / 100.0);
        Double discountPrice = product.getProductPrice() - discount;
        dbProductById.setDiscountPrice(discountPrice);

        Product updatedProduct = productRepository.save(dbProductById);

        //product save then we need to save our new updated image
        if (!ObjectUtils.isEmpty(updatedProduct)) {
            if (!file.isEmpty()) {
                try {
                    File savefile = new ClassPathResource("static/img").getFile();
                    Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "product_image" + File.separator + file.getOriginalFilename());
                    System.out.println("File save Path :" + path);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return updatedProduct;
        }
        return null;
    }
}
