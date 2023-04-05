package it.unicam.ids.dharma.app;

import java.util.Objects;

/**
 * Rappresenta un prodotto venduto da un'azienda.
 */
public class Prodotto implements Cloneable {
    private final int idProdotto;
    private final String nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return idProdotto == prodotto.idProdotto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdotto);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
            "idProdotto=" + idProdotto +
            ", nome='" + nome + '\'' +
            ", prezzo=" + prezzo +
            ", quantita=" + quantita +
            '}';
    }

    /**
     * Ritorna un prodotto singolo (con quantità uguale a uno) decrementando opportunamente la
     * quantità.
     *
     * @return il prodotto singolo.
     * @throws CloneNotSupportedException
     */
    public Prodotto ottieniProdotto() throws CloneNotSupportedException {
        Prodotto prodotto = (Prodotto) this.clone();
        prodotto.setQuantita(1);
        this.quantita--;
        return prodotto;
    }
}
