package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammaLivelliTest {

    @Test
    public void inizialiazzaProgrammaTest() {
        Cliente c1 = new Cliente(1, "Roberto", 40, "roberto@gmail.com");
        Cliente c2 = new Cliente(1, "Sara", 32, "sara@gmail.com");
        Cliente c3 = new Cliente(1, "Alessandro", 50, "alessandro@gmail.com");

        List<Coupon> vantaggi = List.of(
            new Coupon(c1, LocalDate.of(2023, 12, 6), 50),
            new Coupon(c2, LocalDate.of(2023, 11, 6), 60),
            new Coupon(c3, LocalDate.of(2023, 7, 23), 32)
        );

        ProgrammaLivelli<Coupon> p = new ProgrammaLivelli<>(
           1243 ,3, vantaggi, LocalDate.of(2023, 12, 31)
        );
        //controllo della correttezza del numero dei livelli e dell'assegnamento
        for (int i = 0; i < p.getLivelliProgramma().size(); i++) {
            assertNotNull(p.getLivelliProgramma().get(i));
            assertEquals(p.getLivelliProgramma().get(i).getNumeroLivello(), i + 1);
        }

        List<Coupon> vantaggi2 = List.of(
            new Coupon(c1, LocalDate.of(2023, 12, 6), 50),
            new Coupon(c2, LocalDate.of(2023, 11, 6), 60)
        );

        assertThrows(
            IllegalArgumentException.class, () -> new ProgrammaLivelli<>(
                154,3, vantaggi2, LocalDate.of(2023, 12, 31)
            )
        );
    }
    @Test
    public void totaleClientiTest(){
        Cliente c1 = new Cliente(1, "Roberto", 40, "roberto@gmail.com");
        Cliente c2 = new Cliente(1, "Sara", 32, "sara@gmail.com");
        Cliente c3 = new Cliente(1, "Alessandro", 50, "alessandro@gmail.com");

        List<Coupon> vantaggi = List.of(
            new Coupon(c1, LocalDate.of(2023, 12, 6), 50),
            new Coupon(c1, LocalDate.of(2023, 11, 6), 60),
            new Coupon(c1, LocalDate.of(2023, 7, 23), 32)
        );

        ProgrammaLivelli<Coupon> p = new ProgrammaLivelli<>(
            152,3, vantaggi, LocalDate.of(2023, 12, 31)
        );

        p.getLivelliProgramma().get(0).aggiungiCliente(c1, 0.0);
        p.getLivelliProgramma().get(1).aggiungiCliente(c2, 0.0);
        p.getLivelliProgramma().get(2).aggiungiCliente(c3, 0.0);

        assertEquals(p.totaleClienti(), 3);
    }
    @Test
    public void aggiornaLivelloTest(){
        Cliente c1 = new Cliente(1, "Roberto", 40, "roberto@gmail.com");
        Cliente c2 = new Cliente(1, "Sara", 32, "sara@gmail.com");
        Cliente c3 = new Cliente(1, "Alessandro", 50, "alessandro@gmail.com");

        List<Coupon> vantaggi = List.of(
            new Coupon(c1, LocalDate.of(2023, 12, 6), 50),
            new Coupon(c2, LocalDate.of(2023, 11, 6), 60),
            new Coupon(c3, LocalDate.of(2023, 7, 23), 32)
        );

        ProgrammaLivelli<Coupon> p = new ProgrammaLivelli<>(
           235, 3, vantaggi, LocalDate.of(2023, 12, 31)
        );

        p.getLivelliProgramma().get(0).aggiungiCliente(c1, 0.0);
        p.getLivelliProgramma().get(1).aggiungiCliente(c2, 95);
        p.getLivelliProgramma().get(2).aggiungiCliente(c3, 95);

        Prodotto prodotto = new Prodotto("SSD", 1, 50, 10);
        Acquisto a1 = new Acquisto(LocalDate.now(), c2);
        a1.aggiungiProdotto(prodotto);
        Acquisto a2 = new Acquisto(LocalDate.now(), c3);
        a2.aggiungiProdotto(prodotto);
        p.aggiornaLivello(p.getLivelliProgramma().get(1), a1);
        p.aggiornaLivello(p.getLivelliProgramma().get(2), a2);

        assertEquals(p.getLivelliProgramma().get(2).getNumeroLivello(), 3);
        assertTrue(p.getLivelliProgramma().get(2).getClientiLivello().containsKey(c2));
        assertTrue(p.getLivelliProgramma().get(2).getClientiLivello().containsKey(c3));
        assertEquals(p.getLivelliProgramma().get(2).getClientiLivello().get(c3), 100);
    }






}