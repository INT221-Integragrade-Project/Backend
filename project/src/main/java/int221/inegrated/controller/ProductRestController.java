package int221.inegrated.controller;

import int221.inegrated.Exception.ProductException;
import int221.inegrated.models.product;
import int221.inegrated.repositories.ProductColorRepository;
import int221.inegrated.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProductRestController {

    @Autowired
    ProductRepository productJpaRepository;
    @Autowired
    ProductColorRepository productColorRepository;

    @GetMapping("/show")
    public List<product> allProduct(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "50") Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<product> pageResult = productJpaRepository.findAll(pageable);
            return pageResult.getContent();
        } catch (Exception e) {
            throw new ProductException("Can not show all product:" + e);
        }
    }

    @GetMapping("/show/{id}")
    public product show(@PathVariable Long id) {
        try {
            return productJpaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new ProductException("Can not show product: " + e);
        }
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody product newproduct) {
        try {
            productJpaRepository.save(newproduct);
        } catch (Exception e) {
            throw new ProductException("Can not add product: " + e);
        }
    }


    @PutMapping("/edit/{id}")
    public void edit(@PathVariable long id,
                     @RequestParam("productname") String productname,
                     @RequestParam("price") double price,
                     @RequestParam("warranty") long warranty,
                     @RequestParam("menufacturrerdate") java.sql.Date menufacturrerdate,
                     @RequestParam("capacity") long capacity,
                     @RequestParam("description") String description,
                     @RequestParam("images") String images,
                     @RequestParam("brandid") long brandid) {
        try {
            product product = productJpaRepository.findById(id).orElse(null);
            product.setProductname(productname);
            product.setPrice(price);
            product.setWarranty(warranty);
            product.setMenufacturrerdate(menufacturrerdate);
            product.setCapacity(capacity);
            product.setDescription(description);
            product.setImages(images);
            product.setBrandid(brandid);
            productJpaRepository.save(product);
        } catch (Exception e) {
            throw new ProductException("Can not edit product: " + e);
        }
    }

    @DeleteMapping("/deleteproductid")
    public void delete(@RequestParam("deleteproductid") long id) {
        try {
            productJpaRepository.deleteById(id);
            System.out.println("You delete Product: " + id);
        } catch (
                Exception e) {
            throw new ProductException("Can not delete product: " + e);
        }
    }

}