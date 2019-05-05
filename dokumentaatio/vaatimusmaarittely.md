# Vaatimusm��rittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on olla esimerkillinen runko perinteiselle Roguelike-pelille.

[Tarkempi selitys:](https://fi.wikipedia.org/wiki/Roguelike)

Sovellus ei ole valmis peli, sill� sen pelattavuus on kovin huono. Se on sen sijaan toimiva moottori, jonka p��lle voidaan helposti rakentaa monipuolisempi peli.

## K�ytt�j�t

Sovellukseen ei tule erillisi� k�ytt�j�rooleja

## Perusversion tarjoama toiminnallisuus 	

### Pelin�kym�

- Pelaaja n�kee ruudulle piirrettyn� gridimuotoisen pelimaailman
  - Pelimaailmassa Pelaaja, Seini�, Tyhj�� tilaa, Rappuja ja Vihollisia

- Pelaaja n�kee ruudulla my�s tekstikent�n johon peli kirjoittaa kuvaukset tapahtuneista asioista

- Pelaajalle n�ytet��n ruudulla tietoja pelaajahahmosta
  - HP: Osumapisteet. Kuinka monta ly�nti� pelaaja kest�� ennen kuin peli h�vit��n.
  - Damage: Kuinka paljon vahinkoa pelaaja tekee vihollisiin kun h�n ly� niit�.
  - Dungeon level: Kuinka monta luolaston kerrosta pelaaja on suorittanut

- Pelaajalle n�ytet��n ruudulla tekstin� pelaajan viimeisimm�n toiminnan kuvaus sek� vihollisten toimintojen kuvaukset.
  - Perusversiossa n�m� toimet ovat "ly�mist�" (katso alla)
    - Esimerkiksi: Kun pelaaja ly� vihollista, tulee n�yt�ll� lukea "L�it vihollista X" tai "L�it vihollista X. Vihollinen X kuoli"

- Pelaaja voi liikuttaa pelaajahahmoa kahdeksaan suuntaan NUMPAD-n�pp�imill�
  - Jos liikutaan kohti tyhj�� tilaa, siirret��n pelaaja sinne ja hirvi�t saavat vuoron
  - Jos liikutaan kohti sein��, mit��n ei tapahdu
  - Jos liikutaan kohti vihollista, j��d��n paikalleen ja "ly�d��n" vihollista. Viholliset saavat t�m�n j�lkeen vuoron
    - Viholliset poistetaan jos niit� on ly�ty tarpeeksi monta kertaa (Oletusarvoisesti yhden kerran)
  - Jos pelaaja painaa NUMPAD 5 ruudussa jossa on raput, siirryt��n seuraavaan kerrokseen
    - Maailma piirret��n uudelleen
    - Pelaajan Dungeon Level kasvaa yhdell�

- Pelaajan toiminnan j�lkeen viholliset liikkuvat kohti pelaajaa
  - Mik�li pelaaja on viereisess� ruudussa, "ly�d��n" pelaajaa ja v�hennet��n osumapisteit�

- Pelaaja voi lopettaa pelin itse milloin haluaa painamalla ESC

- Peli lopetetaan my�s automaattisesti jos osumapisteet menev�t nollaan


### Taustatoiminnallisuus
- Pelimaailman luominen hoidetaan luolastogeneraattorilla
  - Aina kun pelaaja saapuu uuteen kerrokseen, koodi luo uuden satunnaisen luolastokartan

- Kerrokset kasvavat pelaajan p��stess� peliss� pidemm�lle


## Toteuttamatta j��neet toiminnallisuudet

### Valikkoruutu
- Pelaaja n�kisi pelin avatessaan valikkoruudun
  - Ruudusta voisi aloittaa uuden pelin tai mahdolllisesti ladata vanhan
- Pelaaja voisi my�s nimet� pelaajahahmon uutta peli� luodessaan (t�h�n oli jo helpot toiminnallisuudet)

### Muokattavuus
- Pelaajan statseja sek� luolaston generoimistapaa olisi hyv� voida muuttaa esim. config.filesta.
  - T�ll� hetkell� peli on toteutettu niin, ett� muokattavia muuttujia olisi paljon mutta rajapintaa josta k�ytt�j� voisi sit� tehd� ei ole

### Taistelun monipuolistaminen
- Lis�t��n jokaiseen "ly�ntiin" mahdollisuus osua tai olla osumatta (T�h�n on jo olemassa muuttuja Acc jokaisessa vihollisessa ja pelaajassa).
- Lis�t��n eri tavoin k�ytt�ytyvi� vihollisia (Abstrakti luokka Behavior mahdollistaa useita toteutuksia)

### Varusten�kym� ja varusteet

- Maailmassa oleviin tyhjiin ruutuihin voi nyt pelaajan tai hirvi�n lis�ksi sijoittaa esineit�.
- Pelaaja voi poimia esineen. T�h�n kuluu vuoro.

- Lis�t��n pelaajalle mahdollisuus siirty� varusten�kym��n
  - Varusten�kym�ss� pelaaja ei n�e peliruutua, vaan sen tilalla on lista pelaajan poimimista esineist�
  - Pelaaja voi "k�ytt��" esinett� valitsemalla sen listalta
    - Varusteet puetaan p��lle
       - T�m� muuttaa pelaajan ominaisuuksia ja on n�ht�viss� pelaajan tiedoissa
    - Muut esineet k�ytet��n
      - ???

### Highscore screen

- Pelin loppuessa osumapisteiden puutteeseen tallennetaan pelaajan nime ja viimeinen kerros tietokantataulukkoon

- P��valikosta voi siirty� highscore-n�kym��n
  - N�kym�ss� k�ytt�j� n�kee pisimm�lle luolastossa p��sseiden pelaajien nimet

