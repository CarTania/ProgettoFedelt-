package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Rappresenta un programma a livelli: ciascun livello ha associato un particolare vantaggio che
 * il cliente iscritto a questo programma, una volta raggiunto, può riscattare.
 * @param <T> il vantaggio associato a un livello.
 */
public class ProgrammaLivelli<T> extends ProgrammaFedelta {
    private final Livello<T>[] livelliProgramma;
    public ProgrammaLivelli(int numlivelli, List<T> vantaggi, LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
        this.livelliProgramma = new Livello[numlivelli];
        inizializzaProgramma(vantaggi);
    }

    /**
     * Questo metodo permette di inizializzare il programma aggiungendo i livelli con i vantaggi associati.
     */
    private void inizializzaProgramma(List<T> vantaggiLivelli)
    {
        for (int i = 0; i < livelliProgramma.length; i++) {
            livelliProgramma[i] = new Livello<>(i, vantaggiLivelli.get(i), 100);
        }
    }

    /**
     * Calcola il numero totale dei clienti all'interno di questo programma.
     */
    public int totaleClienti() {
        int somma = 0;
        for (Livello<T> l : livelliProgramma) {
            somma += l.getClientiLivello().size();
        }
        return somma;
    }

    /**
     * aggiorna la percentuale.
     */
    public void aggiornaLivello(Livello<T> livelloDaAggiornare, Acquisto acquisto) {
        Cliente cliente = acquisto.getCliente();
        if(livelloDaAggiornare.getClientiLivello().containsKey(cliente)){
            livelloDaAggiornare.aumentaPercentuale(cliente, acquisto);
            if(livelloDaAggiornare.getClientiLivello().get(cliente) >= livelloDaAggiornare.getSoglia()){
                this.scalaLivello(livelloDaAggiornare, cliente);
            }
        }else throw new IllegalArgumentException("Il cliente che ha effettuato l'acquisto non è" +
            "presente nel livello da aggiornare");
    }

    /**
     * Passare al livello successivo.
     */

    public void scalaLivello(Livello<T> livelloDiRiferimento, Cliente cliente) {
        int numLivelloRiferimento = livelloDiRiferimento.getNumeroLivello();
        if(numLivelloRiferimento < this.livelliProgramma.length - 1){
            livelliProgramma[numLivelloRiferimento + 1].getClientiLivello().put(cliente, 0.0);
            livelliProgramma[numLivelloRiferimento].getClientiLivello().remove(cliente);
        }else throw new IllegalArgumentException("Impossibile scalare al livello successivo poichè" +
            "il livello di riferimento è l'ultimo livello del programma.");
    }

    public Livello<T>[] getLivelliProgramma() {
        return livelliProgramma;
    }
}