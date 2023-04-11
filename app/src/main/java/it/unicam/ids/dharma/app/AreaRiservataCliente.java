package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;


/**
 * La classe rappresenta l'area riservata di un cliente: tramite questa il cliente effettua le
 * operazioni di suo interesse.
 */
public class AreaRiservataCliente implements ICliente {
    private final Cliente cliente;
    private final List<ProgrammaFedelta> programmiAttivati;
    private final List<VantaggioFedelta> vantaggiRiscattati;
    private final List<Acquisto> acquistiEffettuati;

    public AreaRiservataCliente(Cliente cliente) {
        this.cliente = Objects.requireNonNull(cliente);
        this.programmiAttivati = new ArrayList<>();
        this.vantaggiRiscattati = new ArrayList<>();
        this.acquistiEffettuati = new ArrayList<>();
    }

    @Override
    public void cercaProgrammaFedelta() {
        Scanner s = new Scanner(System.in);
        boolean ricercaInCorso = true;
        this.apriMenuRicerca();

        while (ricercaInCorso) {
            String userChoice = s.nextLine();
            if (userChoice.equals("1")) {
                System.out.println("Inserisci la tipologia del programma: ");
                this.cercaPerTipologia(s.nextLine());
                ricercaInCorso = false;

            } else if (userChoice.equals("2")) {
                System.out.println("Inserisci il nome dell'azienda: ");
                this.cercaPerAzienda(s.nextLine());
                ricercaInCorso = false;
            }
            System.out.println("Reinserire modalità ricerca.");
        }


    }

    /**
     * Il metodo permette di aprire il menù di ricerca di un programma fedeltà.
     */
    @Override
    public void apriMenuRicerca() {
        String[] vociMenu = {"1. Ricerca per tipologia", "2. Ricerca per azienda"};

        for (String menu : vociMenu) System.out.println(menu);
    }


    @Override
    public void cercaPerAzienda(String nomeAzienda) {
        List<ProgrammaFedelta> programmiAzienda =
            GestoreDB.ottieniProgrammiAzienda(nomeAzienda);
        if(!programmiAzienda.isEmpty())
            for (ProgrammaFedelta p:
                 programmiAzienda) {
                System.out.println(p);
            }
    }


    @Override
    public void cercaPerTipologia(String tipologia) {
        List<ProgrammaFedelta> programmiTipologia = GestoreDB.ottieniProgrammiTipologia(tipologia);
        assert programmiTipologia != null;
        if (!programmiTipologia.isEmpty())
            for (ProgrammaFedelta p:
                 programmiTipologia) {
                System.out.println(p);
            }
    }


    /**
     * Il metodo permette di effettuare un acquisto dal magazzino online.
     * Il metodo prende in input i nomi dei prodotti inseriti dal cliente e se presenti li aggiunge ad un nuovo acquisto.
     * L'acquisto viene aggiunto agli acquisti effettuati dal cliente.
     */

    @Override
    public void effettuaAcquisto() {
        Scanner s = new Scanner(System.in);
        Acquisto acquisto = new Acquisto(LocalDate.now(), this.cliente);
        while (s.hasNext()) {
            String nomeProdotto = s.nextLine();
            if (Magazzino.getMagazzino().ricercaProdotto(nomeProdotto).isPresent()) {
                Optional<Prodotto> prodottoDaAcquistare =
                    Magazzino.getMagazzino().prelevaProdotto(
                        Magazzino.getMagazzino().ricercaProdotto(nomeProdotto).get()
                    );
                prodottoDaAcquistare.ifPresent(acquisto::aggiungiProdotto);
            }
        }
        acquistiEffettuati.add(acquisto);
    }


    @Override
    public void cronologiAcquisti() {
        for (Acquisto acquisto : acquistiEffettuati) {
            System.out.println(acquisto);
        }
    }

    /**
     * Visualizza il catalogo a premi del programma a punti attivato dal cliente (se ha attivato un
     * programma a punti). Se il cliente non ha attivato nessun programma a punti, viene lanciata
     * una <code>IllegalArgumentException</code>.
     */
    @Override
    public void visualizzaCatalogoPremi() {
        for (ProgrammaFedelta p : programmiAttivati) {
                if (p instanceof ProgrammaPunti programmaPunti) {
                    if (programmaPunti.getCatalogoOpzionale().isPresent()) {
                        programmaPunti.getCatalogoOpzionale().get().getListapremi()
                            .forEach(System.out::println);
                    }
                    return;
                }
            }
        throw new IllegalArgumentException("Nessun programma a punti attivato.");
    }

