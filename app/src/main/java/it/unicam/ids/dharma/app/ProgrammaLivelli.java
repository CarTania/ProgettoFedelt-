package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;

/**
 * Rappresenta un programma a livelli: ciascun livello ha associato un particolare vantaggio che
 * il cliente iscritto a questo programma, una volta raggiunto, può riscattare.
 *
 * @param <T> il vantaggio associato a un livello.
 */
public class ProgrammaLivelli<T extends VantaggioFedelta> extends ProgrammaFedelta {
    private final List<Livello<T>> livelliProgramma;

    public ProgrammaLivelli(int numlivelli, List<T> vantaggi, LocalDate dataScadenza) {
        super(LocalDate.now(), Objects.requireNonNull(dataScadenza));
        this.livelliProgramma = inizializzaProgramma(Objects.requireNonNull(vantaggi), numlivelli);
    }

    /**
     * Esegue l'inizializzazione del programma aggiungendo i livelli indicati con i vantaggi
     * associati. Se il numero di livelli è divero dal numero di vantaggi indicati, viene
     * lanciata un'<code>IllegalArgumentException</code>.
     */
    private List<Livello<T>> inizializzaProgramma(List<T> vantaggiLivelli, int numLivelli) {
        if (vantaggiLivelli.size() != numLivelli)
            throw new IllegalArgumentException("Errore nella creazione del programma: numero di" +
                "vantaggi diverso dal numero dei livelli.");
        List<Livello<T>> livelli = new ArrayList<>(numLivelli);
        for (int i = 0; i < vantaggiLivelli.size(); i++)
            livelli.add(new Livello<>(i + 1, vantaggiLivelli.get(0)));
        return Collections.unmodifiableList(livelli);
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
     * Aggiorna la percentuale di avanzamento del cliente che ha effettuato un acquisto.
     *
     * @param livelloDaAggiornare il livello da aggiornare.
     * @param acquisto            l'acquisto effettuato dal cliente.
     */
    public void aggiornaLivello(Livello<T> livelloDaAggiornare, Acquisto acquisto) {
        Cliente cliente = acquisto.getCliente();
        if (livelloDaAggiornare.getClientiLivello().containsKey(cliente)) {
            livelloDaAggiornare.aumentaPercentuale(cliente, acquisto);
            if (livelloDaAggiornare.getClientiLivello().get(cliente) >= livelloDaAggiornare.getSoglia()) {
                this.scalaLivello(livelloDaAggiornare, cliente);
            }
        } else throw new IllegalArgumentException("Il cliente che ha effettuato l'acquisto non è" +
            "presente nel livello da aggiornare");
    }

    /**
     * Permette di far scalare un cliente (la cui percentuale di avanzamento ha superato la soglia)
     * al livello successivo. Se il livello in cui è presente il cliente è l'ultimo, la percentuale
     * di avanzamento è posta al livello massimo (100%).
     *
     * @param livelloDiRiferimento il livello dove è presente il cliente che deve scalare al livello
     *                             successivo.
     * @param cliente              il cliente che deve scalare al livello successivo.
     */
    public void scalaLivello(Livello<T> livelloDiRiferimento, Cliente cliente) {
        int numLivelloRiferimento = livelloDiRiferimento.getNumeroLivello();
        if (numLivelloRiferimento < this.livelliProgramma.size() - 1) {
            livelliProgramma.get(numLivelloRiferimento + 1).getClientiLivello().put(cliente, 0.0);
            livelliProgramma.get(numLivelloRiferimento).getClientiLivello().remove(cliente);
        } else {
            livelloDiRiferimento.getClientiLivello().computeIfPresent(cliente, (c, p) -> p = 100.0);
        }
    }

    public List<Livello<T>> getLivelliProgramma() {
        return livelliProgramma;
    }
}