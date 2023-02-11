package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import static it.unicam.ids.dharma.app.GestoreProgrammiFedelta.getGestoreProgrammi;


public class AreaRiservataCliente implements ICliente{

    private Cliente cliente;

    private List<ProgrammaFedelta> programmiAttivo;

    private List <Prodotto> prodottiAcquistati;

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

    @Override
    public void apriMenuRicerca() {
        String[] vociMenu= {"1. Ricerca per tipologia", "2. Ricerca per azienda"};

        for (int i= 0; i<vociMenu.length; i++)
            System.out.println (vociMenu[i]);
    }

    public String effettuaScelta(){
        String effettuaScelta;
        System.out.println("Scelta => ");
        Scanner scanner = new Scanner(System.in);
        effettuaScelta= scanner.nextLine();
        return effettuaScelta;

    }


    @Override
    public void cercaPerAzienda(String nomeAzienda) {
        //predicato che verifica l'uguaglianza con la stringa passata dall'utente.
        Predicate<String> matchNomeAzienda = n -> (n.equals(nomeAzienda));
        //il gestore dei programmi ritorna la lista dei programmi attivati da una determinata azienda.
        Optional<List<ProgrammaFedelta>> risultatoRicerca = getGestoreProgrammi().ottieniElenco(matchNomeAzienda);
    }

    @Override
    public void selezionaAzienda(String nomeAzienda) {

    }

    @Override
    public void selezionaProgramma(int idProgramma) {

    }

    @Override
    public void cercaPerTipologia(String tipologia) {
        Predicate<String> matchTipologia = n -> (n.equals(tipologia));
        //il gestore dei programmi ritorna la lista dei programmi attivati di una determinata tipologia.
        Optional<List<ProgrammaFedelta>> risultatoRicerca = getGestoreProgrammi().ottieniElenco(matchTipologia);
    }

    @Override
    public void selezionaTipologiaProgramma(String tipologia) {

    }

    @Override
    public void mostraElencoAziende() {

    }

    @Override
    public void mostraElencoProgrammiAzienda(String nomeAzienda) {

    }

    @Override
    public void mostraDettagliProgramma(int idProgramma) {

    }

    @Override
    public void mostraElencoTipologie() {

        }

    @Override
    public void mostraElencoProgrammiTipologia(String tipologia) {

    }

    @Override
    public void iniziaRegistrazione() {

    }

    @Override
    public void accettaContratto() {

    }

    @Override
    public void inserisciDati(String nome, String cognome, String username, String password) {

    }

    @Override
    public void richiediDatiCliente() {

    }

    @Override
    public void inviaDatiCliente(String nome, String cognome, String username, String password) {

    }

    @Override
    public boolean confermaRegistrazione() {
        return false;
    }

    @Override
    public boolean annullaRegistrazione() {
        return false;
    }

    @Override
    public void iniziaDisiscrizione() {

    }

    @Override
    public boolean confermaVolontaDisiscrizionePiattaforma() {
        return false;
    }

    @Override
    public void visualizzaMessaggioAllerta() {

    }

    @Override
    public void chiediConfermaDisiscrizioneProgramma() {

    }

    @Override
    public void confermaRichiestaDisiscrizioneProgramma() {

    }

    @Override
    public void acquistaProdotto(Prodotto prodotto) {

    }

    @Override
    public void riepilogoOrdini() {

    }

    @Override
    public void cronologiAcquisti() {
        for (Prodotto prodotto : prodottiAcquistati) {
            System.out.println(prodotto);
        }
    }
}
