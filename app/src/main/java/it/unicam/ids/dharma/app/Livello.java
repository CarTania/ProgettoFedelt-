package it.unicam.ids.dharma.app;

import java.util.List;

public class Livello<T> {

    private int numeroLivello;
    private List<Cliente> clientiLivello;
    private int percentualeDiAvanzamento;
    private T vantaggioLivello;

    public Livello(int numeroLivello, List<Cliente> clientiLivello, int percentualeDiAvanzamento, T vantaggioLivello) {
        this.numeroLivello = numeroLivello;
        this.clientiLivello = clientiLivello;
        this.percentualeDiAvanzamento = percentualeDiAvanzamento;
        this.vantaggioLivello = vantaggioLivello;
    }

    public void aggiornaLivello()
    {

    }

    public void scalaLivello()
    {

    }


    public int getNumeroLivello() {
        return numeroLivello;
    }

    public void setNumeroLivello(int numeroLivello) {
        this.numeroLivello = numeroLivello;
    }

    public List<Cliente> getCliente() {
        return clientiLivello;
    }

    public void setCliente(List<Cliente> cliente) {
        this.clientiLivello = cliente;
    }

    public int getPercentualeDiAvanzamento() {
        return percentualeDiAvanzamento;
    }

    public void setPercentualeDiAvanzamento(int percentualeDiAvanzamento) {
        this.percentualeDiAvanzamento = percentualeDiAvanzamento;
    }

    public T vantaggioLivello(){
        return vantaggioLivello;
    }

    public void setVantaggioLivello(T vantaggioLivello) {
        this.vantaggioLivello= vantaggioLivello;
    }
}
