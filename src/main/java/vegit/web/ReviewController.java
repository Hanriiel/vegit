package vegit.web;

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
import vegit.domain.AppUser;
import vegit.domain.Review;
import vegit.repository.AppUserRepository;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;
import vegit.repository.ReviewRepository;
import java.security.Principal;

@Controller
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;
    private final ReviewRepository reviewRepository;
    private final AppUserRepository appUserRepository; 

    public ReviewController(ProductRepository productRepository, RecipeRepository recipeRepository, ReviewRepository reviewRepository, AppUserRepository appUserRepository) {
        this.productRepository = productRepository;
        this.recipeRepository = recipeRepository;
        this.reviewRepository = reviewRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/addrecipereview/{recipeId}")
    public String showAddRecipeReviewForm(@PathVariable Long recipeId, Model model, Principal principal) {
        // Luodaan arvostelu lomakkeeseen sidottavaksi
        Review review = new Review();
        model.addAttribute("review", review);
        model.addAttribute("recipeId", recipeId); // Reseptin id modeliin

        String username = principal.getName(); 
        model.addAttribute("username", username); // lisätään käyttäjän nimi modeliin

        return "addrecipereview"; 
    }

    @GetMapping("/addproductreview/{productId}")
    public String showAddProductReviewForm(@PathVariable Long productId, Model model, Principal principal) {
        // Luodaan arvostelu lomakkeeseen sidottavaksi
        Review review = new Review();
        model.addAttribute("review", review);
        model.addAttribute("productId", productId); // Tuotteen id modeliin

        String username = principal.getName(); 
        model.addAttribute("username", username); // lisätään käyttäjän nimi modeliin

        return "addproductreview"; 
    }


    @PostMapping("/submitrecipereview/{recipeId}")
    public String submitRecipeReview(@PathVariable Long recipeId, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Lomakkeella on virheitä.");
            return "addrecipereview"; 
        }

        // Resepti
        review.setRecipe(recipeRepository.findById(recipeId).orElse(null));

        // Arvostelun tekijä esiin Principalia käyttäen
        AppUser currentUser = appUserRepository.findByUsername(principal.getName());
        review.setAppUser(currentUser); 

        reviewRepository.save(review);
        log.info("Uusi reseptin arvostelu: {}", review);
        return "redirect:/recipes"; 
    }

    @PostMapping("/submitproductreview/{productId}")
    public String submitProductReview(@PathVariable Long productId, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Lomakkeella on virheitä.");
            return "addproductreview"; 
        }

        // Tuote
        review.setProduct(productRepository.findById(productId).orElse(null));

         // Arvostelun tekijä esiin Principalia käyttäen
         AppUser currentUser = appUserRepository.findByUsername(principal.getName());
         review.setAppUser(currentUser); 

        reviewRepository.save(review);
        log.info("Uusi tuotteen arvostelu: {}", review);
        return "redirect:/products"; 
    }

    @RequestMapping(value = "/deleterecipereview/{id}", method = RequestMethod.GET)
    public String deleteRecipeReview(@PathVariable("id") Long id) {
        log.info("Poistetaan reseptin arvostelu id:llä {}", id);
        reviewRepository.deleteById(id);
        return "redirect:/recipes";
    }

    @RequestMapping(value = "/deleteproductreview/{id}", method = RequestMethod.GET)
    public String deleteProductReview(@PathVariable("id") Long id) {
        log.info("Poistetaan tuotteen arvostelu id:llä {}", id);
        reviewRepository.deleteById(id);
        return "redirect:/products";
    }

}
