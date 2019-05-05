# Arkkitehtuurikuvaus

## Rakenne

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/Pakkausrakenne.jpg" width="400">

## Käyttöliittymä

Käyttöliittymässä on yksi näkymä: Pelinäkymä. Näkymä on toteutettu scene-oliolla. 
Toteutuksesta vastaa luokka fi.roguelike.ui.FxApp. Toteutus jättää mahdolliseksi laajentaa ohjelmaa uusilla scene-olioilla.

## Sovelluslogiikka
Sovelluksen käynnistyessä luodaan yksi peli-olio, joka vastaa sovelluksen toiminnasta.

Game-olio luo Map-olion jokaista pelin tasoa kohti sekä yhden Character-abstraction toteuttavan Player-olion.

Map-oliot luovat EnemyGenerator-luokan avulla useita Enemy-instansseja toimimaan pelajaan vastustajina. EnemyGenerator hyödyntää EnemyDao-luokan toteutusta erityyppisten vihollisten tietojen hakemiseksi tietokannasta.

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/luokkakaavio.png" width="400">

## Päätoiminnallisuudet

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/sekvenssikaavio1.png" width="400">