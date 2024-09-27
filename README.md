# Vegit - sovellus kasvisruoan ystäville

## Johdanto
Vegit on kasvisruokatuotteisiin ja resepteihin keskittyvä sovellus, jossa käyttäjät voivat jakaa kokemuksiaan ja löytää inspiraatiota terveelliseen ja ekologiseen ruokailuun. Sovelluksen avulla voi tutustua laajaan vegaanisiin tuotteisiin, lukea muiden käyttäjien arvosteluja ja lisätä omia tuotteisiin liittyviä kokemuksia.

Vegit tarjoaa myös mahdollisuuden lisätä reseptejä, joissa hyödynnetään kasvipohjaisia tuotteita sekä ja antaa niistä palautetta. Reseptit auttavat käyttäjiä kokeilemaan uusia raaka-aineita sekä löytämään luovia ja herkullisia tapoja nauttia kasvisruuasta. 

## Luokkakaavio
Tämä luokkakaavio havainnollistaa sovelluksen rakennetta.

![Luokkakaavio](Vegit_luokkakaavio.jpg)

## Tietohakemisto

> ### _user_
> _user-taulu sisältää käyttäjätilin tiedot. Tili kuuluu aina vain yhdelle käyttäjälle._

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> user_id | Long PK | Käyttäjän yksilöivä tunniste
> role_id | Long FK | Roolin tunniste (viittaus role-tauluun)
> username | String (50) |  Käyttäjän käyttäjätunnus
> password | String (255) | Käyttäjän salasana

> ### _product_
> _product-taulu sisältää tuotteen tiedot. _

> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> productId | Long PK | Tuotteen yksilöivä tunniste
> productName | String (100) | Tuotteen nimi, esimerkiksi "Soijasuikaleet" tai "Nyhtökaura"
> brand | String (50) | Tuotemerkki, esimerkiksi "Vegesun" tai "Gold & Green"
> description | String (50) |  Tuotteen tarkempi kuvaus
> ingredients | String (500) | Ainesosat



