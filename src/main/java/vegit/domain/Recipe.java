package vegit.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private Long id;

    @Column(name="recipe_title", nullable = false)
    @NotEmpty(message = "Reseptin nimi ei voi olla tyhjä")
    @Size(min = 2, max = 50, message = "Nimen pitää olla 2-50 merkkiä pitkä")
    private String recipeTitle;

    @Column(name = "ingredients", columnDefinition = "TEXT") 
    @Size(min = 5, max = 1000, message = "Pituuden pitää olla 5-1000 merkkiä")
    private String ingredients;

    @Column(name="instructions", columnDefinition = "TEXT")
    @Size(min = 5, max = 5000, message = "Pituuden pitää olla 5-5000 merkkiä")
    private String instructions;

    @Column(name="prep_time", nullable = false)
    @NotNull(message = "Valmistusaika pitää antaa")
    private int prepTime;

    @Column(name="servings", nullable = false)
    @NotNull(message = "Annosmäärä pitää antaa")
    private int servings;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;
    
    public Recipe() {
    }

    public Recipe(String recipeTitle, String ingredients, String instructions, int prepTime, int servings) {
        this.recipeTitle = recipeTitle;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.prepTime = prepTime;
        this.servings = servings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
    
}
