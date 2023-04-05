package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;

/**
 * Rappresenta un programma a punti: a questa tipologia di programma può essere associato un catalogo
 * opzionale. Se questo non è presente, i punti possono essere utilizzati per ottenere un coupon sconto da
 * utilizzare presso l'azienda che ha attivato il programma.
 */
public class ProgrammaPunti extends ProgrammaFedelta{
    private final Map<Cliente, Integer> clientiIscritti;
    private final double costanteCambio;
    private final Optional<Catalogo> catalogoOpzionale;

    /**
     * Costruisce un programma a punti con un catalogo premi associato.
     *
     * @param dataScadenza   la data di scadenza del programma.
     * @param costanteCambio la costante di cambio, decisa dal titolare, per convertire i punti in euro (e viceversa).
     * @param catalogo       il catalogo da associare al programma a punti.
     */
    public ProgrammaPunti(LocalDate dataScadenza, double costanteCambio, Catalogo catalogo) {
        super(LocalDate.now(), dataScadenza);
        if (costanteCambio <= 0)
            throw new IllegalArgumentException("La costante di cambio deve essere positiva.");
        this.clientiIscritti = new HashMap<>();
        this.costanteCambio = costanteCambio;
        this.catalogoOpzionale = Optional.of(catalogo);
    }

    /**
     * Costruisce un programma a punti senza un catalogo premi associato.
     *
     * @param dataScadenza   la data di scadenza del programma.
     * @param costanteCambio la costante di cambio, decisa dal titolare, per convertire i punti in euro (e viceversa).
     */
    public ProgrammaPunti(LocalDate dataScadenza, double costanteCambio) {
        super(LocalDate.now(), dataScadenza);
        if (costanteCambio <= 0)
            throw new IllegalArgumentException("La costante di cambio deve essere positiva.");
        this.clientiIscritti = new HashMap<>();
        this.costanteCambio = costanteCambio;
        this.catalogoOpzionale = Optional.empty();
    }

    /**
     * Aggiunge un nuovo cliente al programma a punti: i punti associati al cliente sono inizializzati a zero.
     *
     * @param cliente il cliente da aggiungere al programma a punti.
     * @return true se il cliente è stato aggiunto correttamente al programma, false altrimenti.
     */
    public boolean aggiungiCliente(Cliente cliente) {
        if (!clientiIscritti.containsKey(cliente)) {
            clientiIscritti.put(cliente, 0);
            return true;
        }
        return false;
    }

    /**
     * Incrementa i punti di un cliente iscritto al programma a punti.
     *
     * @param cliente il cliente che ottiene l'incremento dei punti.
     * @param punti   l'ammontare di punti da aggiungere a quel cliente.
     */
    private void incrementaPuntiCliente(Cliente cliente, int punti) {
        clientiIscritti.computeIfPresent(cliente, (c, p) -> p + punti);
    }

    /**
     * Decrementa i punti di un cliente iscritto al programma a punti. Se il numero di punti del cliente è inferiore
     * al numero di punti da decrementare, viene lanciata un <code>IllegalArgumentException</code>.
     *
     * @param cliente il cliente a cui vengono decrementati i punti.
     * @param punti   l'ammontare di punti da scalare a quel cliente.
     */
    private void decrementaPuntiCliente(Cliente cliente, int punti) {
        if (clientiIscritti.get(cliente) >= punti) {
            clientiIscritti.computeIfPresent(cliente, (c, p) -> p - punti);
        } else throw new IllegalArgumentException("Impossibile decrementare i punti: il cliente non possiede la" +
            "quantità necessaria di punti affinchè sia effettuato il decremento");
    }

    /**
     * Aumenta i punti di un cliente iscritto al programma a punti in seguito ad un acquisto effettuato. I punti da
     * assegnare vengono calcolati in base a una costante di cambio definita dal titolare al momento della creazione
     * del programma.
     *
     * @param cliente  il cliente che ottiene i punti.
     * @param acquisto l'acquisto effettuato dal cliente.
     */
    public void ottieniPunti(Cliente cliente, Acquisto acquisto) {
        int puntiOttenuti = (int) Math.round(acquisto.totaleAcquisto() / costanteCambio);
        this.incrementaPuntiCliente(cliente, puntiOttenuti);
    }


    /**
     * Permette di ottenere la lista dei premi del catalogo che un cliente può riscattare con i punti da lui
     * indicati.
     *
     * @param punti   punti che il cliente vuole usare per riscattare un premio.
     * @param cliente il cliente che vuole riscattare il premio.
     * @return la lista dei premi riscattabili dal cliente con i punti indicati.
     */
    public List<Prodotto> premiRiscattabiliCliente(Cliente cliente, int punti) {
        if (catalogoOpzionale.isPresent()) {
            if (clientiIscritti.get(cliente) > punti) {
                return catalogoOpzionale.get().getListapremi().entrySet().stream()
                    .filter(e -> e.getValue().getPuntiPremio() <= punti)
                    .map(Map.Entry::getKey)
                    .toList();
            } else throw new IllegalArgumentException("Il cliente non possiede la quantità di punti indicata.");
        } else throw new IllegalStateException("Nessun catalogo associato a questo programma.");
    }


    /**
     * Permette a un cliente di riscattare un premio dal catalogo associato al programma, se presente.
     *
     * @param punti   punti che si vogliono utilizzare per riscattare un premio.
     * @param cliente il cliente che vuole riscattare un premio.
     * @return il premio ottenuto.
     */
    public Optional<Prodotto> riscattoPremioCatalogo(Cliente cliente, Prodotto prodotto, int punti)
        throws CloneNotSupportedException {
        if (premiRiscattabiliCliente(cliente, punti).contains(prodotto)) {
            if (catalogoOpzionale.isPresent()) {
                Optional<Prodotto> premio = this.catalogoOpzionale.get().emettiPremio(prodotto);
                int puntiDaScalare = catalogoOpzionale.get().getListapremi().get(prodotto).getPuntiPremio();
                this.decrementaPuntiCliente(cliente, puntiDaScalare);
                return premio;
            } else throw new IllegalStateException("Nessun catalogo associato a questo programma.");
        }
        return Optional.empty();
    }

    /**
     * Genera un coupon sconto per un cliente utilizzando i punti che quest'ultimo intende riscattare. Lo sconto
     * viene calcolato usando la percentuale di cambio decisa dal titolare.
     *
     * @param cliente      il cliente che vuole ottenere il coupon sconto.
     * @param punti        i punti che si intendono utilizzare
     * @param dataScadenza la scadenza del coupon generato.
     * @return il coupon generato.
     */
    public Coupon generaCoupon(Cliente cliente, int punti, LocalDate dataScadenza) {
        Coupon coupon = new Coupon(cliente, dataScadenza, punti * costanteCambio);
        this.decrementaPuntiCliente(cliente, punti);
        return coupon;
    }
}
