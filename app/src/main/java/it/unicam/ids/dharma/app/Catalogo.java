package it.unicam.ids.dharma.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Catalogo {
    private final int id;
    private final Map<Prodotto, AttributiPremio> listapremi;

    public Catalogo(int id) {
        this.id = id;
        this.listapremi = new HashMap<>();
    }
    public int getId() {
        return id;
    }
    public Map<Prodotto, AttributiPremio> getListapremi() {
        return listapremi;
    }
    public boolean inserisciPremio(Prodotto prodotto, int quantita, int punti)
    {
        if(!(listapremi.containsKey(prodotto)))
        {
            //se c'è una quantità sufficiente di quel prodotto, lo inserisco nel catalogo
            if(prodotto.getQuantita() >= quantita){
                listapremi.put(prodotto, new AttributiPremio(quantita, punti));
                prodotto.setQuantita(prodotto.getQuantita() - quantita);
                return true;
            }
        }
        return false;
    }
    public boolean rimuoviPremio(Prodotto prodotto)
    {
        if (listapremi.containsKey(prodotto))
        {
            listapremi.remove(prodotto);
            return true;
        }
        return false;
    }

    //assegna un numero di punti diverso a uno specifico premio.
    public boolean aggiornaPuntiPremio(Prodotto prodotto, int punti){
        if(listapremi.containsKey(prodotto)){
            listapremi.get(prodotto).setPunti(punti);
            return true;
        }
        return false;
    }

    public boolean aggiornaQuantitaPremio(Prodotto prodotto, int quantita){
        if(listapremi.containsKey(prodotto)){
            if(prodotto.getQuantita() >= quantita){
                listapremi.get(prodotto).setQuantita(quantita);
                return true;
            }
        }
        return false;
    }

    /*
        * questo metodo viene chiamato quando un premio viene riscosso da un cliente.
        * La quantità dello specifico premio viene decrementata di un' unità e il prodotto
        *  viene restituito (se ci sono ancora unità disponibili).
     */
    public Optional<Prodotto> emettiPremio(Prodotto prodotto) throws CloneNotSupportedException {
        if(listapremi.containsKey(prodotto)){
            //se il premio è disponibile nel catalogo
            if(listapremi.get(prodotto).getQuantita() != 0){
                aggiornaQuantitaPremio(prodotto,
                    listapremi.get(prodotto).getQuantita() - 1);
                return Optional.ofNullable(prodotto.ottieniProdotto());
            }
        }
        return null;
    }

    /*
        * classe annidata che rappresenta gli attributi di un premio (quantità e punti
        * associati al premio) presente nel catalogo.
     */
    class AttributiPremio{
        private int quantita;
        private int punti;

        public AttributiPremio(int quantita, int punti) {
            this.quantita = quantita;
            this.punti = punti;
        }

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

        public int getPunti() {
            return punti;
        }

        public void setPunti(int punti) {
            this.punti = punti;
        }
    }
}

