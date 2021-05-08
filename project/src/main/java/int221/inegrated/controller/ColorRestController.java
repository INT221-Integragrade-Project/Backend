package int221.inegrated.controller;

import int221.inegrated.models.color;
import int221.inegrated.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColorRestController {
    @Autowired
    ColorRepository colorRepository;

    @GetMapping("/showallcolor")
    public List<color> showColor(@RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "50") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<color> pageResult = colorRepository.findAll(pageable);
        return pageResult.getContent();
    }

    @GetMapping("/showcolorbyid/{id}")
    public color showColorById(@PathVariable long id) {
        return colorRepository.findById(id).orElse(null);
    }
}
