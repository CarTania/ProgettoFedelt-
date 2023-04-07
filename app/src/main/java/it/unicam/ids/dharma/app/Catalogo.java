package it.unicam.ids.dharma.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Rappresenta un catalogo premi che può essere associato a un programma a punti.
 */
public class Catalogo {
    private final int id;
    private final List<Premio> listapremi;

    public Catalogo(int id) {
        this.id = id;
        this.listapremi = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Premio> getListapremi() {
        return listapremi;
    }

    /**
     * Inserisce un nuovo premio nel catalogo.
     *
     * @param premio il premio da inserire.
     * @return true se il premio è stato inserito correttamente, false altrimenti.
     */
    public boolean inserisciPremio(Premio premio) {
        if (!(listapremi.contains(premio))) {
            listapremi.add(premio);
            return true;
        }
        return false;
    }

    /**
     * Rimuove un premio dal catalogo.
     *
     * @param premio il premio da rimuovere.
     * @return true se il premio è stato rimosso correttamente, false altrimenti.
     */
    public boolean rimuoviPremio(Premio premio) {
        if (listapremi.contains(premio)) {
            listapremi.remove(premio);
            return true;
        }
        return false;
    }

    /**
     * Assegna un numero di punti diverso a uno specifico premio.
     *
     * @param premio il premio che si vuole modificare.
     * @param punti  la nuova quantità di punti che si vuole associare al premio.
     * @return true se l'aggiornamento è avvenuto con successo, false altrimenti.
     */
    public boolean aggiornaPuntiPremio(Premio premio, int punti) {
        if (listapremi.contains(premio)) {
            int indicePremio = listapremi.indexOf(premio);
            if (premio.getPuntiPremio() > punti) {
                int decremento = premio.getPuntiPremio() - punti;
                listapremi.get(indicePremio).decrementaPuntiPremio(decremento);
                return true;
            } else if (premio.getPuntiPremio() < punti) {
                int aumento = punti - premio.getPuntiPremio();
                listapremi.get(indicePremio).aumentaPuntiPremio(aumento);
                return true;
            }
        }
        return false;
    }

    /**
     * Assegna un numero di unità diverso a uno specifico premio.
     *
     * @param premio   il premio che si vuole modificare.
     * @param quantita il nuovo numero di unità di quel premio.
     * @return true se l'aggiornamento è avvenuto con successo, false altrimenti.
     */
    public boolean aggiornaQuantitaPremio(Premio premio, int quantita) {
        if (listapremi.contains(premio)) {
            int indicePremio = listapremi.indexOf(premio);

            if (listapremi.get(indicePremio).getQuantitaPremio() < quantita) {
                int aumento = quantita - listapremi.get(indicePremio).getQuantitaPremio();
                if(Magazzino.getMagazzino().getProdottiDisponibili().get(premio.getPremio()) >= aumento){
                    listapremi.get(indicePremio).aumentaQuantitaPremio(aumento);
                    return true;
                }else
                    return false;
            } else if (listapremi.get(indicePremio).getQuantitaPremio() > quantita
                && listapremi.get(indicePremio).getQuantitaPremio() - quantita > 0) {
                int decremento = listapremi.get(indicePremio).getQuantitaPremio() - quantita;
                listapremi.get(indicePremio).decrementaQuantitaPremio(decremento);
                return true;
            }
        }
        return false;
    }

    /**
     * Il metodo viene chiamato quando un premio viene riscosso da un cliente.
     * La quantità dello specifico premio viene decrementata di un' unità e il prodotto
     * viene restituito (se ci sono ancora unità disponibili).
     *
     * @param premio il premio che deve essere emesso.
     * @return il premio, se disponibile.
     */
    public Optional<Prodotto> emettiPremio(Premio premio) {
        if (listapremi.contains(premio)) {
            int indicePremio = listapremi.indexOf(premio);
            //se il premio è disponibile nel catalogo
            if (listapremi.get(indicePremio).getQuantitaPremio() != 0) {
                listapremi.get(indicePremio).decrementaQuantitaPremio(1);
                return Optional.of(listapremi.get(indicePremio).getPremio());
            }
        }
        return Optional.empty();
    }
}

