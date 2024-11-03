DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS REVIEW;

CREATE TABLE app_user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    passwordhash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    ingredients TEXT
);

CREATE TABLE recipe (
    recipe_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipe_title VARCHAR(50) NOT NULL,
    ingredients TEXT,
    instructions TEXT,
    prep_time INT NOT NULL,
    servings INT NOT NULL
);

CREATE TABLE review (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    product_id BIGINT,
    recipe_id BIGINT,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);


INSERT INTO app_user (username, passwordhash, role) VALUES 
('admin', '$2a$10$988Dcjhs1HcaUIkIXv8KdeNyKuXCKVPw550ssUsBzcRrWJf3prvYK', 'ADMIN'),
('user', '$2a$10$dop/CP7xDKiYNpRdUJjgTeRvRJfCyal82.m2jEENXxCr7Bo8dVXKG', 'USER');

INSERT INTO product (product_name, brand, description, ingredients) VALUES 
('Linda Mccartney Vegetarian Red Onion & Rosemary Sausages', 'Midsona', 'Linda McCartney Vegetarian Red Onion & Rosemary Sausages makkara pakaste 270g/6kpl. Jälleenmyyjä S-ryhmä.', 'Uudelleen hydratoitu SOIJAproteiini (62%), punasipuli (19%), mausteet (sipulijauhe, murskattua sipulia, suola, sokeri, hiivauute, rosmariini, auringonkukkaöljy, valkopippuri, inkivääri) vesi, rypsiöljy, SOIJAproteiinikonsentraatti, Stabilointiaine: metyyliselluloosa.');

INSERT INTO product (product_name, brand, description, ingredients) VALUES 
('Rainbow 1L Mantelijuoma', 'Rainbow', 'Lisätty B2-, B12- ja D2-vitamiinia ja kalsiumia. Homogenoitu ja iskukuumennettu.', 'Vesi, manteli 2%, kalsiumkarbonaatti, sakeuttamisaineet (E 418, E 412), emulgointiaine (E 322 auringonkukasta), merisuola, riboflaviini, D2-vitamiini, B12-vitamiini.');

INSERT INTO product (product_name, brand, description, ingredients) VALUES 
('Yipin tofu maustamaton', 'Yipin', 'YipinTofu maustamaton luomu 230g. Jälleenmyyjä K-ryhmä.', 'EU:n alueella viljelty SOIJAPAPU* 70%, vesi, mineraalisuola. *Luomu');

INSERT INTO product (product_name, brand, description, ingredients) VALUES 
('Oatly Havregurt Mansikka', 'Oatly', 'Kasvipohjainen jogurtti, joka sisältää mansikkaa ja sopii välipalaksi.', 'Vesi, kaura (9%), mansikkasose (6%), sokeri, stabilointiaineet (pektiini, guarkumi), maitohappokulttuurit.');

INSERT INTO recipe (recipe_title, ingredients, instructions, prep_time, servings) VALUES 
('Vegemakkarapelti', '2 bataattia, 1 pkt makkaraa, 1 paprika, 1 sipuli', 'Kuori ja hienonna sipuli ja valkosipulinkynnet. Kuutioi paprika, pilko makkarat. Kuori ja kuutioi bataatit. Kuumenna öljy pannulla ja ruskista makkaranpalat. Siirrä syrjään odottamaan. Lisää pannulle paprikakuutiot. Paista muutama minuutti kunnes paprikat pehmenevät. Siirrä syrjään. Lisää pannulle tarvittaessa tilkka öljyä ja kuullota sipulia ja valkosipulia muutama minuutti. Lisää sitten bataattikuutiot ja paista vielä hetki.', 30, 4);

INSERT INTO recipe (recipe_title, ingredients, instructions, prep_time, servings) VALUES 
('Tofu-perunasalaatti', '2 paprikaa, 1 pkt tofua, 1 kg paistettuja perunoita, rucolaa, öljyä, suolaa', 'Leikkaa paprikat kuutioiksi. Paista tofua kuumalla pannulla tilkassa öljyä pari minuuttia. Kääntele kaikki salaatin aineet yhteen ja tarjoa heti.', 15, 4);

INSERT INTO recipe (recipe_title, ingredients, instructions, prep_time, servings) VALUES 
('Mansikka-havregurt Smoothie', '1 banaani, 1 dl mansikoita, 2 dl Oatly Havregurt Mansikkaa, 1 rkl chia-siemeniä, jääpaloja', 'Laita kaikki ainekset tehosekoittimeen ja sekoita tasaiseksi smoothieksi. Tarjoile kylmänä.', 10, 2);

INSERT INTO review (rating, comment, product_id, user_id) VALUES 
(5, 'Todella herkullisia vegemakkaroita! Rosmariini sopii täydellisesti näihin.', 
(SELECT product_id FROM product WHERE product_name = 'Linda Mccartney Vegetarian Red Onion & Rosemary Sausages'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, recipe_id, user_id) VALUES 
(4, 'Tämä vegemakkarapelti oli todella ruokaisa, mutta mausteita on vähänlaisesti.',
(SELECT recipe_id FROM recipe WHERE recipe_title = 'Vegemakkarapelti'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, product_id, user_id) VALUES 
(4, 'Hyvä mantelimaito, sopii hyvin smoothieen. Maku voisi kuitenkin olla täyteläisempi.',
(SELECT product_id FROM product WHERE product_name = 'Rainbow 1L Mantelijuoma'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, recipe_id, user_id) VALUES 
(5, 'Ihana salaatti! Helppo valmistaa vaikka lounaaksi.',
(SELECT recipe_id FROM recipe WHERE recipe_title = 'Tofu-perunasalaatti'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, product_id, user_id) VALUES 
(4, 'Raikas ja herkullinen kaurajogurtti! Mansikan maku on luonnollinen eikä liian makea.',
(SELECT product_id FROM product WHERE product_name = 'Oatly Havregurt Mansikka'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, product_id, user_id) VALUES 
(3, 'Hyvä kasvipohjainen vaihtoehto, mutta koostumus voisi olla hieman paksumpi.',
(SELECT product_id FROM product WHERE product_name = 'Oatly Havregurt Mansikka'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, recipe_id, user_id) VALUES 
(5, 'Todella helppo ja maukas smoothie! Sopii hyvin aamuun tai välipalaksi.',
(SELECT recipe_id FROM recipe WHERE recipe_title = 'Mansikka-havregurt Smoothie'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, recipe_id, user_id) VALUES 
(4, 'Mansikan ja kauran maku yhdistyvät ihanasti, mutta lisäsin vähän hunajaa, sillä kaipasin makeutta.',
(SELECT recipe_id FROM recipe WHERE recipe_title = 'Mansikka-havregurt Smoothie'),
(SELECT user_id FROM app_user WHERE username = 'user'));

INSERT INTO review (rating, comment, product_id, user_id) VALUES 
(4, 'Yipin tofu on todella monikäyttöinen, sillä maustamattomana se sopii sekä suolaisiin että makeisiin ruokiin. Kiinteä rakenne tekee siitä helpon käsitellä, mutta marinoimalla saa parhaat maut irti.',
(SELECT product_id FROM product WHERE product_name = 'Yipin tofu maustamaton'),
(SELECT user_id FROM app_user WHERE username = 'user'));
