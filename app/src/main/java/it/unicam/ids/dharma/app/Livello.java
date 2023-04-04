package it.unicam.ids.dharma.app;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe rappresenta un livello del programma a livelli.
 * @param <T> la tipologia del vantaggio associato al livello.
 */
public class Livello<T> implements Comparable<Livello<T>>{

    private final int numeroLivello;
    private final Map<Cliente, Double> clientiLivello;
    private final T vantaggioLivello;
    private final double soglia;

    public Livello(int numeroLivello, T vantaggioLivello, double soglia) {
        this.numeroLivello = numeroLivello;
        this.clientiLivello = new HashMap<>();
        this.vantaggioLivello = vantaggioLivello;
        this.soglia = soglia;
    }

    public boolean aggiungiCliente(Cliente cliente, double percentualeAvanzamento)
    {
        if(!clientiLivello.containsKey(cliente))
        {
            clientiLivello.put(cliente, percentualeAvanzamento);
            return true;
        }
        return false;
    }

    /**
     * Aumenta la percentuale di avanzamento di un cliente nel livello, dopo che ha effettuato
     * un nuovo acquisto.
     * @param cliente il cliente nel livello.
     * @param acquisto l'acquisto effettuato dal cliente.
     * @return true se la percentuale è stata aumentata correttamente, false altrimenti.
     */
    public boolean aumentaPercentuale(Cliente cliente, Acquisto acquisto)
    {
        if (acquisto.getCliente().equals(cliente))
        {
            if (clientiLivello.containsKey(cliente))
            {
                clientiLivello.replace(cliente, clientiLivello.get(cliente),
                        (acquisto.totaleAcquisto() * 0.1)+ (clientiLivello.get(cliente)));
                return true;
            }
        }
        return false;
    }

    public int getNumeroLivello() {
        return numeroLivello;
    }

    public Map<Cliente, Double> getClientiLivello() {
        return clientiLivello;
    }


    public T getVantaggioLivello(){
        return vantaggioLivello;
    }

    public double getSoglia() {
        return soglia;
    }


    @Override
    public int compareTo(Livello<T> o) {
        if(this.getNumeroLivello() < o.getNumeroLivello()) {
            return -1;
        } else if(this.getNumeroLivello() > o.getNumeroLivello()) {
            return 1;
        }
        return 0;
    }
}
