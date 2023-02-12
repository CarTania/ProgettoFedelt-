package it.unicam.ids.dharma.app;

import java.util.List;

public class Livello {

    private int numeroLivello;
    private List<Cliente> clientiLivello;
    private int percentualeDiAvanzamento;
    private int scontoLivello;

    public Livello(int numeroLivello, List<Cliente> clientiLivello, int percentualeDiAvanzamento, int scontoLivello) {
        this.numeroLivello = numeroLivello;
        this.clientiLivello = clientiLivello;
        this.percentualeDiAvanzamento = percentualeDiAvanzamento;
        this.scontoLivello = scontoLivello;
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

    public int getScontoLivello() {
        return scontoLivello;
    }

    public void setScontoLivello(int scontoLivello) {
        this.scontoLivello = scontoLivello;
    }
}
