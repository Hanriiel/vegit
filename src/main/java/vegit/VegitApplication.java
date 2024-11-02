package vegit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vegit.domain.AppUser;
import vegit.domain.Product;
import vegit.domain.Recipe;
import vegit.domain.Review;
import vegit.repository.AppUserRepository;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;
import vegit.repository.ReviewRepository;

@SpringBootApplication
public class VegitApplication {

    private static final Logger log = LoggerFactory.getLogger(VegitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VegitApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(ProductRepository productRepository, RecipeRepository recipeRepository, AppUserRepository appUserRepository, ReviewRepository reviewRepository) {
        return (args) -> {

            log.info("Käyttäjiä");
            AppUser admin = new AppUser("admin", "$2a$10$988Dcjhs1HcaUIkIXv8KdeNyKuXCKVPw550ssUsBzcRrWJf3prvYK", "ADMIN"); // admin
            AppUser user = new AppUser("user", "$2a$10$dop/CP7xDKiYNpRdUJjgTeRvRJfCyal82.m2jEENXxCr7Bo8dVXKG", "USER"); // user

            appUserRepository.save(admin);
            appUserRepository.save(user);

            log.info("Muutama testituote");
            Product product1 = new Product("Linda Mccartney's Vegetarian Red Onion & Rosemary Sausages", "Midsona", "Linda McCartney's Vegetarian Red Onion&Rosemary Sausages makkara pakaste 270g/6kpl. Jälleenmyyjä S-ryhmä.", "Uudelleen hydratoitu SOIJAproteiini (62%), punasipuli (19%), mausteet (sipulijauhe, murskattua sipulia, suola, sokeri, hiivauute, rosmariini, auringonkukkaöljy, valkopippuri, inkivääri) vesi, rypsiöljy, SOIJAproteiinikonsentraatti, Stabilointiaine: metyyliselluloosa.");
            Product product2 = new Product("Rainbow 1L Mantelijuoma", "Rainbow", "Lisätty B2-, B12- ja D2-vitamiinia ja kalsiumia. Homogenoitu ja iskukuumennettu.", "Vesi, manteli 2%, kalsiumkarbonaatti, sakeuttamisaineet (E 418, E 412), emulgointiaine (E 322 auringonkukasta), merisuola, riboflaviini, D2-vitamiini, B12-vitamiini.");
            Product product3 = new Product("Yipin tofu maustamaton", "Yipin", "YipinTofu maustamaton luomu 230g. Jälleenmyyjä K-ryhmä.", "EU:n alueella viljelty SOIJAPAPU* 70%, vesi, mineraalisuola. *Luomu");
            Product product4 = new Product("Oatly Havregurt Mansikka", "Oatly", "Kasvipohjainen jogurtti, joka sisältää mansikkaa ja sopii välipalaksi.", "Vesi, kaura (9%), mansikkasose (6%), sokeri, stabilointiaineet (pektiini, guarkumi), maitohappokulttuurit.");


            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);

            log.info("Muutama resepti testiä varten");
            Recipe recipe1 = new Recipe("Vegemakkarapelti", "2 bataattia, 1 pkt makkaraa, 1 paprika, 1 sipuli", "Kuori ja hienonna sipuli ja valkosipulinkynnet. Kuutioi paprika, pilko makkarat. Kuori ja kuutioi bataatit. Kuumenna öljy pannulla ja ruskista makkaranpalat. Siirrä syrjään odottamaan. Lisää pannulle paprikakuutiot. Paista muutama minuutti kunnes paprikat pehmenevät. Siirrä syrjään. Lisää pannulle tarvittaessa tilkka öljyä ja kuullota sipulia ja valkosipulia muutama minuutti. Lisää sitten bataattikuutiot ja paista vielä hetki.", 30, 4);
            Recipe recipe2 = new Recipe("Tofu-perunasalaatti", "2 paprikaa, 1 pkt tofua, 1 kg paistettuja perunoita, rucolaa, öljyä, suolaa", "Leikkaa paprikat kuutioiksi. Paista tofua kuumalla pannulla tilkassa öljyä pari minuuttia. Kääntele kaikki salaatin aineet yhteen ja tarjoa heti.", 15, 4);
            Recipe recipe3 = new Recipe("Mansikka-havregurt Smoothie", "1 banaani, 1 dl mansikoita, 2 dl Oatly Havregurt Mansikkaa, 1 rkl chia-siemeniä, jääpaloja", "Laita kaikki ainekset tehosekoittimeen ja sekoita tasaiseksi smoothieksi. Tarjoile kylmänä.", 10, 2);

            recipeRepository.save(recipe1);
            recipeRepository.save(recipe2);
            recipeRepository.save(recipe3);

            log.info("Arvostelut");
            Review review1 = new Review(5, "Todella herkullisia vegemakkaroita! Rosmariini sopii täydellisesti näihin.", product1, user); 
            Review review2 = new Review(4, "Tämä vegemakkarapelti oli todella ruokaisa, mutta mausteita on vähänlaisesti.", recipe1, user);
            Review review3 = new Review(4, "Hyvä mantelimaito, sopii hyvin smoothieen. Maku voisi kuitenkin olla täyteläisempi.", product2, user);
            Review review4 = new Review(5, "Ihana salaatti! Helppo valmistaa vaikka lounaaksi.", recipe2, user);
            Review review5 = new Review(4, "Raikas ja herkullinen kaurajogurtti! Mansikan maku on luonnollinen eikä liian makea.", product4, user);
            Review review6 = new Review(3, "Hyvä kasvipohjainen vaihtoehto, mutta koostumus voisi olla hieman paksumpi.", product4, user);
            Review review7 = new Review(5, "Todella helppo ja maukas smoothie! Sopii hyvin aamuun tai välipalaksi.", recipe3, user);
            Review review8 = new Review(4, "Mansikan ja kauran maku yhdistyvät ihanasti, mutta lisäsin vähän hunajaa, sillä kaipasin makeutta.", recipe3, user);
            Review review9 = new Review(4, "Yipin tofu on todella monikäyttöinen, sillä maustamattomana se sopii sekä suolaisiin että makeisiin ruokiin. Kiinteä rakenne tekee siitä helpon käsitellä, mutta marinoimalla saa parhaat maut irti.", product3, user);

            reviewRepository.save(review1);
            reviewRepository.save(review2);
            reviewRepository.save(review3);
            reviewRepository.save(review4);
            reviewRepository.save(review5);
            reviewRepository.save(review6);
            reviewRepository.save(review7);
            reviewRepository.save(review8);
            reviewRepository.save(review9);


            log.info("Feikkidata OK");
        };
    }
}
