package int221.inegrated.controller;

import int221.inegrated.Exception.ProductException;
import int221.inegrated.models.Brand;
import int221.inegrated.models.Product;
import int221.inegrated.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductRestController {

    @Autowired
    ProductRepository productJpaRepository;

    @GetMapping("/show")
    public List<Product> allProduct(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "50") Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<Product> pageResult = productJpaRepository.findAll(pageable);
            return pageResult.getContent();
        }catch (Exception e){
            throw new ProductException("Can not show all product:" + e);
        }
    }

    @GetMapping("/show/{id}")
    public Product show(@PathVariable Long id) {
        try {
            return productJpaRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new ProductException("Can not show product: " + e);
        }
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        try {
            productJpaRepository.save(product);
        }catch (Exception e){
            throw new ProductException("Can not add product: " + e);
        }
    }

    @PutMapping("/edit/{id}")
    public void edit(@PathVariable long id, @RequestParam String productname, @RequestParam double price, @RequestParam long warranty, @RequestParam java.sql.Date menufacturrerdate
    , @RequestParam long capacity, @RequestParam String description, @RequestParam String images, @RequestParam Brand brand) {
        try {
            Product product = productJpaRepository.findById(id).orElse(null);
            product.setProductname(productname);
            product.setPrice(price);
            product.setWarranty(warranty);
            product.setMenufacturrerdate(menufacturrerdate);
            product.setCapacity(capacity);
            product.setDescription(description);
        }catch (Exception e){
            throw new ProductException("Can not edit product: " + e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        try {
            productJpaRepository.deleteById(id);
        }catch (Exception e){
            throw new ProductException("Can not delete product: " + e);
        }
    }

}