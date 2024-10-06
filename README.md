# Vegit - sovellus kasvisruoan ystäville

## Johdanto
Vegit on kasvisruokatuotteisiin ja resepteihin keskittyvä sovellus, jossa käyttäjät voivat jakaa kokemuksiaan ja löytää inspiraatiota terveelliseen ja ekologiseen ruokailuun. Sovelluksen avulla voi tutustua laajaan vegaanisiin tuotteisiin, lukea muiden käyttäjien arvosteluja ja lisätä omia tuotteisiin liittyviä kokemuksia.

Vegit tarjoaa myös mahdollisuuden lisätä reseptejä, joissa hyödynnetään kasvipohjaisia tuotteita sekä ja antaa niistä palautetta. Reseptit auttavat käyttäjiä kokeilemaan uusia raaka-aineita sekä löytämään uusia tapoja nauttia kasvisruuasta. 

## Luokkakaavio
Tämä luokkakaavio havainnollistaa sovelluksen rakennetta.

![Luokkakaavio](Vegit_luokkakaavio.jpg)

## Tietohakemisto

> ### _appUser_
> _appUser-taulu sisältää käyttäjätilin tiedot. Tili kuuluu aina vain yhdelle käyttäjälle._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> appuser_id | Long PK | Käyttäjän yksilöivä tunniste
> role_id | Long FK | Roolin tunniste (viittaus role-tauluun)
> username | String (50) |  Käyttäjän käyttäjätunnus
> password | String (250) | Käyttäjän salasana

> ### _Role_
> _Role-taulu sisältää erilaisten käyttäjäroolien tiedot. Rooleja voidaan käyttää auktorisointiin_

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> role_id | Long PK | Roolin yksilöivä tunniste
> role_name | String (50) | Roolin nimi, esim. "admin" tai "user"

> ### _Product_
> _product-taulu sisältää tuotteen tiedot._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> product_id | Long PK | Tuotteen yksilöivä tunniste
> product_tag_id | Long FK | Kategorian indikaattori, viittaus product_tag -tauluun
> product_name | String (100) | Tuotteen nimi, esimerkiksi "Soijasuikaleet" tai "Nyhtökaura"
> brand | String (50) | Tuotemerkki, esimerkiksi "Vegesun" tai "Gold & Green"
> description | String (50) |  Tuotteen tarkempi kuvaus
> ingredients | String (500) | Tuotteen ainesosat

> ### _Recipe_
> _recipe-taulu sisältää reseptin tiedot. _

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> recipe:id | Long PK | Reseptin yksilöivä tunniste
> recipe_tag_id | Long FK | Kategorian indikaattori, viittaus recipe_tag-tauluun
> recipe_title | String (100) | Reseptin otsikko
> instructions | Text | Tarkemmat ohjeet
> prep_time | int |  Valmistukseen kuluva aika
> servings | int | Tieto siitä, kuinka monelle syöjälle reseptistä riittää

> ### _Tag_
> _tag-taulu sisältää sekä tuotteisiin että resepteihin liitettävän kategoriatunnisteen._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> tag_id | Long PK | Kategorian tunniste
> tag_name | String (50) | Kategorian nimi, esimerkiksi "Vegaaninen" tai "Gluteeniton"

> ### _ProductTag_
> _product_tag -taulu on tuotteen ja tagin välitaulu, joka mahdollistaa monen suhde moneen -yhteyden._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> product_tag_id | Long PK | Uniikki id tuotteen ja tagin väliselle yhdistelmälle
> tag_id | Long FK | Kategorian indikaattori, viittaus tag-tauluun
> product_id | Long FK | Tuotteen id, viittaus product-tauluun

> ### _RecipeTag_
> _recipe_tag -taulu on tuotteen ja tagin välitaulu, joka mahdollistaa monen suhde moneen -yhteyden._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> recipe_tag_id | Long PK | Uniikki id tuotteen ja tagin väliselle yhdistelmälle
> tag_id | Long FK | Tagin id, viittaus tag-tauluun
> recipe_id | Long FK | Reseptin id, viittaus recipe-tauluun

> ### _RecipeProduct_
> _recipe_product -taulu on tuotteen ja reseptin välitaulu, joka mahdollistaa monen suhde moneen -yhteyden._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> recipe_product_id | Long PK | Uniikki id tuotteen ja reseptin väliselle yhdistelmälle
> product_id | Long FK | Tuotteen id, viittaus product-tauluun
> recipe_id | Long FK | Reseptin id, viittaus recipe-tauluun

> ### _Review_
> _review-taulu sisältää arvostelun tiedot. Se voi koskea joko tuotetta tai reseptiä_

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> review_id| Long PK | Arvostelun yksilöivä tunnista
> rating | int | Arvosana (välillä 1-5)
> comment | String (1000) | Sanallinen arvostelu
> product_id | Long FK | Tuote, johon arvostelu liittyy, viittaus product-tauluun
> recipe_id | Long FK |  Resepti, johon arvostelu liittyy, viittaus recipe-tauluun
> user_id | Long FK | Käyttäjä, joka on kirjoittanut arvostelun, viittaus app_user -tauluun








