package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgrammaPunti extends ProgrammaFedelta{

    public ProgrammaPunti(LocalDate dataAttivazione, LocalDate dataScadenza) {
        super(dataAttivazione, dataScadenza);
    }

    private int punti;

    private Optional<Catalogo> catalogoOpzionale;

    public ProgrammaPunti(LocalDate dataAttivazione, LocalDate dataScadenza, int punti, Optional<Catalogo> catalogoOpzionale) {
        super(dataAttivazione, dataScadenza);
        this.punti = punti;
        this.catalogoOpzionale = catalogoOpzionale;
    }

    private void aggiungiPunti(int punti)
    {
        this.punti+= punti;
    }

    private void rimuoviPunti(int punti)
    {
        this.punti-= punti;
    }

    public void aggiornaPunti(Acquisto acquisto)
    {
        double spesa= acquisto.totaleAcquisto();

        if(spesa <= 10)
        {
            this.aggiungiPunti((int) spesa);
        }
        if (spesa > 10 && spesa <= 20)
        {
            this.aggiungiPunti((int)spesa*2);
        }

        if (spesa > 20 && spesa <= 50)
        {
            this.aggiungiPunti((int)spesa*3);
        }
        if (spesa > 50 && spesa <= 100)
        {
            this.aggiungiPunti((int)spesa*4);
        }

    }


    /**
     * Questo metodo permette di selezionare dal catalogo la lista dei premi che è possibile riscattare con i punti
     * indicati.
     * @param punti punti indicati.
     * @return la lista dei premi riscattabili.
     */
    public List<Prodotto> premiRiscattabili(int punti)
    {
        return  catalogoOpzionale.get().getListapremi().entrySet().stream()
                .filter(e -> e.getValue().getPuntiPremio()<= punti)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

    }


    /**
     * Questo metodo permette di riscattare un premio dal catalogo associato al programma.
     * @param punti punti che si vogliono utilizzare per riscattare un premio.
     * @return il premio ottenuto.
     */

    public Optional<Prodotto> riscattoPremioCatalogo (int punti, Prodotto prodotto) throws CloneNotSupportedException {

        if (premiRiscattabili(punti).contains(prodotto))
        {
            Optional <Prodotto> premio= this.catalogoOpzionale.get().emettiPremio(prodotto);
            punti-=catalogoOpzionale.get().getListapremi().get(prodotto).getPuntiPremio();
            return premio;
        }
        return Optional.empty();
    }

}
