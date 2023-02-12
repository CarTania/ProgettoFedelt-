package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;

public class ProgrammaCoalizione extends ProgrammaFedelta{

    private Titolare titolariIscritti[];
    private int scontoAccordato;

    public ProgrammaCoalizione(LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
    }
}