package vegit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Product;
import vegit.domain.Recipe;
import vegit.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {


    List<Review> findByProduct(Product product);
    List<Review> findByRecipe(Recipe recipe);
    List<Review> findByComment(String comment);
    List<Review> findByProductId(Long productId);
    List<Review> findByRecipeId(Long recipeId);
    


}
