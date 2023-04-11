package it.unicam.ids.dharma.app;

import java.util.*;


/**
 * Questa classe permette di tenere traccia dei prodotti dell'azienda.
 */

public class Magazzino {
    private final List<Prodotto> prodottiDisponibili;
    private static Magazzino magazzino;

    private Magazzino() {
        prodottiDisponibili = new ArrayList<>();
    }

    public static Magazzino getMagazzino() {
        if (magazzino == null)
            magazzino = new Magazzino();
        return magazzino;
    }

    public List<Prodotto> getProdottiDisponibili() {
        return prodottiDisponibili;
    }

    /**
     * Aggiunge un nuovo prodotto al magazzino. Se il prodotto è già presente, ritorna false.
     *
     * @param nome il nome del nuovo prodotto da aggiungere.
     * @param prezzo il prezzo del nuovo prodotto da aggiungere.
     * @param quantita la quantità di quel prodotto da aggiungere.
     * @return il prodotto aggiunto.
     */
    public Prodotto aggiungiProdotto(String nome, double prezzo, int quantita) {
        Prodotto prodottoAggiunto = new Prodotto(nome, prezzo, quantita);
        prodottiDisponibili.add(prodottoAggiunto);
        GestoreDB.inserisciProdotto(prodottoAggiunto);
        return prodottoAggiunto;
    }

    /**
     * Il metodo ha lo scopo di rimuovere un prodotto dal magazzino.
     *
     * @param prodotto il prodotto da rimuovere.
     */
    public void rimuoviProdotto(Prodotto prodotto) {
        if (prodottiDisponibili.contains(prodotto)) {
            prodottiDisponibili.remove(prodotto);
            GestoreDB.rimuoviProdotto(prodotto);
        } else
            throw new IllegalArgumentException("Il prodotto da rimuovere non è presente nel magazzino.");
    }

    /**
     * Aumenta la quantità di uno specifico prodotto presente nel magazzino.
     *
     * @param prodotto il prodotto di cui si vuole aumentare la quantità.
     * @param quantitaAggiunta il numero di unità da aggiungere per quel prodotto.
     * @return true se la quantità è stata aumentata, false altrimenti.
     */
    public boolean aumentaQuantita(Prodotto prodotto, int quantitaAggiunta) {
        if (prodottiDisponibili.contains(prodotto)) {
            GestoreDB.aumentaQuantitaProdotto(prodotto, quantitaAggiunta);
            int indice = prodottiDisponibili.indexOf(prodotto);
            int quantitaAggiornata = prodottiDisponibili.get(indice).getQuantita() + quantitaAggiunta;
            prodottiDisponibili.get(indice).setQuantita(quantitaAggiornata);
            return true;
        }
        return false;
    }

    /**
     * Decrementa la quantità di un prodotto presente nel magazzino.
     *
     * @param prodotto il prodotto di cui decrementare la quantità.
     * @param quantita il numero di unità di quel prodotto da rimuovere.
     * @return true se la quantità è stata decrementata, false se il prodotto non è presente.
     */
    public boolean decrementaQuantita(Prodotto prodotto, int quantita) {
        if (prodottiDisponibili.contains(prodotto)) {
            int indice = prodottiDisponibili.indexOf(prodotto);
            if (quantita <= prodottiDisponibili.get(indice).getQuantita()) {
                GestoreDB.decrementaQuantitaProdotto(prodotto, quantita);
                int quantitaAggiornata = prodottiDisponibili.get(indice).getQuantita() - quantita;
                prodottiDisponibili.get(indice).setQuantita(quantitaAggiornata);
                return true;
            } else
                throw new IllegalArgumentException("La quantità da decrementare non può" +
                    "essere maggiore della quantità attuale del prodotto");
        }
        return false;
    }

    /**
     * Il metodo ha lo scopo di ricercare il prodotto nel magazzino sulla base dell'id fornito.
     * Se il prodotto non è presente allora ritorna <code>Optional.empty()</code>.
     *
     * @param id è l'id del prodotto.
     * @return il prodotto, se è presente.
     */

    public Optional<Prodotto> ricercaProdotto(int id) {
        for (Prodotto p : prodottiDisponibili) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    /**
     * Il metodo ha lo scopo di ricercare il prodotto nel magazzino sulla base del nome fornito.
     * Se il prodotto non è presente allora ritorna <code>Optional.empty()</code>.
     *
     * @param nome è il nome del prodotto
     * @return il prodotto, se è presente.
     */

    public Optional<Prodotto> ricercaProdotto(String nome) {
        for (Prodotto p : prodottiDisponibili) {
            if (p.getNome().equals(nome)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    /**
     * Preleva un prodotto dal magazzino, se è presente.
     *
     * @param prodotto il prodotto da prelevare.
     * @return il prodotto da prelevare, se esiste, altrimenti ritorna <code>Optional.empty()</code>.
     */
    public Optional<Prodotto> prelevaProdotto(Prodotto prodotto) {
        if (prodottiDisponibili.contains(prodotto)) {
            int indice = prodottiDisponibili.indexOf(prodotto);
            if (prodottiDisponibili.get(indice).getQuantita() > 0){
                for (Prodotto p : prodottiDisponibili) {
                    if (p.equals(prodotto)) {
                        this.decrementaQuantita(p, 1);
                        int quantitaAggiornata = prodottiDisponibili.get(indice).getQuantita();
                        GestoreDB.aumentaQuantitaProdotto(p, quantitaAggiornata);
                        return Optional.of(p);
                    }
                }
            }else throw new IllegalArgumentException("Il prodotto passato è esaurito.");
        }
        return Optional.empty();
    }


    public void svuota(){
        this.prodottiDisponibili.clear();
    }
}
