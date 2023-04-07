package it.unicam.ids.dharma.app;

import it.unicam.ids.dharma.app.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammaPuntiTest {
    @Test
    public void costruzioneProgrammaConCatalogo() {
        Catalogo c = new Catalogo(1);
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5, c);

        assertNotNull(p1);
        assertTrue(p1.getCatalogoOpzionale().isPresent());
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0, c);
        });
    }

    @Test
    public void costruzioneProgrammaSenzaCatalogo() {
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5);

        assertNotNull(p1);
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0);
        });
    }

    @Test
    public void aggiungiClienteTest() {
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5);
        Cliente c = new Cliente(120, "Giulia", 27, "giulia@gmail.com");

        assertTrue(p1.aggiungiCliente(c));
        assertEquals(0, p1.getClientiIscritti().get(c));
        assertFalse(p1.aggiungiCliente(c));
    }

    @Test
    public void ottieniPuntiTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto = m.aggiungiProdotto("Vernice", 50, 160);
        Prodotto prodotto1 = m.aggiungiProdotto("Trapano", 50, 30);
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5);
        Cliente c = new Cliente(170, "Marco", 29, "marco@gmail.com");
        Acquisto a = new Acquisto(LocalDate.now(), c);
        a.aggiungiProdotto(prodotto);
        p1.aggiungiCliente(c);
        p1.ottieniPunti(c, a);

        assertEquals(25, p1.getClientiIscritti().get(c));
        Acquisto a1 = new Acquisto(LocalDate.now(), c);
        a1.aggiungiProdotto(prodotto);
        a1.aggiungiProdotto(prodotto1);
        p1.ottieniPunti(c, a1);

        assertEquals(75, p1.getClientiIscritti().get(c));
    }

    @Test
    public void premiRiscattabiliClienteTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto = m.aggiungiProdotto("Chitarra", 500, 160);
        Prodotto prodotto1 = m.aggiungiProdotto("Basso", 500, 200);
        Prodotto prodotto2 = m.aggiungiProdotto("Sassofono", 2000, 600);
        Catalogo c = new Catalogo(1);
        Cliente cliente = new Cliente(3, "Daniele", 30, "daniele@gmail.com");
        Premio premio = new Premio(prodotto, 6, 500);
        Premio premio1 = new Premio(prodotto, 6, 600);
        Premio premio2 = new Premio(prodotto, 6, 5000);
        c.inserisciPremio(premio);
        c.inserisciPremio(premio1);
        c.inserisciPremio(premio2);
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5, c);
        p1.aggiungiCliente(cliente);
        Acquisto a = new Acquisto(LocalDate.now(), cliente);
        a.aggiungiProdotto(prodotto);
        a.aggiungiProdotto(prodotto1);
        p1.ottieniPunti(cliente, a);

        List<Prodotto> premiRiscattabili =
            p1.premiRiscattabiliCliente(cliente, p1.getClientiIscritti().get(cliente));
        assertEquals(1, premiRiscattabili.size());

        Cliente cliente1 = new Cliente(4, "Giorgio", 30, "giorgio@gmail.com");
        p1.aggiungiCliente(cliente1);

        List<Prodotto> premiRiscattabili1 =
            p1.premiRiscattabiliCliente(cliente1, p1.getClientiIscritti().get(cliente1));

        assertEquals(0, premiRiscattabili1.size());
    }

    @Test
    public void riscattoPremioCatalogoTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto = m.aggiungiProdotto("Chitarra elettrica", 500, 160);
        Prodotto prodotto1 = m.aggiungiProdotto("Basso elettrico", 500, 200);
        Prodotto prodotto2 = m.aggiungiProdotto("Tastiera", 2000, 600);
        Catalogo c = new Catalogo(1);
        Cliente cliente = new Cliente(3, "Daniele", 30, "daniele@gmail.com");
        Premio premio = new Premio(prodotto, 6, 500);
        Premio premio1 = new Premio(prodotto, 6, 600);
        Premio premio2 = new Premio(prodotto, 6, 5000);
        c.inserisciPremio(premio);
        c.inserisciPremio(premio1);
        c.inserisciPremio(premio2);
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5, c);
        p1.aggiungiCliente(cliente);
        Acquisto a = new Acquisto(LocalDate.now(), cliente);
        a.aggiungiProdotto(prodotto);
        a.aggiungiProdotto(prodotto1);
        p1.ottieniPunti(cliente, a);

        Optional<Prodotto> premioOttenuto = p1.riscattoPremioCatalogo(cliente, premio, 500);

        assertTrue(premioOttenuto.isPresent());
        assertEquals("Chitarra elettrica", premioOttenuto.get().getNome());
        assertEquals(0, p1.getClientiIscritti().get(cliente));
    }

    @Test
    public void generaCouponTest(){
        Magazzino m = Magazzino.getMagazzino();
        Prodotto prodotto = m.aggiungiProdotto("Chitarra elettrica", 500, 160);
        Prodotto prodotto1 = m.aggiungiProdotto("Basso elettrico", 500, 200);
        Prodotto prodotto2 = m.aggiungiProdotto("Tastiera", 2000, 600);
        Catalogo c = new Catalogo(1);
        Cliente cliente = new Cliente(3, "Daniele", 30, "daniele@gmail.com");
        Premio premio = new Premio(prodotto, 6, 500);
        Premio premio1 = new Premio(prodotto, 6, 600);
        Premio premio2 = new Premio(prodotto, 6, 5000);
        c.inserisciPremio(premio);
        c.inserisciPremio(premio1);
        c.inserisciPremio(premio2);
        ProgrammaPunti p1 =
            new ProgrammaPunti(20, LocalDate.of(2023, 5, 24), 0.5, c);
        p1.aggiungiCliente(cliente);
        Acquisto a = new Acquisto(LocalDate.now(), cliente);
        a.aggiungiProdotto(prodotto);
        a.aggiungiProdotto(prodotto1);
        p1.ottieniPunti(cliente, a);
        Coupon coupon = p1.generaCoupon(cliente, 40, LocalDate.of(2023, 5, 24));

        assertNotNull(coupon);
        assertEquals(20, coupon.totaleSconto());
        assertEquals(460, p1.getClientiIscritti().get(cliente));
    }
}
