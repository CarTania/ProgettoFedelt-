package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Rappresenta un programma a livelli: ciascun livello ha associato un particolare vantaggio che
 * il cliente iscritto a questo programma, una volta raggiunto, può riscattare.
 * @param <T> il vantaggio associato a un livello.
 */
public class ProgrammaLivelli<T> extends ProgrammaFedelta {

    private Livello<T>[] livelliProgramma;


    public ProgrammaLivelli(int numlivelli, List<Livello<T>> listaLivelli, LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
        this.livelliProgramma = new Livello[numlivelli];
        inizializzaProgramma(listaLivelli);
    }

    /**
     * Questo metodo permette di inizializzare il programma aggiungendo i livelli,
     * altrimenti lancia l'eccezione.
     * @param listaLivelli la lista di livelli usata per l'inizializzazione.
     */
    private void inizializzaProgramma(List<Livello<T>> listaLivelli)
    {
        if(listaLivelli.size()== livelliProgramma.length)
        {
            Collections.sort(listaLivelli);
            for (int j= 0; j< listaLivelli.size(); j++)
            {
                if(listaLivelli.get(j).getNumeroLivello() != j+1)
                    throw new IllegalArgumentException();
            }
            for(int i= 0; i< listaLivelli.size(); i++)
            {
                livelliProgramma[i]= listaLivelli.get(i);
            }
        } else throw new IllegalArgumentException();

    }

    /**
     * Calcola il numero totale dei clienti all'interno di questo programma.
     */
    public int totaleClienti() {
        int somma = 0;
        for (Livello<T> l : livelliProgramma) {
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

    public Livello<T>[] getLivelliProgramma() {
        return livelliProgramma;
    }
}