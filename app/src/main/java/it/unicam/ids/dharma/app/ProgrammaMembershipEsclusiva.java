package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;

public class ProgrammaMembershipEsclusiva extends ProgrammaFedelta{

    private ServizioPremium servizioScelto;

    public ProgrammaMembershipEsclusiva(LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
    }
}
