package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Classe che implementa un gestore di programmi fedeltà.
 * E' stato usato il Singleton design pattern al fine di ottenere una singola istanza
 * della classe.
 */
public class GestoreProgrammiFedelta implements Gestore<ElementoDB> {
    private static GestoreProgrammiFedelta gestoreProgrammi;

    private GestoreProgrammiFedelta() {
    }

    public static GestoreProgrammiFedelta getGestoreProgrammi(){
        if(gestoreProgrammi == null)
            gestoreProgrammi = new GestoreProgrammiFedelta();
        return gestoreProgrammi;
    }


    @Override
    public <V> Optional<List<ElementoDB>> ottieniElenco(Predicate<V> p) {
        return Optional.empty();
    }

    @Override
    public <V> Optional<ElementoDB> ottieniElemento(Predicate<V> p) {
        return Optional.empty();
    }

    @Override
    public boolean inserisci(ElementoDB elem) {
        return false;
    }

    @Override
    public boolean rimuovi(ElementoDB elem) {
        return false;
    }

    @Override
    public ElementoDB modificaElemento(ElementoDB elem) {
        return null;
    }
}
