package it.unicam.ids.dharma.app;

import java.util.List;

public class Catalogo {

    private int id;
    private List<Prodotto> premi;

    public Catalogo(int id, List<Prodotto> premi) {
        this.id= id;
        this.premi = premi;
    }

    public boolean aggiungiAlCatalogo(Prodotto prodotto)
    {
        if(!(premi.contains(prodotto)))
        {
            return premi.add(prodotto);
        }
        return false;
    }

    public boolean eliminaDalCatalogo(Prodotto prodotto)
    {
        if (premi.contains(prodotto))
        {
            return premi.remove(prodotto);
        }
        return false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Prodotto> getPremi() {
        return premi;
    }

    public void setPremi(List<Prodotto> premi) {
        this.premi = premi;
    }
}

