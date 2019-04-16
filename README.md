# Roguelike

Tämä on ohjelmistotekniikka-kurssin harjoitustyö keväälle 2019.

Harjoitustyön aiheena on yksinkertainen Roguelike-peli. Projekti toteutetaan Javalla.

## Dokumentaatio

[Käyttöohje](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

[Työtuntikirjanpito](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/toukkeli/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _roguelike-1.0-SNAPSHOT.jar_
