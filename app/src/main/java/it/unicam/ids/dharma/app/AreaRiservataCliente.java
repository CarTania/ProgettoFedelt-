package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import static it.unicam.ids.dharma.app.GestoreProgrammiFedelta.getGestoreProgrammi;

/**
 * La classe rappresenta l'area riservata di un cliente: tramite questa il cliente effettua le
 * operazioni di suo interesse.
 */
public class AreaRiservataCliente implements ICliente{

    private Cliente cliente;

    private List<ProgrammaFedelta> programmiAttivati;

    private List <Acquisto> acquistiEffettuati;
    @Override
    public void cercaProgrammaFedelta() {
        Scanner s = new Scanner(System.in);

        this.apriMenuRicerca();
        String userChoice = s.nextLine();
        if(userChoice.equals("1")){
            System.out.println("Inserisci la tipologia del programma: ");
            this.cercaPerTipologia(s.nextLine());

        } else if (userChoice.equals("2")) {
            System.out.println("Inserisci il nome dell'azienda: ");
            this.cercaPerAzienda(s.nextLine());
        }else
            throw new IllegalArgumentException("Inserito nome/tipologia non valido");
    }

    /**
     * Il metodo permette di aprire il menù di ricerca di un programma fedeltà.
     */
    @Override
    public void apriMenuRicerca() {
        String[] vociMenu= {"1. Ricerca per tipologia", "2. Ricerca per azienda"};

        for (int i= 0; i<vociMenu.length; i++)
            System.out.println (vociMenu[i]);
    }

    /**
     * Il metodo legge la scelta in input del cliente e la ritorna.
     * @return la scelta effettuata dal cliente.
     */
    public String leggiInput(){
        String input;
        System.out.println("Scelta => ");
        Scanner scanner = new Scanner(System.in);
        input= scanner.nextLine();
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
     * @param idProgramma è un intero che rappresenta l'id del programma fedeltà.
     */
    @Override
    public Optional<ProgrammaFedelta> selezionaProgramma(int idProgramma) {

        Predicate<Integer> p= i -> i ==idProgramma;

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
     * @param tipologia è una stringa che rappresenta la tipologia.
     */
    @Override
    public void selezionaTipologiaProgramma(String tipologia) {
        Predicate<String> p= t -> t.equals(tipologia);
        Optional<ProgrammaFedelta> programmaSelezionato= GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElemento(p);
    }

    /**
     * Questo metodo permette di visualizzare i programmi attivati dall'azienda.
     * @param nomeAzienda è una stringa che rappresenta il nome dell'azienda.
     */

    @Override
    public void mostraElencoProgrammiAzienda(String nomeAzienda) {
        Predicate<String> p= t -> t.equals(nomeAzienda);
        Optional<List<ProgrammaFedelta>> elencoProgrammi= GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
        if(elencoProgrammi.isPresent()) {
            for (ProgrammaFedelta programma : elencoProgrammi.get()) {
                System.out.println(programma);
            }
        }
    }

    @Override
    public void mostraDettagliProgramma(int idProgramma) {

        Optional<ProgrammaFedelta> programma= this.selezionaProgramma(idProgramma);
        programma.ifPresent(System.out::println);
    }


    @Override
    public void mostraElencoProgrammiPerTipologia(String tipologia) {
        Predicate<String> p= t -> t.equals(tipologia);
        Optional<List<ProgrammaFedelta>> elencoProgrammi= GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
        if(elencoProgrammi.isPresent()) {
            for (ProgrammaFedelta programma : elencoProgrammi.get()) {
                System.out.println(programma);
            }
        }

    }


    @Override
    public void effettuaAcquisto(Prodotto prodotto) {




    }


    @Override
    public void cronologiAcquisti() {
        for (Acquisto acquisto: acquistiEffettuati) {
            System.out.println(acquisto);
        }
    }

    @Override
    public void visualizzaCatalogoPremi(int idProgramma) {

    }

    @Override
    public void riscattaVantaggio(ProgrammaPunti p, int punti) {

    }

    @Override
    public void riscattaPremio(Prodotto p) {

    }

    public void ottieniCouponSconto(int punti){

    }

}
