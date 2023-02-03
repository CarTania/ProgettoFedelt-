package it.unicam.ids.dharma.app;

public class Prodotto {

    private String nome;
    private int codice;
    private int costo;

    public Prodotto(String nome, int codice, int costo) {
        this.nome = nome;
        this.codice = codice;
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
