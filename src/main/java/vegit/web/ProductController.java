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
import vegit.domain.Product;
import vegit.domain.Review;
import vegit.repository.ProductRepository;
import vegit.repository.ReviewRepository;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();

        // Mapataan jokaisen tuotteen arvostelut
        Map<Long, List<Review>> reviewsMap = new HashMap<>();
        for (Product product : products) { 
            List<Review> reviews = reviewRepository.findByProduct(product);
            reviewsMap.put(product.getId(), reviews);
        }

        // Lisätään modeliin
        model.addAttribute("products", products);
        model.addAttribute("reviewsMap", reviewsMap);

        return "products";
    }

    @GetMapping("/addproduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/saveproduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // validaatiovirheiden tsekki
            log.error("Validaatiovirheitä");
            return "addproduct";
        }

        productRepository.save(product);
        log.info("Lisättiin uusi tuote {}", product);
        return "redirect:/products";
    }

    @GetMapping("/editproduct/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return "Error404"; // Jos tuotetta ei löydy, palautetaan 404 error -sivu
        }
        model.addAttribute("product", product);
        return "editproduct";
    }

    @PostMapping("/saveEditedProduct")
    public String saveEditedProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // validaatiotsekki
            log.error("Validaatiovirheitä"); 
            return "editproduct";
        }
        productRepository.save(product);
        log.info("Päivitys tuotteeseen {}", product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long id) {
        log.info("Poistettiin tuote numero {}", id);
        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
