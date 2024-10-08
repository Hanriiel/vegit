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
import vegit.domain.Role;
import vegit.repository.AppUserRepository;
import vegit.repository.ProductRepository;
import vegit.repository.RecipeRepository;
import vegit.repository.RoleRepository;


@SpringBootApplication
public class VegitApplication {

	private static final Logger log = LoggerFactory.getLogger(VegitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VegitApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(ProductRepository productRepository, RecipeRepository recipeRepository, AppUserRepository appUserRepository, RoleRepository roleRepository){ return (args) -> {
	
		log.info("Luodaan pari roolia");
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		roleRepository.save(userRole);
		roleRepository.save(adminRole);

		log.info("Muutama testituote");
		Product product1 = new Product("Linda Mccartney's Vegetarian Red Onion&Rosemary Sausages", "Midsona", "Linda McCartney's Vegetarian Red Onion&Rosemary Sausages makkara pakaste 270g/6kpl. Jälleenmyyjä S-ryhmä.", "Uudelleen hydratoitu SOIJAproteiini (62%), punasipuli (19%), mausteet (sipulijauhe, murskattua sipulia, suola, sokeri, hiivauute, rosmariini, auringonkukkaöljy, valkopippuri, inkivääri) vesi, rypsiöljy, SOIJAproteiinikonsentraatti, Stabilointiaine: metyyliselluloosa.");
		Product product2 = new Product("Rainbow 1L Mantelijuoma", "Rainbow", "Lisätty B2-, B12- ja D2-vitamiinia ja kalsiumia. Homogenoitu ja iskukuumennettu.", "Vesi, manteli 2%, kalsiumkarbonaatti, sakeuttamisaineet (E 418, E 412), emulgointiaine (E 322 auringonkukasta), merisuola, riboflaviini, D2-vitamiini, B12-vitamiini.");
		Product product3 = new Product("Ypin tofu maustamaton", "Yipin", "YipinTofu maustamaton luomu 230g. Jälleenmyyjä K-ryhmä.", "EU:n alueella viljelty SOIJAPAPU* 70%, vesi, mineraalisuola. *Luomu");

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		log.info("Muutama resepti testiä varten");
		Recipe recipe1 = new Recipe("Vegemakkarapelti", "2 bataattia, 1 pkt makkaraa, 1 paprika, 1 sipuli", "Kuori ja hienonna sipuli ja valkosipulinkynnet. Kuutioi paprika, pilko makkarat. Kuori ja kuutioi bataatit. Kuumenna öljy pannulla ja ruskista makkaranpalat. Siirrä syrjään odottamaan. Lisää pannulle paprikakuutiot. Paista muutama minuutti kunnes paprikat pehmenevät. Siirrä syrjään. Lisää pannulle tarvittaessa tilkka öljyä ja kuullota sipulia ja valkosipulia muutama minuutti. Lisää sitten bataattikuutiot ja paista vielä hetki.",30, 4);
		Recipe recipe2 = new Recipe("Tofu-perunasalaatti", "2 paprikaa, 1 pkt tofua, 1 kg paistettuja perunoita, ruolaa, öljyä","Leikkaa paprikat kuutioiksi. Paista tofua kuumalla pannulla tilkassa öljyä pari minuuttia. Kääntele kaikki salaatin aineet yhteen ja tarjoa heti.", 15, 4);

		recipeRepository.save(recipe1);
		recipeRepository.save(recipe2);

		log.info("Kaksi käyttäjää");
		AppUser user1 = new AppUser("user", "$2a$10$dop/CP7xDKiYNpRdUJjgTeRvRJfCyal82.m2jEENXxCr7Bo8dVXKG", userRole);
		AppUser user2 = new AppUser("admin", "$2a$10$988Dcjhs1HcaUIkIXv8KdeNyKuXCKVPw550ssUsBzcRrWJf3prvYK", adminRole);

		appUserRepository.save(user1);
		appUserRepository.save(user2);

		log.info("Feikkidata OK");
		

	};

	}
}
