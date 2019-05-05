# Arkkitehtuurikuvaus

## Rakenne

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/Pakkausrakenne.jpg" width="400">

## K�ytt�liittym�

K�ytt�liittym�ss� on yksi n�kym�: Pelin�kym�. N�kym� on toteutettu scene-oliolla. 
Toteutuksesta vastaa luokka fi.roguelike.ui.FxApp. Toteutus j�tt�� mahdolliseksi laajentaa ohjelmaa uusilla scene-olioilla.

## Sovelluslogiikka
Sovelluksen k�ynnistyess� luodaan yksi peli-olio, joka vastaa sovelluksen toiminnasta.

Game-olio luo Map-olion jokaista pelin tasoa kohti sek� yhden Character-abstraction toteuttavan Player-olion.

Map-oliot luovat EnemyGenerator-luokan avulla useita Enemy-instansseja toimimaan pelajaan vastustajina. EnemyGenerator hy�dynt�� EnemyDao-luokan toteutusta erityyppisten vihollisten tietojen hakemiseksi tietokannasta.

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/luokkakaavio.png" width="400">

## P��toiminnallisuudet

<img src="https://raw.githubusercontent.com/toukkeli/ot-harjoitustyo/tree/master/dokumentaatio/kuvat/sekvenssikaavio1.png" width="400">