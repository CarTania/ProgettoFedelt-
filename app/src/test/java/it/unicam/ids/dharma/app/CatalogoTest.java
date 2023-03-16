package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {

    @Test
    public void inserisciPremioTest (){
        Catalogo catalogo = new Catalogo(1);
        Prodotto prodotto= new Prodotto("scarpe", 1, 30,4);
        catalogo.inserisciPremio(prodotto, 4, 10);
        assertTrue(catalogo.getListapremi().containsKey(prodotto));
        assertFalse(catalogo.inserisciPremio(prodotto, 4, 10));
        Prodotto prodotto2= new Prodotto("cinta", 1, 15, 3);
        assertFalse(catalogo.inserisciPremio(prodotto2, 4, 15));
    }

    @Test
    public void rimuoviPremioTest()
    {
        Catalogo catalogo = new Catalogo(1);
        Prodotto prodotto= new Prodotto("scarpe", 1, 30,4);
        catalogo.inserisciPremio(prodotto, 4, 10);
        assertTrue(catalogo.rimuoviPremio(prodotto));
        assertFalse(catalogo.rimuoviPremio(prodotto));
    }

    @Test
    public void aggiornaPuntiPremioTest()
    {
        Catalogo catalogo = new Catalogo(1);
        Prodotto prodotto= new Prodotto("scarpe", 1, 30,4);
        catalogo.inserisciPremio(prodotto, 4, 10);
        assertTrue(catalogo.aggiornaPuntiPremio(prodotto, 20));
        assertEquals(catalogo.getListapremi().get(prodotto).getPuntiPremio(), 20);
    }

    @Test
    public void aggiornaQuantitaPremioTest()
    {
        Catalogo catalogo = new Catalogo(1);
        Prodotto prodotto= new Prodotto("scarpe", 1, 30,4);
        catalogo.inserisciPremio(prodotto, 4, 10);
        assertFalse(catalogo.aggiornaQuantitaPremio(prodotto, 6));
        Prodotto prodotto2= new Prodotto("gioiello", 1, 300, 20);
        catalogo.inserisciPremio(prodotto2, 10, 40);
        assertTrue(catalogo.aggiornaQuantitaPremio(prodotto2, 15));
        assertEquals(catalogo.getListapremi().get(prodotto2).getQuantitaPremio(), 15);
        assertEquals(prodotto2.getQuantita(), 5);
    }

    @Test
    public void emettiPremioTest() throws CloneNotSupportedException {
        Catalogo catalogo = new Catalogo(1);
        Prodotto prodotto= new Prodotto("scarpe", 1, 30,4);
        catalogo.inserisciPremio(prodotto, 4, 10);
        Optional<Prodotto> premio= catalogo.emettiPremio(prodotto);
        assertTrue(premio.isPresent());
        assertEquals(premio.get().getQuantita(), 1);
        assertEquals(catalogo.getListapremi().get(prodotto).getQuantitaPremio(), 3);
        Prodotto prodotto2= new Prodotto("libro", 1, 15, 10);
        catalogo.inserisciPremio(prodotto2, 0, 9);
        assertFalse(catalogo.emettiPremio(prodotto2).isPresent());

    }

}
