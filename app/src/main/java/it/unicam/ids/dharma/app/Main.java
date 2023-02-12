package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main (String [] args)
    {
        LocalDate t = LocalDate.of(2022, 2, 20);
        LocalDate n = LocalDate.of(2023, 7, 8);
        LocalDate s = LocalDate.now();
        ProgrammaFedelta a= new ProgrammaFedelta(s, n);

        System.out.println (a.attivo());
        System.out.println (a.calcolaPeriodoValidita().toTotalMonths());
        System.out.println(a.calcolaTempoRimanente());

    }
}
