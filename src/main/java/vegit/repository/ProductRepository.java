package vegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
