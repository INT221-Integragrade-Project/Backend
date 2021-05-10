package int221.inegrated.controller;

import com.google.api.client.testing.http.HttpTesting;
import int221.inegrated.Exception.ProductException;
import int221.inegrated.file.StorageService;
import int221.inegrated.models.product;
import int221.inegrated.models.productcolor;
import int221.inegrated.repositories.ProductColorRepository;
import int221.inegrated.repositories.ProductRepository;
import org.apache.maven.plugin.lifecycle.Phase;
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

    @GetMapping("/product")
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

    @GetMapping("/product/{id}")
    public product show(@PathVariable Long id) {
        try {
            return productJpaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new ProductException("Can not show product: " + e);
        }
    }

    @PostMapping("/add")
    public void addProduct(@RequestParam("productname") String productname,
                           @RequestParam("price") double price,
                           @RequestParam("warranty") long warranty,
                           @RequestParam("menufacturrerdate") java.sql.Date menufacturrerdate,
                           @RequestParam("capacity") long capacity,
                           @RequestParam("description") String description,
                           @RequestParam("images") String images,
                           @RequestParam("brandid") long brandid,
                           @RequestParam("colorid") Long[] colorid) {
        try {
            product newproduct = new product();
            newproduct.setProductname(productname);
            newproduct.setPrice(price);
            newproduct.setWarranty(warranty);
            newproduct.setMenufacturrerdate(menufacturrerdate);
            newproduct.setCapacity(capacity);
            newproduct.setDescription(description);
            newproduct.setImages(images);
            newproduct.setBrandid(brandid);
            productJpaRepository.save(newproduct);
            System.out.println(newproduct.getProductid());
            for (int i = 0; i < colorid.length; i++) {
                productcolor npc = new productcolor();
//                Long test = Long.parseLong(colorid[i]);
                npc.setProductid(newproduct.getProductid());
                npc.setColorid(colorid[i]);
                productColorRepository.save(npc);
            }
            System.out.println(colorid);
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
            int countproductcolor = productColorRepository.countByProductid(id);
            for (int i = 0; i < countproductcolor; i++) {
                productColorRepository.deleteById(productColorRepository.findAllByProductid(id).get(0).getProductcolorid());
            }
            productJpaRepository.deleteById(id);
            System.out.println("You delete Product: " + id);
        } catch (Exception e) {
            throw new ProductException("Can not delete product: " + e);
        }
    }

}