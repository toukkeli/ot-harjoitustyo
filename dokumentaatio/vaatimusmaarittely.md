# Vaatimusm��rittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on olla perinteinen Roguelike.

[Tarkempi selitys:](https://fi.wikipedia.org/wiki/Roguelike)

## K�ytt�j�t

Sovellukseen ei tule erillisi� k�ytt�j�rooleja

## Perusversion tarjoama toiminnallisuus

### P��valikko

- K�ytt�j� voi aloittaa uuden pelin
  - K�ytt�j� antaa t�t� varten pelaajahahmolleen nimen
- K�ytt�j� n�kee aiemmin tallennetut pelit
  - Tallennetun pelin voi ladata 	

### Pelin�kym�

- Pelaaja n�kee ruudulle piirrettyn� gridimuotoisen pelimaailman
  - Pelimaailmassa Pelaaja, Seini�, Tyhj�� tilaa, Rappuja ja Vihollisia

- Pelaajalle n�ytet��n ruudulla tietoja pelaajahahmosta
  - HP: Osumapisteet. Kuinka monta ly�nti� pelaaja kest�� ennen kuin peli h�vit��n.
  - Dungeon level: Kuinka monta luolaston kerrosta pelaaja on suorittanut

- Pelaajalle n�ytet��n ruudulla tekstin� pelaajan viimeisimm�t toimet sek� vihollisten relevantit toimet
  - Perusversiossa n�m� toimet ovat "ly�mist�" (katso alla)
    - Esimerkiksi: Kun pelaaja ly� vihollista, tulee n�yt�ll� lukea "L�it vihollista X" tai "L�it vihollista X. Vihollinen X kuoli"

- Pelaaja voi liikuttaa pelaajahahmoa kahdeksaan suuntaan
  - Jos liikutaan kohti tyhj�� tilaa, siirret��n pelaaja sinne ja hirvi�t saavat vuoron
  - Jos liikutaan kohti sein��, mit��n ei tapahdu
  - Jos liikutaan kohti vihollista, j��d��n paikalleen ja "ly�d��n" vihollista. Viholliset saavat t�m�n j�lkeen vuoron
    - Viholliset poistetaan jos niit� on ly�ty tarpeeksi monta kertaa (Oletusarvoisesti yhden kerran)
  - Jos pelaaja liikkuu ruutuun jossa on raput, siirtyy pelaaja seuraavaan kerrokseen
    - Maailma piirret��n uudelleen
    - Pelaajan Dungeon Level kasvaa yhdell�

- Pelaajan toiminnan j�lkeen viholliset liikkuvat kohti pelaajaa
  - Mik�li pelaaja on viereisess� ruudussa, "ly�d��n" pelaajaa ja v�hennet��n osumapisteit�

- Pelaaja voi lopettaa pelin itse milloin haluaa
  - T�ll�in pelitilanne tallennetaan ja se voidaan ladata p��valikosta uudestaan

- Peli lopetetaan my�s automaattisesti jos osumapisteet menev�t nollaan
  - T�ll�in tallennusta ei luoda


### Taustatoiminnallisuus
- Pelimaailman luominen hoidetaan luolastogeneraattorilla
  - Aina kun pelaaja saapuu uuteen kerrokseen, koodi luo uuden satunnaisen luolastokartan

- Kerrokset kasvavat pelaajan p��stess� peliss� pidemm�lle


## Jatkokehitysideoita

### Taistelun monipuolistaminen
- Lis�t��n jokaiseen "ly�ntiin" mahdollisuus osua tai olla osumatta
- Lis�t��n pelaajalle sek� vihollisille erilaiset vahinkoarvot (dmg) sek� osumapisteet (hp)

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

### Hirvi�iden generoiminen

- Kun pelimaailmaa luodessa generoidaan hirvi�, voidaan sille antaa erilaisia ominaisuuksia
  - Nimi, hp, dmg, osumisen todenn�k�isyys

- Peliss� on taulukko / muu tiedosto, jossa valmiita hirvi�pohjia joiden avulla tiettyj� hirvi�t� voidaan luoda yksinkertaisesti


### Maailmageneraation muokkaus

- Maailmageneraatioalgoritmiin voidaan lis�t� muuttujia, joiden pohjalta prosessia voidaan muokata
  - Pelaaja voi peli� aloittaessaan asettaa parametrit haluamikseen ja saada erilaisia pelikokemuksia
  - Mahdollistaa samalla esimerkiksi vaikeusasteen lis��misen peliin

