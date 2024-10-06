package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.RecipeTag;

public interface RecipeTagRepository extends JpaRepository<RecipeTag,Long> {


}
