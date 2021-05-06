package int221.inegrated.controller;

import int221.inegrated.Exception.ProductException;
import int221.inegrated.models.Productcolor;
import int221.inegrated.repositories.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductColorRestController {

    @Autowired
    ProductColorRepository productColorRepository;

    @GetMapping("/showproductcolor")
    public List<Productcolor> allProductcolor(@RequestParam(defaultValue = "0") Integer pageNo,
                                              @RequestParam(defaultValue = "50") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Productcolor> pageResult = productColorRepository.findAll(pageable);
        return pageResult.getContent();
    }

    @GetMapping("/showproductcolor/{id}")
    public Productcolor showProductcolor(@PathVariable Long id) {
        return productColorRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/deletePodCol")
    public void deleteProductColor(@RequestParam("deletePodCol") long id) {
        int countproductcolor = productColorRepository.countByProductid(id);
        for (int i = 0; i < countproductcolor; i++) {
            productColorRepository.deleteById(productColorRepository.findAllByProductid(id).get(0).getProductcolorid());

        }
    }
}