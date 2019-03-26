# Vaatimusm��rittely

## Sovelluksen tarkoitus

Sovelluksen avulla k�ytt�jien on mahdollista pit�� kirjaa tekem�tt�mist��n t�ist� eli _todoista_. Sovellusta on mahdollista k�ytt�� useamman rekister�ityneen k�ytt�j�n, joilla kaikilla on oma yksil�llinen teht�v�listansa.

## K�ytt�j�t

Alkuvaiheessa sovelluksella on ainoastaan yksi k�ytt�j�rooli eli _normaali k�ytt�j�_. My�hemmin sovellukseen saatetaan lis�t� suuremmilla oikeuksilla varustettu _p��k�ytt�j�_.

## K�ytt�liittym�luonnos

Sovellus koostuu kolmesta eri n�kym�st�

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/v-1.png" width="750">

Sovellus aukeaa kirjautumisn�kym��n, josta on mahdollista siirty� uuden k�ytt�j�n luomisn�kym��n tai onnistuneen kirjautumisen yhteydess� kirjaantuneen k�ytt�j�n teht�v�listaan.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- k�ytt�j� voi luoda j�rjestelm��n k�ytt�j�tunnuksen
  - k�ytt�j�tunnuksen t�ytyy olla uniikki ja pituudeltaan v�hint��n 3 merkki�

- k�ytt�j� voi kirjautua j�rjestelm��n
  - kirjautuminen onnistuu sy�tett�ess� olemassaoleva k�ytt�j�tunnus kirjautumislomakkeelle
  - jos k�ytt�j�� ei olemassa, ilmoittaa j�rjestelm� t�st�

### Kirjautumisen j�lkeen

- k�ytt�j� n�kee omat tekem�tt�m�t ty�t eli _todot_

- k�ytt�j� voi luoda uuden todon
  - luou todo n�kyy ainoastaan sen luoneelle k�ytt�j�lle

- k�ytt�j� voi merkit� todon tehdyksi, jolloin todo h�vi�� listalta

- k�ytt�j� voi kirjautua ulos j�rjestelm�st�

## Jatkokehitysideoita

Perusversion j�lkeen j�rjestelm�� t�ydennet��n ajan salliessa esim. seuraavilla toiminnallisuuksilla

- tehdyksi merkittyjen todojen tarkastelu
- tehdyksi merkittyjen todojen merkkaaminen tekem�tt�miksi
- todon tietojen editointi
- todojen j�rjestely t�rkeysj�rjestykseen
- todojen m��rittely muille k�ytt�jille
- k�ytt�j�tiimit, jotka n�kev�t kaikki yhteiset todot
- mahdollisuus useampaan erilliseen todo-listaan
- lis�t��n todoon kentt�, johon on mahdollista merkit� tarkempia todoon liittyvi� tietoja
- k�ytt�jien yhteyteen salasana, joka vaaditaan kirjautuessa
- k�ytt�j�tunnuksen (ja siihen liittyvien todojen) poisto