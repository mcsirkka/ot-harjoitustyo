package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void konstruktoriAsettaaRahamaaranOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisiaMyytyAlussaNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaitaMyytyAlussaNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKasvattaaRahamaaraa240() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaRahamaaraa400() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiPalauttaaVaihtorahanOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void syoMaukkaastiPalauttaaVaihtorahanOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }    
    
    @Test
    public void syoEdullisestiKasvattaaLounaidenMaaraa() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaLounaidenMaaraa() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiMuutaRahamaaraaJosMaksuLiianPieni() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiEiMuutaRahamaaraaJosMaksuLiianPieni() {
        kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiPalauttaaRahatJosMaksuLiianPieni() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaRahatJosMaksuLiianPieni() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }   
    
    @Test
    public void syoEdullisestiEiMuutaLounaisenMaaraaJosMaksuLiianPieni() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiMuutaLounaisenMaaraaJosMaksuLiianPieni() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }  
    
    @Test
    public void syoEdullisestiVahentaaKortinSaldoaOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiVahentaaKortinSaldoaOikein() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void syoEdullisestiPalauttaaTrueJosKortinSaldoRiittaa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiPalauttaaTrueJosKortinSaldoRiittaa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoEdullisestiKasvattaaLounaidenMaaraaJosSaldoRiittaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaLounaidenMaaraaJosSaldoRiittaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    } 
    
    @Test
    public void syoEdullisestiEiMuutaKortinSaldoaJosSaldoEiRiita() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiEiMuutaKortinSaldoaJosSaldoEiRiita() {
        kortti.otaRahaa(800);
        kassa.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());        
    }

    @Test
    public void syoEdullisestiEiMuutaLounaidenMaaraaJosKortinSaldoEiRiita() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiMuutaLounaidenMaaraaJosKortinSaldoEiRiita() {
        kortti.otaRahaa(800);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaFalseJosKortinSaldoEiRiita() {
        kortti.otaRahaa(800);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }   
    
    @Test
    public void syoMaukkaastiPalauttaaFalseJosKortinSaldoEiRiita() {
        kortti.otaRahaa(800);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoEdullisestikorttiostoEiMuutaKassanRahamaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastikorttiostoEiMuutaKassanRahamaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleMuuttaaKortinSaldoa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }  
    
    @Test
    public void lataaRahaaKortilleKasvattaaRahamaaraaLadatullaSummalla() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }   
    
    @Test 
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
    }
}
