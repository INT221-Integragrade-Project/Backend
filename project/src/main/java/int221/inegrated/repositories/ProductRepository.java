package int221.inegrated.repositories;

import int221.inegrated.models.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<product, Long> {}
