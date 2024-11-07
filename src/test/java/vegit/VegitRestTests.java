package vegit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vegit.domain.Product;
import vegit.domain.Recipe;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;

@SpringBootTest
@ActiveProfiles("test")
public class VegitRestTests {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void getAllProductsStatusOk() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllProductsResponseTypeJson() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductByIdStatusOk() throws Exception {
        Product product = productRepository.save(new Product("Testituote", "Testimerkki", "Testikuvaus", "Ainekset"));
        
        mockMvc.perform(get("/api/products/" + product.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllRecipesStatusOk() throws Exception {
        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllRecipesResponseTypeJson() throws Exception {
        mockMvc.perform(get("/api/recipes"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getRecipeByIdStatusOk() throws Exception {
        Recipe recipe = recipeRepository.save(new Recipe("Testiresepti", "Testiainekset", "Testiohjeet", 15, 2));
        
        mockMvc.perform(get("/api/recipes/" + recipe.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllReviewsStatusOk() throws Exception {
        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllReviewsResponseTypeJson() throws Exception {
        mockMvc.perform(get("/api/reviews"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getReviewByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/reviews/99999")) 
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmptyProductList() throws Exception {
    productRepository.deleteAll(); // Tyhjennetään kaikki tuotteet

    mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[]")); // Takkistetaan, että tuotteita ei ole
    }

    @Test
    public void getProductByInvalidIdNotFound() throws Exception {
    mockMvc.perform(get("/api/products/99999"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getRecipeByInvalidIdNotFound() throws Exception {
    mockMvc.perform(get("/api/recipes/99999"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getProductByIdContentCheck() throws Exception {
    Product product = productRepository.save(new Product("Testituote", "Testimerkki", "Testikuvaus", "Ainekset"));
    
    mockMvc.perform(get("/api/products/" + product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"productName\":\"Testituote\",\"brand\":\"Testimerkki\",\"description\":\"Testikuvaus\"}"));
    }

    @Test
    public void getRecipeByIdContentCheck() throws Exception {
    Recipe recipe = recipeRepository.save(new Recipe("Testiresepti", "Testiainekset", "Testiohjeet", 15, 2));
    
    mockMvc.perform(get("/api/recipes/" + recipe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"recipeTitle\":\"Testiresepti\",\"ingredients\":\"Testiainekset\",\"instructions\":\"Testiohjeet\"}"));
    }   

}
