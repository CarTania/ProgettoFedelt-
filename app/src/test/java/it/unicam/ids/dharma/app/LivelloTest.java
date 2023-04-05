package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LivelloTest {


    @Test
    public void aggiungiClienteTest()
    {
        Cliente cliente = new Cliente(230, "Luca", 21, "luca.malvatani@studenti.unicam.it");
        Coupon c = new Coupon(cliente, LocalDate.of(2023, 5, 23), 40);
        Livello<Coupon> livelloStandard = new Livello<>(0, c);
        assertTrue(livelloStandard.aggiungiCliente(cliente, 0));
        assertFalse(livelloStandard.aggiungiCliente(cliente, 10));
    }

    @Test
    public void aumentaPercentualeTest() {
        Cliente cliente = new Cliente(230, "Luca", 21, "luca.malvatani@studenti.unicam.it");
        Coupon c = new Coupon(cliente, LocalDate.of(2023, 5, 23), 40);
        Livello<Coupon> livelloStandard = new Livello<>(0, c);
        Acquisto acquisto = new Acquisto(LocalDate.now(), cliente);
        assertFalse(livelloStandard.aumentaPercentuale(cliente, acquisto));
        livelloStandard.aggiungiCliente(cliente, 40);
        assertTrue(livelloStandard.aumentaPercentuale(cliente, acquisto));
        Prodotto prodotto = new Prodotto("astuccio", 60, 5);
        acquisto.aggiungiProdotto(prodotto);
        livelloStandard.aumentaPercentuale(cliente, acquisto);
        assertEquals(livelloStandard.getClientiLivello().get(cliente), 40 + acquisto.totaleAcquisto() * 0.1);
    }
}
