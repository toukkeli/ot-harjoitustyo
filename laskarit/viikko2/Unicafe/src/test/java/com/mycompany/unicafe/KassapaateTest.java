/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toukk
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }

    // Onko kassa luotu oikein
    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa != null);
    }

    @Test
    public void alussaOikeaMaaraRahaa() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void alussaEiMyytyjaAterioita() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    // Edulliset luonaat ilman korttia
    @Test
    public void edullisenLounaanOstoPalauttaaOikeanVaihtorahan() {
        int vaihtoraha = kassa.syoEdullisesti(250);
        assertTrue(vaihtoraha == 10);
    }

    @Test
    public void edullisenLounaanOnnistunutOstoKasvattaaMyytyjaAterioita() {
        kassa.syoEdullisesti(250);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void edullisenLounaanOstoPalauttaaKaikenVaihtorahanaJosOstoEiOnnistu() {
        int vaihtoraha = kassa.syoEdullisesti(220);
        assertTrue(vaihtoraha == 220);
    }

    @Test
    public void edullisenLounaanEpaonnistunutOstoEiKasvataMyytyjaAterioita() {
        kassa.syoEdullisesti(220);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    //Maukkaat lounaat ilman korttia
    @Test
    public void maukkaanLounaanOstoPalauttaaOikeanVaihtorahan() {
        int vaihtoraha = kassa.syoMaukkaasti(410);
        assertTrue(vaihtoraha == 10);
    }

    @Test
    public void maukkaanLounaanOnnistunutOstoKasvattaaMyytyjaAterioita() {
        kassa.syoMaukkaasti(410);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void maukkaanLounaanOstoPalauttaaKaikenVaihtorahanaJosOstoEiOnnistu() {
        int vaihtoraha = kassa.syoMaukkaasti(390);
        assertTrue(vaihtoraha == 390);
    }

    @Test
    public void maukkaanLounaanEpaonnistunutOstoEiKasvataMyytyjaAterioita() {
        kassa.syoMaukkaasti(390);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    // Edulliset luonaat kortilla
    @Test
    public void edullisenLounaanOstoKortillaTuottaaOikeanSaldon() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void edullisenLounaanOstoKortillaKasvattaaMyytyjaAterioita() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void edullisenLounaanOstoKortillaEiVahennaRahaaKortilta() {
        kortti.otaRahaa(300);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 200);
    }

    @Test
    public void edullisenLounaanEpaonnistunutOstoKortillaEiKasvataMyytyjaAterioita() {
        kortti.otaRahaa(300);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisenLounaanOstoKortillaEiMuutaKassanRahamaaraa() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    // Maukkaat luonaat kortilla
    @Test
    public void maukkaanLounaanOstoKortillaTuottaaOikeanSaldon() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void maukkaanLounaanOstoKortillaKasvattaaMyytyjaAterioita() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void maukkaanLounaanOstoKortillaEiVahennaRahaaKortilta() {
        kortti.otaRahaa(300);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 200);
    }

    @Test
    public void maukkaanLounaanEpaonnistunutOstoKortillaEiKasvataMyytyjaAterioita() {
        kortti.otaRahaa(300);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void maukkaanLounaanOstoKortillaEiMuutaKassanRahamaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    // Rahan lataaminen
    @Test
    public void rahanLatausKortilleLisaaRahaaKassaan() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(kassa.kassassaRahaa() == 100000 + 1000);
    }

    @Test
    public void rahanLatausKortilleLisaaRahaaKortille() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(kortti.saldo() == 500 + 1000);
    }

    @Test
    public void negatiivistaSummaaEiLadatesaLaitetaKassaan() {
        kassa.lataaRahaaKortille(kortti, -4000);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void negatiivistaSummaaEiLadatesaLaitetaKortille() {
        kassa.lataaRahaaKortille(kortti, -4000);
        assertTrue(kortti.saldo() == 500);
    }

}
