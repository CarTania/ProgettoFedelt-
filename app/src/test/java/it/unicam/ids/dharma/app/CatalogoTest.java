package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

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
        assertFalse(catalogo.inserisciPremio(prodotto, 4, 15));
    }
}
