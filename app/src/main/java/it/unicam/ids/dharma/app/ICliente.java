package it.unicam.ids.dharma.app;

public interface ICliente {
    public void cercaProgrammaFedelta();
    public void apriMenuRicerca();
    public void cercaPerAzienda();
    public void selezionaAzienda(String nomeAzienda);
    public void selezionaProgramma (int idProgramma);
    public void cercaPerTipologia();
    public void selezionaTipologiaProgramma(String tipologia);
    public void mostraElencoAziende();
    public void mostraElencoProgrammiAzienda (String nomeAzienda);
    public void mostraDettagliProgramma (int idProgramma);
    public void mostraElencoTipologie();
    public void mostraElencoProgrammiTipologia (String tipologia);
    public void iniziaRegistrazione();
    public void accettaContratto();
    public void inserisciDati(String nome, String cognome, String username, String password);
    public void richiediDatiCliente();
    public void inviaDatiCliente(String nome, String cognome, String username, String password);
    public boolean confermaRegistrazione();
    public boolean annullaRegistrazione();
    public void iniziaDisiscrizione();
    public boolean confermaVolontaDisiscrizionePiattaforma();
    public void visualizzaMessaggioAllerta();
    public void chiediConfermaDisiscrizioneProgramma();
    public void confermaRichiestaDisiscrizioneProgramma();
    public void acquistaProdotto(Prodotto prodotto);
    public void riepilogoOrdini();

    public void cronologiAcquisti();
}

