package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOnAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());     
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString()); 
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());      
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());         
    }
    
    @Test
    public void onnistunutRahanOttaminenPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));      
    }
    
    @Test
    public void epaonnistunutRahanOttaminenPalauttaaFalse() {
        assertTrue(!kortti.otaRahaa(15));      
    }
    
    

}
