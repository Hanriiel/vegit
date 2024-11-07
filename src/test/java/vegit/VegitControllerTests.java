package vegit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;

import java.util.List;

import vegit.domain.AppUser;
import vegit.domain.Product;
import vegit.domain.Recipe;
import vegit.domain.Review;
import vegit.repository.AppUserRepository;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;
import vegit.repository.ReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = VegitApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VegitControllerTests {
    
    @BeforeAll
    public static void setup() {
        Dotenv dotenv = Dotenv.load();
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String databaseUrl = dotenv.get("DATABASE_URL");

        if (dbUsername != null && dbPassword != null && databaseUrl != null) {
            System.setProperty("spring.datasource.username", dbUsername);
            System.setProperty("spring.datasource.password", dbPassword);
            System.setProperty("spring.datasource.url", databaseUrl);
        } else {
            throw new IllegalStateException("Ympäristömuuttujat eivät toimi oikein.");
        }
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppUserRepository appUserRepository;
    
    @Test
    void findByProductNameShouldReturnProduct() {

        List<Product> products = productRepository.findByProductName("Linda Mccartney Vegetarian Red Onion & Rosemary Sausages");
        
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getBrand()).isEqualTo("Midsona");
    }

    @Test
    public void createNewProductTest() {
        Product product = new Product("Testituote", "Testimerkki", "Testikuvauksen sisältö.", "Testiainesosat.");
        productRepository.save(product);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getBrand()).isEqualTo("Testimerkki");
    }

    @Test
    public void deleteProductTest() {
        List<Product> products = productRepository.findByProductName("Yipin tofu maustamaton");
        Product product = products.get(0);
        productRepository.delete(product);
        List<Product> newProducts = productRepository.findByProductName("Yipin tofu maustamaton");
        assertThat(newProducts).isEmpty();
    }

    @Test
    public void editProductTest() {
        List<Product> products = productRepository.findByProductName("Oatly Havregurt Mansikka");
        Product product = products.get(0);
        product.setBrand("Oatly Havregurt Vadelma");
        productRepository.save(product);

        Product updatedProduct = productRepository.findById(product.getId()).get();
       assertThat(updatedProduct.getBrand()).isEqualTo("Oatly Havregurt Vadelma");
    }

    @Test
    void findByRecipeTitleShouldReturnRecipeTest() {

        List<Recipe> recipes = recipeRepository.findByRecipeTitle("Vegemakkarapelti");
        
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getPrepTime()).isEqualTo(30);
    }

    @Test
    public void createNewRecipeTest() {
        Recipe recipe = new Recipe("Testiresepti", "Testiainekset", "Testiohjeet", 10,4);
        recipeRepository.save(recipe);
        assertThat(recipe.getId()).isNotNull();
        assertThat(recipe.getIngredients()).isEqualTo("Testiainekset");
    }

    @Test
    public void deleteRecipeTest() {
        List<Recipe> recipes = recipeRepository.findByRecipeTitle("Tofu-perunasalaatti");
        Recipe recipe = recipes.get(0);
        recipeRepository.delete(recipe);
        List<Recipe> newRecipes = recipeRepository.findByRecipeTitle("Tofu-perunasalaatti");
        assertThat(newRecipes).isEmpty();
    }

    @Test
    public void editRecipeTest() {
        List<Recipe> recipes = recipeRepository.findByRecipeTitle("Vegemakkarapelti");
        Recipe recipe = recipes.get(0);
        recipe.setRecipeTitle("Vegekakkarapelti");
        recipeRepository.save(recipe);

        Recipe updatedRecipe = recipeRepository.findById(recipe.getId()).get();
       assertThat(updatedRecipe.getRecipeTitle()).isEqualTo("Vegekakkarapelti");
    }

    @Test
    public void createNewRecipeReviewTest() {
        AppUser user = appUserRepository.findByUsername("user");
        Recipe recipe = recipeRepository.findByRecipeTitle("Vegekakkarapelti").get(0);

        Review review = new Review();
        review.setRating(5);
        review.setComment("Herkullinen ja helppo!");
        review.setAppUser(user);
        review.setRecipe(recipe);

        reviewRepository.save(review);
        assertThat(review.getId()).isNotNull();
        assertThat(review.getComment()).isEqualTo("Herkullinen ja helppo!");
    }

    @Test
    public void deleteRecipeReviewTest() {
        List<Review> reviews = reviewRepository.findByComment("Todella herkullisia vegemakkaroita! Rosmariini sopii täydellisesti näihin.");
        Review review = reviews.get(0);
        reviewRepository.delete(review);

        List<Review> updatedReviews = reviewRepository.findByComment("Todella herkullisia vegemakkaroita! Rosmariini sopii täydellisesti näihin.");
        assertThat(updatedReviews).isEmpty();
    }

    @Test
    public void editReviewTest() {
        List<Review> reviews = reviewRepository.findByComment("Hyvä mantelimaito, sopii hyvin smoothieen. Maku voisi kuitenkin olla täyteläisempi.");
        Review review = reviews.get(0);
        review.setComment("Hyvä mutta liian makea.");
        reviewRepository.save(review);

        Review updatedReview = reviewRepository.findById(review.getId()).get();
        assertThat(updatedReview.getComment()).isEqualTo("Hyvä mutta liian makea.");
    }

    @Transactional // tämä annotaatio "eristää" testin niin, että päästään eroon LazyInitialization Exceptionista
    @Test
    public void findByRecipeIdShouldReturnReviewTest() {
        Recipe recipe = recipeRepository.findByRecipeTitle("Vegemakkarapelti").get(0);
        List<Review> reviews = reviewRepository.findByRecipeId(recipe.getId());

        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getRecipe()).isEqualTo(recipe);
    }

    @Transactional // tämä annotaatio "eristää" testin niin, että päästään eroon LazyInitialization Exceptionista
    @Test
    public void findByProductIdShouldReturnReviewTest() {
        Product product = productRepository.findByProductName("Rainbow 1L Mantelijuoma").get(0);
        List<Review> reviews = reviewRepository.findByProductId(product.getId());

        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getProduct()).isEqualTo(product);
    }
    

}
