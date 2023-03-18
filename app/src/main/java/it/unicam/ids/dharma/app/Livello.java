package it.unicam.ids.dharma.app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Livello<T> {

    private final int numeroLivello;
    private final Map<Cliente, Double> clientiLivello;
    private final T vantaggioLivello;
    private final double soglia;

    public Livello(int numeroLivello, T vantaggioLivello, double soglia) {
        this.numeroLivello = numeroLivello;
        this.clientiLivello= new HashMap<>();
        this.vantaggioLivello = vantaggioLivello;
        this.soglia = soglia;
    }

    public boolean aggiungiCliente(Cliente cliente, double percentualeAvanzamento)
    {
        if(!clientiLivello.containsKey(cliente))
        {
       clientiLivello.put(cliente,percentualeAvanzamento);
       return true;
        }
     return false;
    }

    public boolean aumentaPercentuale(Cliente clienteAttuale, Acquisto acquisto)
    {
        if (acquisto.getCliente().equals(clienteAttuale))
        {
            clientiLivello.replace(clienteAttuale, clientiLivello.get(clienteAttuale), (acquisto.totaleAcquisto()*0.1));
            return true;
        }
        return false;
    }

    public int getNumeroLivello() {
        return numeroLivello;
    }

    public Map<Cliente, Double> getCliente() {
        return clientiLivello;
    }


    public T vantaggioLivello(){
        return vantaggioLivello;
    }

    public double getSoglia() {
        return soglia;
    }
}
