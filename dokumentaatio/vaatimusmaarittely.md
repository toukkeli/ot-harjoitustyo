# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on olla esimerkillinen runko perinteiselle Roguelike-pelille.

[Tarkempi selitys:](https://fi.wikipedia.org/wiki/Roguelike)

Sovellus ei ole valmis peli, sillä sen pelattavuus on kovin huono. Se on sen sijaan toimiva moottori, jonka päälle voidaan helposti rakentaa monipuolisempi peli.

## Käyttäjät

Sovellukseen ei tule erillisiä käyttäjärooleja

## Perusversion tarjoama toiminnallisuus 	

### Pelinäkymä

- Pelaaja näkee ruudulle piirrettynä gridimuotoisen pelimaailman
  - Pelimaailmassa Pelaaja, Seiniä, Tyhjää tilaa, Rappuja ja Vihollisia

- Pelaaja näkee ruudulla myös tekstikentän johon peli kirjoittaa kuvaukset tapahtuneista asioista

- Pelaajalle näytetään ruudulla tietoja pelaajahahmosta
  - HP: Osumapisteet. Kuinka monta lyöntiä pelaaja kestää ennen kuin peli hävitään.
  - Damage: Kuinka paljon vahinkoa pelaaja tekee vihollisiin kun hän lyö niitä.
  - Dungeon level: Kuinka monta luolaston kerrosta pelaaja on suorittanut

- Pelaajalle näytetään ruudulla tekstinä pelaajan viimeisimmän toiminnan kuvaus sekä vihollisten toimintojen kuvaukset.
  - Perusversiossa nämä toimet ovat "lyömistä" (katso alla)
    - Esimerkiksi: Kun pelaaja lyö vihollista, tulee näytöllä lukea "Löit vihollista X" tai "Löit vihollista X. Vihollinen X kuoli"

- Pelaaja voi liikuttaa pelaajahahmoa kahdeksaan suuntaan NUMPAD-näppäimillä
  - Jos liikutaan kohti tyhjää tilaa, siirretään pelaaja sinne ja hirviöt saavat vuoron
  - Jos liikutaan kohti seinää, mitään ei tapahdu
  - Jos liikutaan kohti vihollista, jäädään paikalleen ja "lyödään" vihollista. Viholliset saavat tämän jälkeen vuoron
    - Viholliset poistetaan jos niitä on lyöty tarpeeksi monta kertaa (Oletusarvoisesti yhden kerran)
  - Jos pelaaja painaa NUMPAD 5 ruudussa jossa on raput, siirrytään seuraavaan kerrokseen
    - Maailma piirretään uudelleen
    - Pelaajan Dungeon Level kasvaa yhdellä

- Pelaajan toiminnan jälkeen viholliset liikkuvat kohti pelaajaa
  - Mikäli pelaaja on viereisessä ruudussa, "lyödään" pelaajaa ja vähennetään osumapisteitä

- Pelaaja voi lopettaa pelin itse milloin haluaa painamalla ESC

- Peli lopetetaan myös automaattisesti jos osumapisteet menevät nollaan


### Taustatoiminnallisuus
- Pelimaailman luominen hoidetaan luolastogeneraattorilla
  - Aina kun pelaaja saapuu uuteen kerrokseen, koodi luo uuden satunnaisen luolastokartan

- Kerrokset kasvavat pelaajan päästessä pelissä pidemmälle


## Toteuttamatta jääneet toiminnallisuudet

### Valikkoruutu
- Pelaaja näkisi pelin avatessaan valikkoruudun
  - Ruudusta voisi aloittaa uuden pelin tai mahdolllisesti ladata vanhan
- Pelaaja voisi myös nimetä pelaajahahmon uutta peliä luodessaan (tähän oli jo helpot toiminnallisuudet)

### Muokattavuus
- Pelaajan statseja sekä luolaston generoimistapaa olisi hyvä voida muuttaa esim. config.filesta.
  - Tällä hetkellä peli on toteutettu niin, että muokattavia muuttujia olisi paljon mutta rajapintaa josta käyttäjä voisi sitä tehdä ei ole

### Taistelun monipuolistaminen
- Lisätään jokaiseen "lyöntiin" mahdollisuus osua tai olla osumatta (Tähän on jo olemassa muuttuja Acc jokaisessa vihollisessa ja pelaajassa).
- Lisätään eri tavoin käyttäytyviä vihollisia (Abstrakti luokka Behavior mahdollistaa useita toteutuksia)

### Varustenäkymä ja varusteet

- Maailmassa oleviin tyhjiin ruutuihin voi nyt pelaajan tai hirviön lisäksi sijoittaa esineitä.
- Pelaaja voi poimia esineen. Tähän kuluu vuoro.

- Lisätään pelaajalle mahdollisuus siirtyä varustenäkymään
  - Varustenäkymässä pelaaja ei näe peliruutua, vaan sen tilalla on lista pelaajan poimimista esineistä
  - Pelaaja voi "käyttää" esinettä valitsemalla sen listalta
    - Varusteet puetaan päälle
       - Tämä muuttaa pelaajan ominaisuuksia ja on nähtävissä pelaajan tiedoissa
    - Muut esineet käytetään
      - ???

### Highscore screen

- Pelin loppuessa osumapisteiden puutteeseen tallennetaan pelaajan nime ja viimeinen kerros tietokantataulukkoon

- Päävalikosta voi siirtyä highscore-näkymään
  - Näkymässä käyttäjä näkee pisimmälle luolastossa päässeiden pelaajien nimet

