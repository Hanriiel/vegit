package vegit.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vegit.domain.Recipe;
import vegit.exception.ResourceNotFoundException;
import vegit.repository.RecipeRepository;

@RestController
@RequestMapping("/api/recipes")
public class RecipeRestController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reseptiä ei löydy id:llä " + id));
    }


    @PostMapping
    public Recipe createRecipe(@Valid @RequestBody Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe updatedRecipe) {
        Recipe recipe = recipeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reseptiä ei löydy id:llä " + id));

        recipe.setRecipeTitle(updatedRecipe.getRecipeTitle());
        recipe.setIngredients(updatedRecipe.getIngredients());
        recipe.setInstructions(updatedRecipe.getInstructions());
        recipe.setPrepTime(updatedRecipe.getPrepTime());
        recipe.setServings(updatedRecipe.getServings());
        return recipeRepository.save(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reseptiä ei löydy id:llä " + id);
        }
        recipeRepository.deleteById(id);
    }

    
}
