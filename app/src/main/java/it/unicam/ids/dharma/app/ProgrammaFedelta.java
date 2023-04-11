package it.unicam.ids.dharma.app;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 * La classe astratta rappresenta un generico programma fedeltà.
 */
public class ProgrammaFedelta implements ElementoDB {
    protected final int id;
    protected final LocalDate dataAttivazione;
    protected final LocalDate dataScadenza;
    protected boolean attivo;


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

    public int getId() {
        return id;
    }
}
