# Vegit - sovellus kasvisruoan ystäville

## Johdanto
Vegit on kasvisruokatuotteisiin ja resepteihin keskittyvä sovellus, jossa käyttäjät voivat jakaa kokemuksiaan ja löytää inspiraatiota terveelliseen ja ekologiseen ruokailuun. Sovelluksen avulla voi tutustua laajaan vegaanisiin tuotteisiin, lukea muiden käyttäjien arvosteluja ja lisätä omia tuotteisiin liittyviä kokemuksia.

Vegit tarjoaa myös mahdollisuuden lisätä reseptejä, joissa hyödynnetään kasvipohjaisia tuotteita sekä ja antaa niistä palautetta. Reseptit auttavat käyttäjiä kokeilemaan uusia raaka-aineita sekä löytämään uusia tapoja nauttia kasvisruuasta. Myös reseptejä voi arvostella ja muiden käyttäjien reseptiarvosteluja lukea.

## Sovelluksen osoite
Sovellus toimii osoitteessa https://vegit-db.onrender.com 

## Luokkakaavio
Tämä luokkakaavio havainnollistaa sovelluksen rakennetta.

![Luokkakaavio](Vegit_luokkakaavio.jpg)

## Tietohakemisto

> ### _appUser_
> _appUser-taulu sisältää käyttäjätilin tiedot. Tili kuuluu aina vain yhdelle käyttäjälle._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> user_id | Long PK | Käyttäjän yksilöivä tunniste
> role | String | Roolin tunniste 
> username | String (50) |  Käyttäjän käyttäjätunnus
> passworhash | String (250) | Käyttäjän salasana

> ### _Product_
> _product-taulu sisältää tuotteen tiedot._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> product_id | Long PK | Tuotteen yksilöivä tunniste
> product_name | String (100) | Tuotteen nimi, esimerkiksi "Soijasuikaleet" tai "Nyhtökaura"
> brand | String (50) | Tuotemerkki, esimerkiksi "Vegesun" tai "Gold & Green"
> description | String (50) |  Tuotteen tarkempi kuvaus
> ingredients | String (500) | Tuotteen ainesosat

> ### _Recipe_
> _recipe-taulu sisältää reseptin tiedot. _

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> recipe_id | Long PK | Reseptin yksilöivä tunniste
> recipe_title | String (50) | Reseptin otsikko
> ingredients | String (1000) | Reseptin ainekset
> instructions | String (5000) | Tarkemmat ohjeet
> prep_time | int |  Valmistukseen kuluva aika
> servings | int | Tieto siitä, kuinka monelle syöjälle reseptistä riittää

> ### _Review_
> _review-taulu sisältää arvostelun tiedot. Se voi koskea joko tuotetta tai reseptiä_

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> review_id| Long PK | Arvostelun yksilöivä tunnista
> rating | int | Arvosana (välillä 1-5)
> comment | String | Sanallinen arvostelu
> product_id | Long FK | Tuote, johon arvostelu liittyy, viittaus product-tauluun
> recipe_id | Long FK |  Resepti, johon arvostelu liittyy, viittaus recipe-tauluun
> user_id | Long FK | Käyttäjä, joka on kirjoittanut arvostelun, viittaus app_user -tauluun








