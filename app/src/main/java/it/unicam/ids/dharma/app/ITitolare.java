package it.unicam.ids.dharma.app;

/**
 * Interfaccia ITitolare
 */

public interface ITitolare {
    boolean creaProgramma(int idProgramma);
    boolean rimuoviProgramma(int idProgramma);
    void visualizzaStatistiche();
    int creaCatalogo();
    boolean modificaCatalogo(int idCatalogo);
    int definisciServizioPremium();
    boolean modificaServizioPremium(int idServizio);
    boolean rimuoviServizioPremium(int idServizio);
    boolean rimuoviCatalogo(int idCatalogo);
}
