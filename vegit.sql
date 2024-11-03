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
