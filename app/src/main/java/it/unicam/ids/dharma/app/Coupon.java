package it.unicam.ids.dharma.app;

import java.time.LocalDate;

/**
 * Rappresenta un coupon sconto che può essere ottenuto da un cliente iscritto a un programma fedeltà.
 *
 * @param cliente      il cliente che possiede questo coupon.
 * @param dataScadenza la data di scadenza di questo coupon.
 * @param totaleSconto l'ammontare dello sconto ottenuto dal cliente.
 */
public record Coupon(Cliente cliente, LocalDate dataScadenza, double totaleSconto) implements VantaggioFedelta {
    /**
     * Il metodo verifica la validità di questo coupon. Ritorna true se il coupon è ancora valido, false se è scaduto.
     * @return true se il coupon è ancora valido, false se è scaduto.
     */
    public boolean verificaValidita(){
        return LocalDate.now().isBefore(dataScadenza) || LocalDate.now().isEqual(dataScadenza);
    }
}

