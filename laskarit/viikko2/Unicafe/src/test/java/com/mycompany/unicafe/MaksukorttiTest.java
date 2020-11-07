package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoPalauttaaSaldon() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(250);
        assertEquals("saldo: 12.50", kortti.toString());
    }
    
    @Test
    public void saldoPieneneeKunRahaaOtetaan() {
        kortti.otaRahaa(250);
        assertEquals("saldo: 7.50", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaOtetaanYliSaldon() {
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosOttaminenOnnistuu() {
        assertEquals(true, kortti.otaRahaa(250));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosOttaminenEiOnnistu() {
        assertEquals(false, kortti.otaRahaa(1500));
    }
}
