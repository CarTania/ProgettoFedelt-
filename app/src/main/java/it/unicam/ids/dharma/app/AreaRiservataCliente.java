package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import static it.unicam.ids.dharma.app.GestoreProgrammiFedelta.getGestoreProgrammi;

/**
 * La classe rappresenta l'area riservata di un cliente: tramite questa il cliente effettua le
 * operazioni di suo interesse.
 */
public class AreaRiservataCliente implements ICliente {
    private Cliente cliente;
    private List<ProgrammaFedelta> programmiAttivati;
    private List <VantaggioFedelta> vantaggiRiscattati;
    private List<Acquisto> acquistiEffettuati;

    //TODO Aggiungere costruttore.

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
     *
     * @param magazzino prodotti in vendita.
     * @throws CloneNotSupportedException lancia l'eccezione.
     */

    @Override
    public void effettuaAcquisto(Magazzino magazzino) throws CloneNotSupportedException {
        Scanner s = new Scanner(System.in);
        Acquisto acquisto = new Acquisto(LocalDate.now(), this.cliente);
        while (s.hasNext()) {
            String nomeProdotto = s.nextLine();
            if (magazzino.ricercaProdotto(nomeProdotto).isPresent()) {
                Prodotto p = magazzino.ricercaProdotto(nomeProdotto).get().ottieniProdotto();
                acquisto.aggiungiProdotto(p);
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
                                .forEach((key, value) -> System.out.println("Premio: " + key
                                        + ", Punti: " + value.getPuntiPremio()
                                        + ", Quantità: " + value.getQuantitaPremio()));
                    }
                }
            }
        }

    }


    /**
     * Il metodo ha l'obiettivo di permettere al cliente di riscattare il vantaggio dal programma punti:
     * al cliente è richiesto di selezionare una delle modalità di riscatto del vantaggio (premio dal catalogo oppure un coupon
     * sconto).
     * @param p rappresenta il Programma a punti.
     * @param punti rappresenta i punti che il cliente vuole utilizzare per riscattare il vantaggio.
     */

    @Override
    public void riscattaVantaggioProgrammaPunti(ProgrammaPunti p, int punti) {

        Scanner s = new Scanner(System.in);
        String inputCliente;

        while (true) {
            System.out.println("Seleziona una delle seguenti opzioni: /n 1. Riscatta un premio /n 2. Ottieni Coupon Sconto");
            inputCliente = s.nextLine();
            if (inputCliente.equals("1")) {
                List<Prodotto> premiRiscattabili = p.premiRiscattabiliCliente(this.cliente, punti);
                this.visualizzaPremiRiscattabil(premiRiscattabili);
                String inputPremio;
                System.out.println("Inserisci il nome del premio che vuoi riscattare: ");
                inputPremio = s.nextLine();
                for (Prodotto prodotto : premiRiscattabili) {
                    if (prodotto.getNome().equals(inputPremio)) {
                        this.riscattaPremio(prodotto);
                        System.out.println("Hai riscattato il seguente premio: " + prodotto.getNome());
                    }
                }
                System.out.println("Premio non presente: seleziona un altro premio.");
            }
            if (inputCliente.equals("2")) {
                this.ottieniCouponSconto(p, punti);
                break;
            }
        }
    }

    private void visualizzaPremiRiscattabil(List<Prodotto> premiRiscattabili) {
        for (Prodotto p : premiRiscattabili) {
            System.out.println("Premio: " + p.getNome());
        }
    }

    /**
     *
     * @param p
     */

    @Override
    public void riscattaPremio(Prodotto p) {



    }

    /**
     *
     * @param punti
     */
    public void ottieniCouponSconto(ProgrammaPunti programmaPunti, int punti)
    {
        Coupon coupon= programmaPunti.generaCoupon(this.cliente, punti, programmaPunti.getDataScadenza());
        this.vantaggiRiscattati.add(coupon);
    }

}
