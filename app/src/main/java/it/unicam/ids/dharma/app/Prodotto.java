package it.unicam.ids.dharma.app;

import java.util.Objects;

/**
 * Rappresenta un prodotto venduto da un'azienda.
 */
public class Prodotto {
    private final int idProdotto;
    private final String nome;
    private double prezzo;

    public Prodotto(String nome, int idProdotto, double prezzo) {
        this.nome = nome;
        this.idProdotto = idProdotto;
        this.prezzo = prezzo;
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
            '}';
    }
}
