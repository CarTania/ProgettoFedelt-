package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.time.Period;

public class ProgrammaFedelta {

    private LocalDate dataAttivazione;
    private LocalDate dataScadenza;

    private final boolean attivo;

    public ProgrammaFedelta(LocalDate dataAttivazione, LocalDate dataScadenza) {
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

    //calcola il periodo tra due date e lo ritorna in mesi
    public Period calcolaPeriodoValidita()
    {
        return Period.between(dataAttivazione, dataScadenza);
    }

    public Period calcolaTempoRimanente()
    {
        return Period.between(LocalDate.now(), dataScadenza);
    }
    public LocalDate getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(LocalDate dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
