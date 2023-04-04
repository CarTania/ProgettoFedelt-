package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Rappresenta un programma a punti: a questa tipologia di programma può essere associato un catalogo
 * opzionale. Se questo non è presente, i punti possono essere utilizzati per ottenere uno sconto da
 * utilizzare presso l'azienda presso cui è attivo il programma.
 */
public class ProgrammaPunti extends ProgrammaFedelta{
    private int puntiCliente;
    private final double costanteCambio;
    private final Optional<Catalogo> catalogoOpzionale;
    public ProgrammaPunti(LocalDate dataScadenza, double costanteCambio, Optional<Catalogo> catalogoOpzionale) {
        super(LocalDate.now(), dataScadenza);
        this.costanteCambio = costanteCambio;
        this.puntiCliente = 0;
        this.catalogoOpzionale = catalogoOpzionale;
    }

    private void incrementaPunti(int punti)
    {
        this.puntiCliente += punti;
    }

    private void decrementaPunti(int punti)
    {
        this.puntiCliente -= punti;
    }


    public void ottieniPunti(Acquisto acquisto)
    {
        this.puntiCliente += acquisto.totaleAcquisto()/costanteCambio;
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
            Optional <Prodotto> premio = this.catalogoOpzionale.get().emettiPremio(prodotto);
            int puntiDaScalare = catalogoOpzionale.get().getListapremi().get(prodotto).getPuntiPremio();
            this.decrementaPunti(puntiDaScalare);
            return premio;
        }
        return Optional.empty();
    }

    /**
     * Genera un coupon sconto con i punti che si intendono utilizzare. Lo sconto viene calcolato usando
     * una percentuale di cambio decisa dal titolare.
     * @param punti i punti che si intendono utilizzare
     * @param dataScadenza la scadenza del coupon generato.
     * @return il coupon generato.
     */
    public Coupon generaCoupon(int punti, LocalDate dataScadenza){
        Coupon c = new Coupon(dataScadenza, punti*costanteCambio);
        this.decrementaPunti(punti);
        return c;
    }
}