    /**
     * Il metodo ha l'obiettivo di permettere al cliente di riscattare il vantaggio dal programma punti:
     * al cliente è richiesto di selezionare una delle modalità di riscatto del vantaggio (premio dal catalogo oppure un coupon
     * sconto).
     *
     * @param p     rappresenta il Programma a punti.
     * @param punti rappresenta i punti che il cliente vuole utilizzare per riscattare il vantaggio.
     */

    @Override
    public void riscattaVantaggioProgrammaPunti(ProgrammaPunti p, int punti) {
        Scanner s = new Scanner(System.in);
        boolean acquisizioneInput = true;
        String inputCliente;


        while (acquisizioneInput) {
            System.out.println("Seleziona una delle seguenti opzioni:/n " +
                "1. Riscatta un premio /n 2. Ottieni Coupon Sconto");
            inputCliente = s.nextLine();
            if (inputCliente.equals("1")) {
                List<Premio> premiRiscattabili = p.premiRiscattabiliCliente(this.cliente, punti);
                this.visualizzaPremiRiscattabili(premiRiscattabili);
                String inputPremio;
                System.out.println("Inserisci il nome del premio che vuoi riscattare: ");
                inputPremio = s.nextLine();
                boolean premioPresente = false;
                for (Premio premio: premiRiscattabili) {
                    if (premio.getPremio().getNome().equals(inputPremio)) {
                        this.riscattaPremio(premio, p);
                        System.out.println("Hai riscattato il seguente premio: " + premio.getPremio().getNome());
                        premioPresente = true;
                        acquisizioneInput = false;
                    }
                }
                if (!premioPresente)
                    System.out.println("Premio non presente: seleziona un altro premio.");
            }
            if (inputCliente.equals("2")) {
                this.ottieniCouponSconto(p, punti);
                acquisizioneInput = false;
            }
        }
    }

    private void visualizzaPremiRiscattabili(List<Premio> premiRiscattabili) {
        for (Premio p : premiRiscattabili) {
            System.out.println("Premio: " + p.getPremio().getNome());
        }
    }

    /** Questo metodo permette di riscattare un premio da un programma a punti attivato.
     * @param p rappresenta il premio.
     * @param programmaPunti il programma a punti attivato.
     */

    @Override
    public void riscattaPremio(Premio p, ProgrammaPunti programmaPunti) {

        Optional<Premio> premio = programmaPunti.riscattoPremioCatalogo(cliente, p);
        premio.ifPresent(this.vantaggiRiscattati::add);

    }

    /** Questo metodo permettere di ottenere un coupon Sconto.
     * @param punti rappresenta i punti del cliente.
     * @param programmaPunti rappresenta il programma Punti a cui il cliente è iscritto.
     */
    public void ottieniCouponSconto(ProgrammaPunti programmaPunti, int punti) {
        Coupon coupon = programmaPunti.generaCoupon(this.cliente, punti, programmaPunti.getDataScadenza());
        this.vantaggiRiscattati.add(coupon);
    }

    @Override
    public void attivaProgrammaPunti() {
        Scanner s = new Scanner(System.in);
        String input;
        boolean inserimentoInCorso = true;
        this.cercaProgrammaFedelta();
        System.out.println("Inserisci l'id del programma da attivare: ");

        while (inserimentoInCorso) {
            input = s.nextLine();
            Optional<ProgrammaFedelta> programmaSelezionato =
                GestoreDB.ottieniProgrammaFedelta(Integer.parseInt(input));

            if (programmaSelezionato.isPresent()) {
                if (programmaSelezionato.get() instanceof ProgrammaPunti p) {
                    GestoreDB.attivaProgrammaPunti(p, this.cliente);
                    this.programmiAttivati.add(p);
                    inserimentoInCorso = false;
                }
            }
        }
    }

    @Override
    public void iniziaSessione() {
        Scanner s = new Scanner(System.in);
        System.out.println("Area riservata di "+ this.cliente.getName()+". Premere ! per terminare"+
            " la sessione.");
        String input = "";
        while(!input.equals("!")){
            System.out.println("Scegli l'operazione da effettuare:\n1.Attiva un programma a punti\n"+
                "2. Cerca programma fedeltà");
            input = s.nextLine();
            if(input.equals("1"))
                this.cercaProgrammaFedelta();
            else if (input.equals("2")) {
                this.attivaProgrammaPunti();
            }
        }
    }

    public void visualizzaVantaggiRiscattati(){
        for (VantaggioFedelta v:
             vantaggiRiscattati) {
            System.out.println(v);
        }
    }
}
