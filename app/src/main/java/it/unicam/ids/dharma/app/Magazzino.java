package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;


/**
 * Questa classe permette di tenere traccia dei prodotti dell'azienda.
 */

public record Magazzino(List<Prodotto> prodottiDisponibili) {

    /**
     * Il metodo ha lo scopo di aggiungere un prodotto al magazzino.
     * Se il prodotto è già presente, aumenta la quantità.
     *
     * @param prodotto è il prodotto da aggiungere.
     */

    public void aggiungiProdotto(Prodotto prodotto) {
        if (prodottiDisponibili.contains(prodotto)) {
            int indiceProdotto = prodottiDisponibili.indexOf(prodotto);
            int quantita = prodottiDisponibili.get(indiceProdotto).getQuantita() + prodotto.getQuantita();
            prodottiDisponibili.get(indiceProdotto).setQuantita(quantita);
        } else
            prodottiDisponibili.add(prodotto);
    }

    /**
     * Il metodo ha lo scopo di rimuovere un prodotto dal magazzino.
     *
     * @param prodotto è il prodotto da rimuovere.
     */

    public void rimuoviProdotto(Prodotto prodotto) {
        if (prodottiDisponibili.contains(prodotto)) {
            prodottiDisponibili.remove(prodotto);
        } else
            throw new IllegalArgumentException("Il prodotto da rimuovere non è presente nel magazzino.");
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
            if (p.getIdProdotto() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    /**
     * Il metodo ha lo scopo di ricercare il prodotto nel magazzino sulla base del nome fornito.
     * Se il prodotto non è presente allora ritorna <code>Optional.empty()</code>.
     * @param nome è il nome del prodotto
     * @return il prodotto, se è presente.
     */

    public Optional <Prodotto> ricercaProdotto (String nome)
    {
        for (Prodotto p : prodottiDisponibili) {
            if (p.getNome().equals(nome)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
