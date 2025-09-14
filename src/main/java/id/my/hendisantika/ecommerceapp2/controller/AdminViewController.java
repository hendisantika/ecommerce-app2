package id.my.hendisantika.ecommerceapp2.controller;

import id.my.hendisantika.ecommerceapp2.entity.Category;
import id.my.hendisantika.ecommerceapp2.entity.Product;
import id.my.hendisantika.ecommerceapp2.entity.User;
import id.my.hendisantika.ecommerceapp2.service.CartService;
import id.my.hendisantika.ecommerceapp2.service.CategoryService;
import id.my.hendisantika.ecommerceapp2.service.ProductService;
import id.my.hendisantika.ecommerceapp2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
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
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminViewController {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final UserService userService;

    private final CartService cartService;

    //to track which user is login right Now
    //by default call this method when any request come to this controller because of @ModelAttribut
    @ModelAttribute
    public void getUserDetails(Principal principal, Model model) {
        if (principal != null) {
            String currenLoggedInUserEmail = principal.getName();
            User currentUserDetails = userService.getUserByEmail(currenLoggedInUserEmail);
            //System.out.println("Current Logged In User is :: ADMIN Controller :: "+currentUserDetails.toString());
            model.addAttribute("currentLoggedInUserDetails", currentUserDetails);

            //for showing user cart count
            Long countCartForUser = cartService.getCounterCart(currentUserDetails.getId());
            log.info("Admin Cart Count :{}", countCartForUser);
            model.addAttribute("countCartForUser", countCartForUser);

        }
        List<Category> allActiveCategory = categoryService.findAllActiveCategory();
        model.addAttribute("allActiveCategory", allActiveCategory);
    }

    @GetMapping("/")
    public String adminIndex() {
        return "admin/admin-dashboard";
    }

    //CATEGORY-MODULE-START
    @GetMapping("/add-category")
    public String addCategory(Model model) {
        return "admin/category/category-add-form";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
        category.setCategoryImage(imageName);

        if (categoryService.existCategory(category.getCategoryName())) {
            session.setAttribute("errorMsg", "Category Name already Exists");
        } else {
            Category saveCategory = categoryService.saveCategory(category);

            if (ObjectUtils.isEmpty(saveCategory)) {
                session.setAttribute("errorMsg", "Not Saved! Internal Server Error!");
            } else {

                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category" + File.separator + file.getOriginalFilename());
                System.out.println("File save Path :" + path);

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                //set Suceesss Msg to Session
                session.setAttribute("successMsg", "Category Save Successfully.");
            }

        }
        return "redirect:/admin/category";
    }

    @GetMapping("/category")
    public String category(Model model) {
        log.info("category:WWWWWWWWW");
        List<Category> allCategories = categoryService.getAllCategories();
        log.info("category: {}", allCategories.toString());
        for (Category category : allCategories) {
            //category.getCreatedAt();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
            String format = formatter.format(category.getCreatedAt());
            model.addAttribute("formattedDateTimeCreatedAt", format);
        }

        model.addAttribute("allCategoryList", allCategories);
        return "/admin/category/category-home";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategoryForm(@PathVariable("id") long id, Model model) {
        //System.out.println("ID :"+id);
        Optional<Category> categoryObj = categoryService.findById(id);
        if (categoryObj.isPresent()) {
            Category category = categoryObj.get();
            model.addAttribute("category", category);
        } else {
            log.info("ELSEEEEE");
        }
        return "/admin/category/category-edit-form";
    }

    @PostMapping("/update-category")
    public String updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        log.info("Category for UPDATE :{}", category.toString());

        Optional<Category> categoryById = categoryService.findById(category.getId());
        log.info("Category obj{}", categoryById.toString());

        if (categoryById.isPresent()) {
            log.info("Present:");
            Category oldCategory = categoryById.get();
            log.info("Category old Obj {}", oldCategory);
            oldCategory.setCategoryName(category.getCategoryName());
            oldCategory.setIsActive(category.getIsActive());
            //oldCategory.setUpdatedAt(LocalDateTime.now());


            String imageName = file.isEmpty() ? oldCategory.getCategoryImage() : file.getOriginalFilename();
            oldCategory.setCategoryImage(imageName);

            Category updatedCategory = categoryService.saveCategory(oldCategory);

            if (!ObjectUtils.isEmpty(updatedCategory)) {
                //save File
                if (!file.isEmpty()) {
                    File saveFile = new ClassPathResource("static/img").getFile();
                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category" + File.separator + file.getOriginalFilename());
                    log.info("File Update path: {}", path);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }

                session.setAttribute("successMsg", "Category Updated Successfully");
            } else {
                session.setAttribute("errorMsg", "Something wrong on server!");
            }


            //OR
//			if(file!=null) {
//				String newImageName = file.getOriginalFilename();
//				log.info();("File name: "+newImageName);
//				oldCategory.setCategoryImage(newImageName);
//			}else {
//				String oldOriginalImg = oldCategory.getCategoryImage();
//				log.info();("File name ELSE: "+oldOriginalImg);
//				oldCategory.setCategoryImage(oldOriginalImg);
//			}
        } else {
            log.info("Not Present:");
        }

        return "redirect:/admin/category";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") long id, HttpSession session) {
        Boolean deleteCategory = categoryService.deleteCategory(id);
        if (deleteCategory) {
            session.setAttribute("successMsg", "Category Deleted Successfully");
        } else {
            session.setAttribute("errorMsg", "Server Error");
        }

        return "redirect:/admin/category";
    }

    //PRODUCT-MODULE-START
    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("allCategoryList", allCategories);
        return "/admin/product/add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file != null ? file.getOriginalFilename() : "default.png";

        product.setProductImage(imageName);
        product.setDiscount(0);
        product.setDiscountPrice(product.getProductPrice());

        Product saveProduct = productService.saveProduct(product);

        if (!ObjectUtils.isEmpty(saveProduct)) {
            File savefile = new ClassPathResource("static/img").getFile();
            Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "product_image" + File.separator + imageName);
            log.info("File save Path :{}", path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            session.setAttribute("successMsg", "Product Save Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something Wrong on server while save Product");
            //System.out.println("Something Wrong on server while save Product");
        }

        return "redirect:/admin/product-list";
    }

    @GetMapping("/product-list")
    public String productList(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "/admin/product/product-list";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") long id, HttpSession session) {
        Boolean deleteProduct = productService.deleteProduct(id);

        if (deleteProduct) {
            session.setAttribute("successMsg", "Product Deleted Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something Wrong on server while deleting Product");
        }
        return "redirect:/admin/product-list";
    }
}
