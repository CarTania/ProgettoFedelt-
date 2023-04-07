package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * L'interfaccia Gestore definisce le operazioni necessarie per gestire l'interazione
 * con il database.
 */
public interface Gestore <T extends ElementoDB> {
    /**
     * Recupera un elenco di elementi che soddisfano un dato predicato dal db.
     *
     * @param p predicato che gli elementi che voglio ottenere devono soddisfare.
     * @return l'elenco di elementi che soddisfano il predicato (se esiste).
     */
    <V> Optional<List<T>> ottieniElenco(Predicate<V> p);

    /**
     * Recupera un elemento che soddisfa un dato predicato dal db.
     *
     * @param p predicato che l'elemento che voglio ottenere deve soddisfare.
     * @return l'elemento che soddisfa il predicato (se esiste).
     */
    <V> Optional<T> ottieniElemento(Predicate<V> p);

    /**
     * Inserisce l'elemento indicato nel db.
     *
     * @param elem l'elemento che voglio inserire nel db.
     * @return true se l'operazione è avvenuta con successo.
     */
    boolean inserisci(T elem);

    /**
     * Rimuove l'elemento indicato dal db.
     *
     * @param elem l'elemento che voglio rimuovere dal db.
     * @return true se l'operazione è avvenuta con successo.
     */
    boolean rimuovi(T elem);

    /**
     * Modifica l'elemento indicato presente nel db.
     *
     * @param elem l'elemento nel db che voglio modificare.
     * @return l'elemento modificato.
     */
    T modificaElemento(T elem);
}
