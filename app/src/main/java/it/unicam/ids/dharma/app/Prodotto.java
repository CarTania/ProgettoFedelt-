package it.unicam.ids.dharma.app;

public class Prodotto implements Cloneable{

    private String nome;
    private final int idProdotto;
    private double prezzo;
    private int quantita;

    public Prodotto(String nome, int idProdotto, double prezzo) {
        this.nome = nome;
        this.idProdotto = idProdotto;
        this.prezzo = prezzo;
        this.quantita = 1;
    }

    public Prodotto(String nome, int idProdotto, double prezzo, int quantita) {
        this.nome = nome;
        this.idProdotto = idProdotto;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Prodotto ottieniProdotto() throws CloneNotSupportedException {
        Prodotto prodotto = (Prodotto) this.clone();
        prodotto.setQuantita(1);
        this.quantita--;
        return prodotto;
    }
}
