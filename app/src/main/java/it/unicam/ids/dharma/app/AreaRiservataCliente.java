package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static it.unicam.ids.dharma.app.GestoreProgrammiFedelta.getGestoreProgrammi;

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

        this.apriMenuRicerca();
        String userChoice = s.nextLine();
        if (userChoice.equals("1")) {
            System.out.println("Inserisci la tipologia del programma: ");
            this.cercaPerTipologia(s.nextLine());

        } else if (userChoice.equals("2")) {
            System.out.println("Inserisci il nome dell'azienda: ");
            this.cercaPerAzienda(s.nextLine());
        } else
            throw new IllegalArgumentException("Inserito nome/tipologia non valido");
    }

    /**
     * Il metodo permette di aprire il menù di ricerca di un programma fedeltà.
     */
    @Override
    public void apriMenuRicerca() {
        String[] vociMenu = {"1. Ricerca per tipologia", "2. Ricerca per azienda"};

        for (int i = 0; i < vociMenu.length; i++)
            System.out.println(vociMenu[i]);
    }

    /**
     * Il metodo legge la scelta in input del cliente e la ritorna.
     *
     * @return la scelta effettuata dal cliente.
     */
    public String leggiInput() {
        String input;
        System.out.println("Scelta => ");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }


    @Override
    public void cercaPerAzienda(String nomeAzienda) {
        //predicato che verifica l'uguaglianza con la stringa passata dall'utente.
        Predicate<String> matchNomeAzienda = n -> (n.equals(nomeAzienda));
        //il gestore dei programmi ritorna la lista dei programmi attivati da una determinata azienda.
        Optional<List<ProgrammaFedelta>> risultatoRicerca = getGestoreProgrammi().ottieniElenco(matchNomeAzienda);
    }

    /**
     * Questo metodo permette di selezionare un programma tra quelli attivabili da un cliente.
     *
     * @param idProgramma è un intero che rappresenta l'id del programma fedeltà.
     */
    @Override
    public Optional<ProgrammaFedelta> selezionaProgramma(int idProgramma) {

        Predicate<Integer> p = i -> i == idProgramma;

        return GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElemento(p);

    }

    @Override
    public void cercaPerTipologia(String tipologia) {
        Predicate<String> matchTipologia = n -> (n.equals(tipologia));
        //il gestore dei programmi ritorna la lista dei programmi attivati di una determinata tipologia.
        Optional<List<ProgrammaFedelta>> risultatoRicerca = getGestoreProgrammi().ottieniElenco(matchTipologia);
    }


    /**
     * Questo metodo permette di selezionare un programma in base alla tipologia tra quelli attivabili da un cliente.
     *
     * @param tipologia è una stringa che rappresenta la tipologia.
     */
    @Override
    public void selezionaTipologiaProgramma(String tipologia) {
        Predicate<String> p = t -> t.equals(tipologia);
        Optional<ProgrammaFedelta> programmaSelezionato = GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElemento(p);
    }

    /**
     * Questo metodo permette di visualizzare i programmi attivati dall'azienda.
     *
     * @param nomeAzienda è una stringa che rappresenta il nome dell'azienda.
     */

    @Override
    public void mostraElencoProgrammiAzienda(String nomeAzienda) {
        Predicate<String> p = t -> t.equals(nomeAzienda);
        Optional<List<ProgrammaFedelta>> elencoProgrammi = GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
        if (elencoProgrammi.isPresent()) {
            for (ProgrammaFedelta programma : elencoProgrammi.get()) {
                System.out.println(programma);
            }
        }
    }

    @Override
    public void mostraDettagliProgramma(int idProgramma) {

        Optional<ProgrammaFedelta> programma = this.selezionaProgramma(idProgramma);
        programma.ifPresent(System.out::println);
    }


    @Override
    public void mostraElencoProgrammiPerTipologia(String tipologia) {
        Predicate<String> p = t -> t.equals(tipologia);
        Optional<List<ProgrammaFedelta>> elencoProgrammi = GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
        if (elencoProgrammi.isPresent()) {
            for (ProgrammaFedelta programma : elencoProgrammi.get()) {
                System.out.println(programma);
            }
        }

    }

    /**
     * Il metodo permette di effettuare un acquisto dal magazzino.
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
     * @param idProgramma
     */

    @Override
    public void visualizzaCatalogoPremi(int idProgramma) {
        for (ProgrammaFedelta p : programmiAttivati) {
            if (p.getId() == idProgramma) {
                if (p instanceof ProgrammaPunti programmaPunti) {
                    if (programmaPunti.getCatalogoOpzionale().isPresent()) {
                        programmaPunti.getCatalogoOpzionale().get().getListapremi()
                            .forEach(System.out::println);
                    }
                }
            }
        }

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

    /** Questo metodo permette di riscattare un premio.
     * @param p rappresenta il premio.
     * @param programmaPunti
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
}
