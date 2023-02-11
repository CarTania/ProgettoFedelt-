package it.unicam.ids.dharma.app;

public interface ICliente {
    /**
     * Il metodo si occupa di cercare un programma fedeltà tra tutti quelli attivabili.
     * E' possibile ricercare un programma effettuando una ricerca per azienda o per
     * tipologia di programma.
     */
    public void cercaProgrammaFedelta();
    public void apriMenuRicerca();
    public void cercaPerAzienda(String nomeAzienda);
    public void selezionaAzienda(String nomeAzienda);
    public void selezionaProgramma (int idProgramma);
    public void cercaPerTipologia(String tipologia);
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
    public void cronologiAcquisti();
}

