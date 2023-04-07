package it.unicam.ids.dharma.app;

import it.unicam.ids.dharma.app.Catalogo;
import it.unicam.ids.dharma.app.Magazzino;
import it.unicam.ids.dharma.app.Premio;
import it.unicam.ids.dharma.app.Prodotto;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatalogoTest {
    @BeforeAll
    static void inizializzaTest(){
        Magazzino m = Magazzino.getMagazzino();
        m.svuota();
    }
    @Test
    @Order(1)
    public void inserisciPremioTest (){
        Magazzino m = Magazzino.getMagazzino();
        Catalogo c = new Catalogo(1);
        Prodotto prodotto1 = m.aggiungiProdotto("scarpe", 30, 4);
        assertEquals(4, m.getProdottiDisponibili().get(prodotto1));
        Premio premio = new Premio(prodotto1, 4, 10);
        c.inserisciPremio(premio);
        assertTrue(c.getListapremi().contains(premio));
        assertFalse(c.inserisciPremio(premio));
        Prodotto prodotto2 = m.aggiungiProdotto("cinta", 15, 3);
        assertThrows(
            IllegalArgumentException.class, () -> {
                new Premio(prodotto2, 4, 20);
            });
    }

    @Test
    @Order(2)
    public void rimuoviPremioTest()
    {
        Magazzino m = Magazzino.getMagazzino();
        Catalogo c = new Catalogo(1);
        Prodotto prodotto3 = m.aggiungiProdotto("zaino", 30, 4);
        Premio premio = new Premio(prodotto3, 4, 10);
        c.inserisciPremio(premio);
        assertTrue(c.rimuoviPremio(premio));
        assertFalse(c.rimuoviPremio(premio));
    }

    @Test
    @Order(3)
    public void aggiornaPuntiPremioTest()
    {
        Magazzino m = Magazzino.getMagazzino();
        Catalogo c = new Catalogo(1);
        Prodotto prodotto4 = m.aggiungiProdotto("orologio", 30, 4);
        Premio premio = new Premio(prodotto4, 4, 10);
        c.inserisciPremio(premio);
        assertTrue(c.aggiornaPuntiPremio(premio, 20));
        int indicePremio = c.getListapremi().indexOf(premio);
        assertEquals(c.getListapremi().get(indicePremio).getPuntiPremio(), 20);
    }

    @Test
    @Order(4)
    public void aggiornaQuantitaPremioTest() {
        Magazzino m = Magazzino.getMagazzino();
        Catalogo c = new Catalogo(1);
        Prodotto prodotto5 = m.aggiungiProdotto("cappotto", 30, 4);
        Premio premio = new Premio(prodotto5, 4, 10);
        c.inserisciPremio(premio);

        assertFalse(c.aggiornaQuantitaPremio(premio, 6));

        Prodotto prodotto6 = m.aggiungiProdotto("anello", 300, 20);
        Premio premio2 = new Premio(prodotto6, 10, 40);
        c.inserisciPremio(premio2);

        assertTrue(c.aggiornaQuantitaPremio(premio2, 15));

        int indicePremio = c.getListapremi().indexOf(premio2);

        assertEquals(c.getListapremi().get(indicePremio).getQuantitaPremio(), 15);
        assertEquals(Magazzino.getMagazzino().getProdottiDisponibili().get(prodotto6), 5);
    }

    @Test
    @Order(5)
    public void emettiPremioTest() throws CloneNotSupportedException {
        Magazzino m = Magazzino.getMagazzino();
        Catalogo c = new Catalogo(1);
        Prodotto prodotto7 = m.aggiungiProdotto("scarpe", 30, 4);
        Premio premio = new Premio(prodotto7, 4, 10);
        c.inserisciPremio(premio);
        Optional<Premio> premioAcquisito = c.emettiPremio(premio);
        assertTrue(premioAcquisito.isPresent());

        int indicePremio = c.getListapremi().indexOf(premio);

        assertEquals(c.getListapremi().get(indicePremio).getQuantitaPremio(), 3);

        Prodotto prodotto8 = m.aggiungiProdotto("libro", 10, 10);
        Premio premio2 = new Premio(prodotto8, 0, 10);
        c.inserisciPremio(premio2);
        assertFalse(c.emettiPremio(premio2).isPresent());
    }
}
