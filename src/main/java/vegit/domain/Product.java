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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false, updatable = false)
    private Long id;
    
    @NotEmpty(message = "Tuotteen nimi ei voi olla tyhjä")
    @Size(min = 2, max = 100, message = "Pituuden pitää olla 2-100 merkkiä")
    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name="brand", nullable = false)
    @NotEmpty(message = "Tuotteen merkki ei voi olla tyhjä")
    @Size(min = 2, max = 100, message = "Pituuden pitää olla 2-100 merkkiä")
    private String brand;

    @Column(name="description")
    @Size(min = 5, max = 500, message = "Pituuden pitää olla 5-500 merkkiä")
    private String description;

    @Column(name="ingredients", columnDefinition = "TEXT")
    @Size(min = 5, max = 500, message = "Pituuden pitää olla 5-500 merkkiä")
    private String ingredients;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Product() {
    }

    public Product(String productName, String brand, String description, String ingredients) {
        this.productName = productName;
        this.brand = brand;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

 
}
