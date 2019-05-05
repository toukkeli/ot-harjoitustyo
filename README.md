# Roguelike

Tämä on ohjelmistotekniikka-kurssin harjoitustyö keväälle 2019.

Harjoitustyön aiheena on yksinkertainen Roguelike-pelimoottori. Projekti toteutetaan Javalla.

## Dokumentaatio

[Käyttöohje](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työtuntikirjanpito](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Testaus](https://github.com/toukkeli/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[Viikko 5](https://github.com/toukkeli/ot-harjoitustyo/releases/tag/viikko5)
[Loppupalautus](https://github.com/toukkeli/ot-harjoitustyo/releases/tag/loppupalautus)


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

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedoston .checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
