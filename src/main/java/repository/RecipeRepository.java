package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {


}
