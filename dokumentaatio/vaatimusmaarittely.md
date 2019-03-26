# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on olla perinteinen Roguelike.

[Tarkempi selitys:](https://fi.wikipedia.org/wiki/Roguelike)

## Käyttäjät

Sovellukseen ei tule erillisiä käyttäjärooleja

## Perusversion tarjoama toiminnallisuus

### Päävalikko

- Käyttäjä voi aloittaa uuden pelin
  - Käyttäjä antaa tätä varten pelaajahahmolleen nimen
- Käyttäjä näkee aiemmin tallennetut pelit
  - Tallennetun pelin voi ladata 	

### Pelinäkymä

- Pelaaja näkee ruudulle piirrettynä gridimuotoisen pelimaailman
  - Pelimaailmassa Pelaaja, Seiniä, Tyhjää tilaa, Rappuja ja Vihollisia

- Pelaajalle näytetään ruudulla tietoja pelaajahahmosta
  - HP: Osumapisteet. Kuinka monta lyöntiä pelaaja kestää ennen kuin peli hävitään.
  - Dungeon level: Kuinka monta luolaston kerrosta pelaaja on suorittanut

- Pelaajalle näytetään ruudulla tekstinä pelaajan viimeisimmät toimet sekä vihollisten relevantit toimet
  - Perusversiossa nämä toimet ovat "lyömistä" (katso alla)
    - Esimerkiksi: Kun pelaaja lyö vihollista, tulee näytöllä lukea "Löit vihollista X" tai "Löit vihollista X. Vihollinen X kuoli"

- Pelaaja voi liikuttaa pelaajahahmoa kahdeksaan suuntaan
  - Jos liikutaan kohti tyhjää tilaa, siirretään pelaaja sinne ja hirviöt saavat vuoron
  - Jos liikutaan kohti seinää, mitään ei tapahdu
  - Jos liikutaan kohti vihollista, jäädään paikalleen ja "lyödään" vihollista. Viholliset saavat tämän jälkeen vuoron
    - Viholliset poistetaan jos niitä on lyöty tarpeeksi monta kertaa (Oletusarvoisesti yhden kerran)
  - Jos pelaaja liikkuu ruutuun jossa on raput, siirtyy pelaaja seuraavaan kerrokseen
    - Maailma piirretään uudelleen
    - Pelaajan Dungeon Level kasvaa yhdellä

- Pelaajan toiminnan jälkeen viholliset liikkuvat kohti pelaajaa
  - Mikäli pelaaja on viereisessä ruudussa, "lyödään" pelaajaa ja vähennetään osumapisteitä

- Pelaaja voi lopettaa pelin itse milloin haluaa
  - Tällöin pelitilanne tallennetaan ja se voidaan ladata päävalikosta uudestaan

- Peli lopetetaan myös automaattisesti jos osumapisteet menevät nollaan
  - Tällöin tallennusta ei luoda


### Taustatoiminnallisuus
- Pelimaailman luominen hoidetaan luolastogeneraattorilla
  - Aina kun pelaaja saapuu uuteen kerrokseen, koodi luo uuden satunnaisen luolastokartan

- Kerrokset kasvavat pelaajan päästessä pelissä pidemmälle


## Jatkokehitysideoita

### Taistelun monipuolistaminen
- Lisätään jokaiseen "lyöntiin" mahdollisuus osua tai olla osumatta
- Lisätään pelaajalle sekä vihollisille erilaiset vahinkoarvot (dmg) sekä osumapisteet (hp)

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

### Hirviöiden generoiminen

- Kun pelimaailmaa luodessa generoidaan hirviö, voidaan sille antaa erilaisia ominaisuuksia
  - Nimi, hp, dmg, osumisen todennäköisyys

- Pelissä on taulukko / muu tiedosto, jossa valmiita hirviöpohjia joiden avulla tiettyjä hirviötä voidaan luoda yksinkertaisesti


### Maailmageneraation muokkaus

- Maailmageneraatioalgoritmiin voidaan lisätä muuttujia, joiden pohjalta prosessia voidaan muokata
  - Pelaaja voi peliä aloittaessaan asettaa parametrit haluamikseen ja saada erilaisia pelikokemuksia
  - Mahdollistaa samalla esimerkiksi vaikeusasteen lisäämisen peliin

