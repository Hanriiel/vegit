package vegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.RecipeProduct;

public interface RecipeProductRepository extends JpaRepository<RecipeProduct,Long> {


}
