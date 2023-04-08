package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTest {
    @Test
    public void operazioniProdottoTest(){
        Prodotto p = new Prodotto("Chitarra", 1, 700);
        int quantita = 40;
        assertTrue(DBManager.getGestoreDb().rimuoviProdotto(p));
        assertTrue(DBManager.getGestoreDb().inserisciProdotto(p, quantita));
        assertTrue(DBManager.getGestoreDb().verificaPresenzaProdotto(p.getIdProdotto()));
        Optional<Prodotto> prodottoDB = DBManager.getGestoreDb().recuperaProdotto(p.getIdProdotto());
        assertTrue(prodottoDB.isPresent());
        assertEquals(p, prodottoDB.get());
    }


}
