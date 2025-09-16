package id.my.hendisantika.ecommerceapp2.util;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app2
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/09/25
 * Time: 07.40
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ImageUtils {

    /**
     * Determines if the given image path is a URL or a local path
     *
     * @param imagePath the image path to check
     * @return the appropriate path for displaying the image
     */
    public String getImagePath(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return "/img/product_image/default.jpg"; // fallback to default image
        }

        // Check if it's a URL (starts with http:// or https://)
        if (isUrl(imagePath)) {
            return imagePath; // Return the URL as-is
        } else {
            // It's a local image, prepend the local path
            return "/img/product_image/" + imagePath;
        }
    }

    /**
     * Gets the image path specifically for category images
     *
     * @param imagePath the category image path to check
     * @return the appropriate path for displaying the category image
     */
    public String getCategoryImagePath(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return "/img/category_image/default.jpg"; // fallback to default image
        }

        // Check if it's a URL (starts with http:// or https://)
        if (isUrl(imagePath)) {
            return imagePath; // Return the URL as-is
        } else {
            // It's a local image, prepend the local path
            return "/img/category_image/" + imagePath;
        }
    }

    /**
     * Checks if the given path is a URL
     *
     * @param path the path to check
     * @return true if it's a URL, false otherwise
     */
    private boolean isUrl(String path) {
        return path != null && (path.startsWith("http://") || path.startsWith("https://"));
    }

    /**
     * Gets the image path for product images with fallback
     *
     * @param imagePath the product image path
     * @return the appropriate image path
     */
    public String getProductImagePath(String imagePath) {
        return getImagePath(imagePath);
    }
}