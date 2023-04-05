package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.time.Period;

/**
 * La classe astratta rappresenta un generico programma fedeltà.
 */
public class ProgrammaFedelta {

    private final int id;

    private final LocalDate dataAttivazione;
    private final LocalDate dataScadenza;

    private boolean attivo;

    public ProgrammaFedelta(int id, LocalDate dataAttivazione, LocalDate dataScadenza) {
        this.id = id;
        this.dataAttivazione = dataAttivazione;
        this.dataScadenza = dataScadenza;
        this.attivo= false;
    }

    public boolean attivo ()
    {
        if (dataAttivazione.compareTo(dataScadenza) <= 0)
            return true;
        else
            return false;
    }

    /**
     * calcola il periodo di validità del programma fedeltà e lo ritorna in mesi.
     * @return il periodo di validà del programma fedeltà (in mesi).
     */
    public Period calcolaPeriodoValidita()
    {
        return Period.between(dataAttivazione, dataScadenza);
    }

    /**
     * Calcola il tempo rimanente alla scadenza del programma fedeltà.
     * @return il tempo rimanente alla scadenza del programma fedeltà.
     */
    public Period calcolaTempoRimanente()
    {
        return Period.between(LocalDate.now(), dataScadenza);
    }
    public LocalDate getDataAttivazione() {
        return dataAttivazione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

}
