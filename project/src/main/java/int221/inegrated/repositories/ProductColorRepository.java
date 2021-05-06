package int221.inegrated.repositories;

import int221.inegrated.models.Productcolor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<Productcolor, Long> {
    List<Productcolor> findAllByProductid(Long id);
    int countByProductid(Long id);
}
