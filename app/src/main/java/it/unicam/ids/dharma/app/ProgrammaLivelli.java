package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProgrammaLivelli<T> extends ProgrammaFedelta {

    private Livello<T> livelloCorrente;
    private Livello<T>[] livelliProgramma;


    public ProgrammaLivelli(int numlivelli, double soglia, T vantaggio, LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
        this.livelloCorrente = new Livello(0, vantaggio, soglia);
        this.livelliProgramma = new Livello[numlivelli];
    }

    /**
     * Calcolo numero totale dei clienti all'interno di questo programma.
     */
    public int totaleClienti() {
        int somma = 0;
        for (Livello l : livelliProgramma) {
            somma += l.getCliente().size();
        }
        return somma;
    }

    /**
     * aggiorna la percentuale.
     */
    public void aggiornaLivello(Livello<T> livelloDaAggiornare, List<Acquisto> acquistiEffettuati) {
        for (Acquisto a : acquistiEffettuati) {
            if (livelloDaAggiornare.getCliente().containsKey(a.getCliente())) {
                livelloDaAggiornare.aumentaPercentuale(a.getCliente(), a);
            }
        }
    }

    /**
     * Passare al livello successivo.
     */

    public void scalaLivello() {
        for (int i = 0; i < livelliProgramma.length - 1; i++) {
            int indiceLivello = i;
            livelliProgramma[i].getCliente().entrySet().stream()
                    .filter(e -> e.getValue() >= livelliProgramma[indiceLivello].getSoglia())
                    .forEach(e -> livelliProgramma[indiceLivello + 1].getCliente().entrySet().add(e));
            livelliProgramma[i].getCliente().entrySet().stream()
                    .filter(e -> e.getValue() >= livelliProgramma[indiceLivello].getSoglia())
                    .forEach(e -> livelliProgramma[indiceLivello].getCliente().entrySet().remove(e));
        }
    }
}