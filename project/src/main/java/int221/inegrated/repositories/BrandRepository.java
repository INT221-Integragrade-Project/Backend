package int221.inegrated.repositories;


import int221.inegrated.models.brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<brand, Long> {
}
