package it.unicam.ids.dharma.app;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Rappresenta un premio che è possibile inserire in un catalogo premi.
 */

@Entity
@Table(name = "premi")
public class Premio implements VantaggioFedelta,ElementoDB{
    @OneToOne
    @JoinColumn(name = "id_premio", referencedColumnName = "id")
    @EmbeddedId
    private Prodotto premio;

    @Column(name = "quantita")
    private int quantitaPremio;

    @Column(name = "punti")
    private int puntiPremio;

    public Premio() {
    }

    /**
     * Crea un premio da un prodotto presente nel magazzino, se è possibile farlo.
     *
     * @param prodotto       che si vuole utilizzare come premio.
     * @param quantitaPremio la quantità di unità disponibili da riscattare.
     * @param puntiPremio    i punti associati a quel premio.
     */
    public Premio(Prodotto prodotto, int quantitaPremio, int puntiPremio) {
            this.premio = prodotto;
            this.quantitaPremio = quantitaPremio;
            Magazzino.getMagazzino().decrementaQuantita(prodotto, quantitaPremio);
            this.puntiPremio = puntiPremio;
    }
    

    public Prodotto getPremio() {
        return premio;
    }

    public void setPremio(Prodotto premio) {
        this.premio = premio;
    }

    public int getQuantitaPremio() {
        return quantitaPremio;
    }

    public void setQuantitaPremio(int quantitaPremio) {
        this.quantitaPremio = quantitaPremio;
    }

    public int getPuntiPremio() {
        return puntiPremio;
    }

    public void setPuntiPremio(int puntiPremio) {
        this.puntiPremio = puntiPremio;
    }

    /**
     * Aggiunge nuove unità per questo premio, se c'è disponibilità nel magazzino.
     *
     * @param quantitaDaAggiungere il numero di unità da aggiungere.
     * @return true se la quantità viene aggiornata, false altrimenti.
     */
    public boolean aumentaQuantitaPremio(int quantitaDaAggiungere) {
        int indice = Magazzino.getMagazzino().getProdottiDisponibili().indexOf(this.premio);
        if (Magazzino.getMagazzino().getProdottiDisponibili().get(indice).getQuantita() >= quantitaDaAggiungere) {
            GestoreDB.aumentaQuantitaPremio(this, quantitaDaAggiungere);
            Magazzino.getMagazzino().decrementaQuantita(this.premio, quantitaDaAggiungere);
            this.quantitaPremio += quantitaDaAggiungere;
            return true;
        }
        return false;
    }

    /**
     * Decrementa le unità di un premio e le aggiunge nuovamente nel magazzino.
     *
     * @param quantitaDaDecrementare il numero di unità da rimuovere.
     * @return true se la quantità viene decrementata, false altrimenti.
     */
    public boolean decrementaQuantitaPremio(int quantitaDaDecrementare) {
        if ((this.quantitaPremio - quantitaDaDecrementare) >= 0) {
            GestoreDB.decrementaQuantitaPremio(this, quantitaDaDecrementare);
            Magazzino.getMagazzino().aumentaQuantita(this.premio, quantitaDaDecrementare);
            this.quantitaPremio -= quantitaDaDecrementare;
            return true;
        }
        return false;
    }

    /**
     * Aggiunge dei punti a questo premio.
     *
     * @param puntiDaAggiungere la quantità di punti da aggiungere.
     * @return true se i punti sono stati aggiunti correttamente, false altrimenti.
     */
    public boolean aumentaPuntiPremio(int puntiDaAggiungere) {
        if (puntiDaAggiungere > 0) {
            this.puntiPremio += puntiDaAggiungere;
            return true;
        }
        return false;
    }

    /**
     * Decrementa i punti di questo premio.
     *
     * @param puntiDaDecrementare la quantità di punti da decrementare.
     * @return true se i punti sono stati decrementati correttamente, false altrimenti.
     */
    public boolean decrementaPuntiPremio(int puntiDaDecrementare) {
        if (puntiDaDecrementare > 0 && (this.puntiPremio - puntiDaDecrementare) > 0) {
            this.puntiPremio -= puntiDaDecrementare;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Premio premio1 = (Premio) o;
        return premio.equals(premio1.premio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premio);
    }

    @Override
    public String toString() {
        return "Premio{" +
            "premio=" + premio +
            ", quantitaPremio=" + quantitaPremio +
            ", puntiPremio=" + puntiPremio +
            '}';
    }
}
