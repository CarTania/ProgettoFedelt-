package it.unicam.ids.dharma.app;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;


import java.util.HashMap;
import java.util.Map;

public class Catalogo {

    private final int id;
    private final Map<Prodotto, Pair<Integer, Integer>> listapremi;

    public Catalogo(int id) {
        this.id= id;
        this.listapremi = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Prodotto, Pair<Integer, Integer>> getListapremi() {
        return listapremi;
    }

    public boolean aggiungiAlCatalogo(Prodotto prodotto, int quantita, int punti)
    {
        if(!(listapremi.containsKey(prodotto)))
        {
            listapremi.put(prodotto, new MutablePair<>(quantita, punti));
            return true;
        }
        return false;
    }

    public boolean eliminaDalCatalogo(Prodotto prodotto)
    {
        if (listapremi.containsKey(prodotto))
        {
            listapremi.remove(prodotto);
            return true;
        }
        return false;
    }
}

