package int221.inegrated.repositories;

import int221.inegrated.models.productcolor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<productcolor, Long> {
    List<productcolor> findAllByProductid(Long id);
    int countByProductid(Long id);
}
