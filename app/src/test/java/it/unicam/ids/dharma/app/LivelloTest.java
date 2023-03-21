package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LivelloTest {


    @Test
    public void aggiungiClienteTest()
    {
        Livello<Integer> livelloStandard= new Livello<>(0, 30, 40);
        Cliente cliente= new Cliente(230, "Luca", 21, false, "luca.malvatani@studenti.unicam.it");
        assertTrue(livelloStandard.aggiungiCliente(cliente, 0));
        assertFalse(livelloStandard.aggiungiCliente(cliente, 10));
    }

    @Test
    public void aumentaPercentualeTest()
    {
        Livello<Integer> livelloStandard= new Livello<>(0, 30, 40);
        Cliente cliente1= new Cliente(231, "Tania", 31, false, "tania.cartechini@studenti.unicam.it");
        Acquisto acquisto= new Acquisto(LocalDate.now(), cliente1);
        assertFalse(livelloStandard.aumentaPercentuale(cliente1, acquisto));
        livelloStandard.aggiungiCliente(cliente1, 40);
        assertTrue(livelloStandard.aumentaPercentuale(cliente1, acquisto));
        Prodotto prodotto= new Prodotto("astuccio", 60, 5);
        acquisto.aggiungiProdotto(prodotto);
        livelloStandard.aumentaPercentuale(cliente1, acquisto);
        assertEquals(livelloStandard.getCliente().get(cliente1), 40+ acquisto.totaleAcquisto()*0.1);
    }
}
