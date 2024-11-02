package vegit.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import vegit.DTO.ReviewDTO;
import vegit.domain.AppUser;
import vegit.domain.Review;
import vegit.exception.ResourceNotFoundException;
import vegit.repository.AppUserRepository;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;
import vegit.repository.ReviewRepository;
import java.security.Principal;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
    return reviewRepository.findAll().stream()
        .map(review -> new ReviewDTO(review.getId(), review.getRating(), review.getComment(), 
                                     review.getProduct() != null ? review.getProduct().getId() : null,
                                     review.getRecipe() != null ? review.getRecipe().getId() : null,
                                     review.getAppUser().getUserId()))
        .collect(Collectors.toList());
}

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
    Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Arvostelua ei löydy id:llä " + id));
    ReviewDTO reviewDTO = new ReviewDTO(review.getId(), review.getRating(), review.getComment(),
            review.getProduct() != null ? review.getProduct().getId() : null,
            review.getRecipe() != null ? review.getRecipe().getId() : null,
            review.getAppUser().getUserId());
    return ResponseEntity.ok(reviewDTO);
    }

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewDTO reviewDTO, Principal principal) {

    // Mapataan DTO Review-entiteettiin
    Review review = new Review();
    review.setRating(reviewDTO.getRating());
    review.setComment(reviewDTO.getComment());
    
    if (reviewDTO.getProductId() != null) {
        review.setProduct(productRepository.findById(reviewDTO.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Tuotetta ei löydy")));
    }

    if (reviewDTO.getRecipeId() != null) {
        review.setRecipe(recipeRepository.findById(reviewDTO.getRecipeId()).orElseThrow(() -> new ResourceNotFoundException("Reseptiä ei löydy")));
    }

    AppUser appUser = appUserRepository.findById(reviewDTO.getAppUserId()).orElseThrow(() -> new ResourceNotFoundException("Käyttäjää ei löydy")); //Kaivetaan käyttäjä esiin
    review.setAppUser(appUser);

    Review savedReview = reviewRepository.save(review); //Tallennetaan arvostelu 
    return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
    if (!reviewRepository.existsById(id)) {
        throw new ResourceNotFoundException("Arvostelua ei löydy id:llä " + id);
    }
    reviewRepository.deleteById(id);
    return ResponseEntity.ok().build();
    }
}
