package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.*;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class MagazzinoTest {
    @BeforeEach
    public void inizializzaTest(){
        Magazzino m = Magazzino.getMagazzino();
        m.svuota();
    }

    @Test
    public void aggiungiProdottoTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Chitarra", 1200, 10);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertNotNull(prodotto1);
        assertEquals(1, m.getProdottiDisponibili().size());
        assertEquals(10, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());

        Prodotto prodotto2 = m.aggiungiProdotto("Stereo", 250, 40);

        int indiceProdotto2 = m.getProdottiDisponibili().indexOf(prodotto2);
        assertNotNull(prodotto2);
        assertEquals(2, m.getProdottiDisponibili().size());
        assertEquals(40, m.getProdottiDisponibili().get(indiceProdotto2).getQuantita());
    }

    @Test
    public void rimuoviProdottoTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Chitarra", 1200, 10);
        m.rimuoviProdotto(prodotto1);

        assertEquals(0, m.getProdottiDisponibili().size());

        Prodotto prodotto2 = m.aggiungiProdotto("Chitarra", 1300, 15);

        Prodotto prodotto3 = m.aggiungiProdotto("Basso", 2000, 30);
        m.rimuoviProdotto(prodotto2);
        m.rimuoviProdotto(prodotto3);

        assertEquals(0, m.getProdottiDisponibili().size());
    }

    @Test
    public void aumentaQuantitaTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto2 = new Prodotto("Ukulele", 5.0, 50);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertTrue(m.aumentaQuantita(prodotto1, 3));
        assertEquals(9, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertFalse(m.aumentaQuantita(prodotto2, 16));
    }

    @Test
    public void decrementaQuantitaTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto2 = new Prodotto("Ukulele", 5.0, 50);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertTrue(m.decrementaQuantita(prodotto1, 3));
        assertEquals(3, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertTrue(m.decrementaQuantita(prodotto1, 3));
        assertEquals(0, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());
        assertThrows(IllegalArgumentException.class, () -> {
            m.decrementaQuantita(prodotto1, 2);
        });
        assertFalse(m.decrementaQuantita(prodotto2, 16));
    }

    @Test
    public void ricercaProdottoPerId(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto2 = m.aggiungiProdotto("Chitarra", 2000, 12);
        m.rimuoviProdotto(prodotto2);

        Optional<Prodotto> prodottoCercato = m.ricercaProdotto(prodotto1.getId());
        assertTrue(prodottoCercato.isPresent());
        assertEquals("Amplificatore", prodottoCercato.get().getNome());

        Optional<Prodotto> prodottoCercato2 = m.ricercaProdotto(prodotto2.getId());
        assertFalse(prodottoCercato2.isPresent());
    }

    @Test
    public void ricercaProdottoPerNome(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto2 = m.aggiungiProdotto("Chitarra", 2000, 12);
        Prodotto prodotto3 = m.aggiungiProdotto("Basso", 900, 22);

        Optional<Prodotto> prodottoCercato = m.ricercaProdotto("Amplificatore");
        assertTrue(prodottoCercato.isPresent());
        assertEquals("Amplificatore", prodottoCercato.get().getNome());

        Optional<Prodotto> prodottoCercato2 = m.ricercaProdotto("Tastiera");
        assertFalse(prodottoCercato2.isPresent());
    }

    @Test
    public void prelevaProdottoTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto1 = m.aggiungiProdotto("Amplificatore", 3000, 6);
        Prodotto prodotto3 = m.aggiungiProdotto("Basso", 1300, 0);
        Optional<Prodotto> prodottoPrelevato = m.prelevaProdotto(prodotto1);
        int indiceProdotto = m.getProdottiDisponibili().indexOf(prodotto1);

        assertTrue(prodottoPrelevato.isPresent());
        assertEquals(5, m.getProdottiDisponibili().get(indiceProdotto).getQuantita());

        assertThrows(IllegalArgumentException.class, () -> {
            m.prelevaProdotto(prodotto3);
        });
    }
}
