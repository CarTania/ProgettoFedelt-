package it.unicam.ids.dharma.app;

/**
 * La classe astratta rappresenta il concetto di area riservata di un utente generico.
 * @param <T> la tipologia di utente.
 */
public abstract class AreaRiservata<T> {

    private T utente;

    public T getUtente() {
        return utente;
  }
}
