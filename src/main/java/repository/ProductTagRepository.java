package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.ProductTag;

public interface ProductTagRepository extends JpaRepository<ProductTag,Long> {


}
