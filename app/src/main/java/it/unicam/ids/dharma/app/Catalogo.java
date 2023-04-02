package it.unicam.ids.dharma.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Rappresenta un catalogo premi che può essere associato a un programma a punti.
 */
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

    /**
     * Inserisce un nuovo premio nel catalogo, se disponibile nello stock dei prodotti.
     * @param prodotto il prodotto da inserire come premio.
     * @param quantita il numero di unità del premio che si vuole inserire nel catalogo.
     * @param punti i punti che si vogliono associare al premio.
     * @return true se l'inserimento è avvenuto correttamente, false altrimenti.
     */
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

    /**
     * Assegna un numero di punti diverso a uno specifico premio.
     * @param prodotto il premio che si vuole modificare.
     * @param punti la nuova quantità di punti che si vuole associare al premio.
     * @return true se l'aggiornamento è avvenuto con successo, false altrimenti.
     */
    public boolean aggiornaPuntiPremio(Prodotto prodotto, int punti){
        if(listapremi.containsKey(prodotto)){
            listapremi.get(prodotto).setPuntiPremio(punti);
            return true;
        }
        return false;
    }
    /**
     * Assegna un numero di unità diverso a uno specifico premio.
     * @param prodotto il premio che si vuole modificare.
     * @param quantita il nuovo numero di unità di quel premio.
     * @return true se l'aggiornamento è avvenuto con successo, false altrimenti.
     */
    public boolean aggiornaQuantitaPremio(Prodotto prodotto, int quantita){
        if(listapremi.containsKey(prodotto)){
            if(prodotto.getQuantita() >= quantita){
                listapremi.get(prodotto).setQuantitaPremio(quantita);
                return true;
            }
        }
        return false;
    }

    /**
     * Il metodo viene chiamato quando un premio viene riscosso da un cliente.
     * La quantità dello specifico premio viene decrementata di un' unità e il prodotto
     * viene restituito (se ci sono ancora unità disponibili).
     * @param prodotto il premio che deve essere emesso.
     * @return il premio, se disponibile.
     * @throws CloneNotSupportedException
     */
    public Optional<Prodotto> emettiPremio(Prodotto prodotto) throws CloneNotSupportedException {
        if(listapremi.containsKey(prodotto)){
            //se il premio è disponibile nel catalogo
            if(listapremi.get(prodotto).getQuantitaPremio() != 0){
                aggiornaQuantitaPremio(prodotto,
                    listapremi.get(prodotto).getQuantitaPremio() - 1);
                return Optional.ofNullable(prodotto.ottieniProdotto());
            }
        }
        return null;
    }

    /**
     * Classe annidata che rappresenta gli attributi di un premio (quantità e punti
     * associati al premio) presente nel catalogo.
     */
    class AttributiPremio{
        private int quantita;
        private int punti;

        public AttributiPremio(int quantita, int punti) {
            this.quantita = quantita;
            this.punti = punti;
        }

        public int getQuantitaPremio() {
            return quantita;
        }

        public void setQuantitaPremio(int quantita) {
            this.quantita = quantita;
        }

        public int getPuntiPremio() {
            return punti;
        }

        public void setPuntiPremio(int punti) {
            this.punti = punti;
        }
    }
}

