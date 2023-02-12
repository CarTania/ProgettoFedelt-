package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;

public class ProgrammaLivelli extends ProgrammaFedelta{

    private Livello livelloCorrente;

    public ProgrammaLivelli(LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
    }
}
