package it.unicam.ids.dharma.app;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {
    @BeforeEach
    public void inizializzaTest(){
        Magazzino m = Magazzino.getMagazzino();
        m.svuota();
    }

    @Test
    public void costruzionePremio(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto2 = new Prodotto("Ukulele", 5.0, 50);
        Premio premio = new Premio(prodotto1, 3, 4000);
        assertNotNull(premio);

        Premio premio2 = new Premio(prodotto1, 3, 5000);
        assertNotNull(premio2);
    }

    @Test
    public void aumentaQuantitaPremioTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 10);
        Premio premio = new Premio(prodotto1, 3, 4000);
        Catalogo c = new Catalogo();
        c.inserisciPremio(premio);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertTrue(premio.aumentaQuantitaPremio(4));
        assertEquals(7, premio.getQuantitaPremio());
        assertEquals(3, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertFalse(premio.aumentaQuantitaPremio(4));
    }

    @Test
    public void decrementaQuantitaPremioTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 10);
        Premio premio = new Premio(prodotto1, 3, 4000);
        Catalogo c = new Catalogo();
        c.inserisciPremio(premio);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertEquals(7, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertTrue(premio.decrementaQuantitaPremio(2));
        assertEquals(1, premio.getQuantitaPremio());
        assertEquals(9, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertFalse(premio.decrementaQuantitaPremio(2));
    }

    @Test
    public void aumentaPuntiPremioTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 10);
        Premio premio = new Premio(prodotto1, 3, 4000);

        assertTrue(premio.aumentaPuntiPremio(1000));
        assertEquals(5000, premio.getPuntiPremio());
        assertFalse(premio.aumentaPuntiPremio(0));
        assertEquals(5000, premio.getPuntiPremio());
    }

    @Test
    public void decrementaPuntiPremioTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 10);
        Premio premio = new Premio(prodotto1, 3, 4000);

        assertTrue(premio.decrementaPuntiPremio(3999));
        assertEquals(1, premio.getPuntiPremio());
        assertFalse(premio.decrementaPuntiPremio(1));
        assertFalse(premio.decrementaPuntiPremio(0));
        assertEquals(1, premio.getPuntiPremio());
    }
}
