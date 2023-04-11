package it.unicam.ids.dharma.app;

import java.time.LocalDate;


public class Coupon implements VantaggioFedelta {

    private Cliente cliente;
    private final LocalDate dataScadenza;

    private final double totaleSconto;

    public Coupon(LocalDate dataScadenza, double totaleSconto) {
        this.dataScadenza = dataScadenza;
        this.totaleSconto = totaleSconto;
    }

    public Coupon(Cliente cliente, LocalDate dataScadenza, double totaleSconto) {
        this.cliente = cliente;
        this.dataScadenza = dataScadenza;
        this.totaleSconto = totaleSconto;
    }

    /**
     * Il metodo verifica la validità di questo coupon. Ritorna true se il coupon è ancora valido, false se è scaduto.
     * @return true se il coupon è ancora valido, false se è scaduto.
     */
    public boolean verificaValidita(){
        return LocalDate.now().isBefore(dataScadenza) || LocalDate.now().isEqual(dataScadenza);
    }

    public double getTotaleSconto() {
        return totaleSconto;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

