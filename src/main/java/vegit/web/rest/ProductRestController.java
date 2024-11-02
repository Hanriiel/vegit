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
import vegit.domain.Product;
import vegit.exception.ResourceNotFoundException;
import vegit.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tuotetta ei löydy id:llä " + id));
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
        @PathVariable Long id, 
        @Valid @RequestBody Product updatedProduct) {

            Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tuotetta ei löydy id:llä " + id));
            
            product.setProductName(updatedProduct.getProductName());
            product.setBrand(updatedProduct.getBrand());
            product.setDescription(updatedProduct.getDescription());
            product.setIngredients(updatedProduct.getIngredients());
            return productRepository.save(product);
        }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tuotetta ei löydy id:llä " + id));
        
        productRepository.delete(product);
    }
}
