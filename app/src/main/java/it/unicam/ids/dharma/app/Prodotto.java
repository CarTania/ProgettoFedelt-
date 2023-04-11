package it.unicam.ids.dharma.app;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Rappresenta un prodotto venduto da un'azienda.
 */

@Entity
@Embeddable
@Table(name = "prodotti")
public class Prodotto implements  ElementoDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private double prezzo;
    private int quantita;

    public Prodotto() {

    }

    public Prodotto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", prezzo=" + prezzo +
            ", quantita=" + quantita +
            '}';
    }
}
