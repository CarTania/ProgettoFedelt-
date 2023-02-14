package it.unicam.ids.dharma.app;

public interface ITitolare {
    boolean attivaProgramma (int idProgramma);
    boolean disattivaProgramma(int idProgramma);
    void visualizzaStatistiche();
    int creaCatalogo();
    boolean modificaCatalogo(int idCatalogo);
    int definisciServizioPremium();
    boolean modificaServizioPremium(int idServizio);
    boolean rimuoviServizioPremium(int idServizio);
    boolean rimuoviCatalogo(int idCatalogo);
}
