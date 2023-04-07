package it.unicam.ids.dharma.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


/**
 * Questa classe permette di tenere traccia dei prodotti dell'azienda.
 */

public class Magazzino {
    private final Map<Prodotto, Integer> prodottiDisponibili;

    private static Magazzino magazzino;

    private static int ultimoIdProdotto;

    private Magazzino() {
        prodottiDisponibili = new HashMap<>();
        ultimoIdProdotto = 0;
    }

    public static Magazzino getMagazzino() {
        if (magazzino == null)
            magazzino = new Magazzino();
        return magazzino;
    }

    public Map<Prodotto, Integer> getProdottiDisponibili() {
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
    public Prodotto aggiungiProdotto(String nome, int prezzo, int quantita) {
        Prodotto prodottoAggiunto = new Prodotto(nome, ultimoIdProdotto, prezzo);
        prodottiDisponibili.put(prodottoAggiunto, quantita);
        ultimoIdProdotto++;
        return prodottoAggiunto;
    }

    /**
     * Il metodo ha lo scopo di rimuovere un prodotto dal magazzino.
     *
     * @param prodotto il prodotto da rimuovere.
     */
    public void rimuoviProdotto(Prodotto prodotto) {
        if (prodottiDisponibili.containsKey(prodotto)) {
            prodottiDisponibili.remove(prodotto);
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
        if (prodottiDisponibili.containsKey(prodotto)) {
            prodottiDisponibili.compute(prodotto, (p, q) -> q + quantitaAggiunta);
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
        if (prodottiDisponibili.containsKey(prodotto)) {
            if (quantita <= prodottiDisponibili.get(prodotto)) {
                prodottiDisponibili.compute(prodotto, (p, q) -> q - quantita);
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
        for (Prodotto p : prodottiDisponibili.keySet()) {
            if (p.getIdProdotto() == id) {
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
        for (Prodotto p : prodottiDisponibili.keySet()) {
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
        if (prodottiDisponibili.containsKey(prodotto)) {
            if (prodottiDisponibili.get(prodotto) > 0){
                Set<Prodotto> prodotti = prodottiDisponibili.keySet();
                for (Prodotto p : prodotti) {
                    if (p.equals(prodotto)) {
                        prodottiDisponibili.compute(p, (prod, q) -> q - 1);
                        return Optional.of(p);
                    }
                }
            }else throw new IllegalArgumentException("Il prodotto passato è esaurito.");
        }
        return Optional.empty();
    }


    public void svuota(){
        this.prodottiDisponibili.clear();
        Magazzino.ultimoIdProdotto = 0;
    }
}
