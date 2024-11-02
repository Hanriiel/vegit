package vegit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByProductName (String productname);
}
