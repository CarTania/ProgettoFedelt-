package it.unicam.ids.dharma.app;

import java.util.List;

public class AreaRiservataTitolare extends AreaRiservata<Titolare> implements ITitolare{

    private Titolare titolare;
    private List<ProgrammaFedelta> listaProgrammi;
    private List<ServizioPremium> servizioPremiumAttivi;
    private List<Catalogo> listaCataloghi;

    public AreaRiservataTitolare(Titolare titolare,
                                 List<ProgrammaFedelta> listaProgrammi,
                                 List<ServizioPremium> servizioPremiumAttivi,
                                 List<Catalogo> listaCataloghi)
    {
        this.titolare = titolare;
        this.listaProgrammi = listaProgrammi;
        this.servizioPremiumAttivi = servizioPremiumAttivi;
        this.listaCataloghi = listaCataloghi;
    }

    @Override
    public boolean attivaProgramma(int idProgramma) {
        return false;
    }

    @Override
    public boolean disattivaProgramma(int idProgramma) {
        return false;
    }

    @Override
    public void visualizzaStatistiche() {

    }

    @Override
    public int creaCatalogo() {
        return 0;
    }

    @Override
    public boolean modificaCatalogo(int idCatalogo) {
        return false;
    }

    @Override
    public int definisciServizioPremium() {
        return 0;
    }

    @Override
    public boolean modificaServizioPremium(int idServizio) {
        return false;
    }

    @Override
    public boolean rimuoviServizioPremium(int idServizio) {
        return false;
    }

    public Titolare getTitolare() {
        return titolare;
    }

    public void setTitolare(Titolare titolare) {
        this.titolare = titolare;
    }

    public List<ProgrammaFedelta> getListaProgrammi() {
        return listaProgrammi;
    }

    public void setListaProgrammi(List<ProgrammaFedelta> listaProgrammi) {
        this.listaProgrammi = listaProgrammi;
    }

    public List<ServizioPremium> getServizioPremiumAttivi() {
        return servizioPremiumAttivi;
    }

    public void setServizioPremiumAttivi(List<ServizioPremium> servizioPremiumAttivi) {
        this.servizioPremiumAttivi = servizioPremiumAttivi;
    }

    public List<Catalogo> getListaCataloghi() {
        return listaCataloghi;
    }

    public void setListaCataloghi(List<Catalogo> listaCataloghi) {
        this.listaCataloghi = listaCataloghi;
    }

    @Override
    public boolean rimuoviCatalogo(int idCatalogo) {
        return false;
    }
}

