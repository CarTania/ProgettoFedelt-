package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;

public class ProgrammaPunti extends ProgrammaFedelta{

    private int punti;

    public ProgrammaPunti(LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
    }
}
