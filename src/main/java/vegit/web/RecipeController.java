package vegit.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import vegit.domain.Recipe;
import vegit.domain.Review;
import vegit.repository.RecipeRepository;
import vegit.repository.ReviewRepository;

@Controller
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeRepository recipeRepository;
    private final ReviewRepository reviewRepository;

    public RecipeController(RecipeRepository recipeRepository, ReviewRepository reviewRepository) {
        this.recipeRepository = recipeRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/recipes")
    public String recipes(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();

        // Mapataan kaikki arvostelut
        Map<Long, List<Review>> reviewsMap = new HashMap<>();
        for (Recipe recipe : recipes) {
            List<Review> reviews = reviewRepository.findByRecipe(recipe);
            reviewsMap.put(recipe.getId(), reviews);
        }

        // Lisätään modeliin
        model.addAttribute("recipes", recipes);
        model.addAttribute("reviewsMap", reviewsMap);
        return "recipes";
    }

    @GetMapping("/addrecipe")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "addrecipe";
    }

    @PostMapping("/saverecipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validaatiovirheitä");
            return "addrecipe";
        }

        recipeRepository.save(recipe);
        log.info("Lisättiin uusi resepti {}", recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/editrecipe/{id}")
    public String editRecipe(@PathVariable("id") Long id, Model model) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            return "Error404"; 
        }
        model.addAttribute("recipe", recipe);
        return "editrecipe";
    }

    @PostMapping("/saveEditedRecipe")
    public String saveEditedRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validaatiovirheitä");
            return "editrecipe";
        }
        recipeRepository.save(recipe);
        log.info("Päivitettiin resepti: {}", recipe);
        return "redirect:/recipes";
    }

    @RequestMapping(value = "/deleterecipe/{id}", method = RequestMethod.GET)
    public String deleteRecipe(@PathVariable("id") Long id) {
        log.info("Poistettiin resepti {}", id);
        recipeRepository.deleteById(id);
        return "redirect:/recipes";
    }

    
}
