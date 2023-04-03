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
    public void selezionaProgramma (int idProgramma);
    public void cercaPerTipologia(String tipologia);
    public void selezionaTipologiaProgramma(String tipologia);
    public void mostraElencoProgrammiAzienda (String nomeAzienda);
    public void mostraDettagliProgramma (int idProgramma);
    public void mostraElencoTipologie();
    public void mostraElencoProgrammiPerTipologia(String tipologia);
    public void acquistaProdotto(Prodotto prodotto);
    public void cronologiAcquisti();
    public void visualizzaCatalogoPremi(int idProgramma);
    public void riscattaVantaggio(ProgrammaPunti p, int punti);
    public void riscattaPremio (Prodotto p);
    public void ottieniCouponSconto(int punti);

}

