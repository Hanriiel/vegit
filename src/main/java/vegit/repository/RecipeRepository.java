package vegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Recipe;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    List<Recipe> findByRecipeTitle (String recipeTitle);

}
