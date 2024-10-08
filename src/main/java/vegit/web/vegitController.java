package vegit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vegit.domain.Recipe;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;

@Controller
public class VegitController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/recipes")
    public String recipes(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

       @PostMapping("/submitRecipe")
    public String submitRecipe(@RequestParam String title,
                               @RequestParam String ingredients,
                               @RequestParam String instructions,
                               @RequestParam int prepTime,
                               @RequestParam int servings) {
        Recipe newRecipe = new Recipe(title, ingredients, instructions, prepTime, servings);
        recipeRepository.save(newRecipe);
        return "redirect:/recipes"; 
    }

    @GetMapping("/reviews")
    public String reviews(){
        return "reviews";
    }




}
