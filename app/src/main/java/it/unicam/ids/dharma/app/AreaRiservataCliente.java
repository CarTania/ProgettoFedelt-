package it.unicam.ids.dharma.app;

import java.util.List;

public class AreaRiservataCliente implements ICliente{

    private Cliente cliente;

    private List<ProgrammaFedelta> programmiAttivo;

    private List <Prodotto> prodottiAcquistati;

    @Override
    public void cercaProgrammaFedelta() {

    }

    @Override
    public void apriMenuRicerca() {

    }

    @Override
    public void cercaPerAzienda() {

    }

    @Override
    public void selezionaAzienda(String nomeAzienda) {

    }

    @Override
    public void selezionaProgramma(int idProgramma) {

    }

    @Override
    public void cercaPerTipologia() {

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

    }
}
