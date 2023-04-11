package it.unicam.ids.dharma.app;

import java.util.List;
import java.util.Optional;

public interface ICliente {
    /**
     * Il metodo si occupa di cercare un programma fedeltà tra tutti quelli attivabili.
     * E' possibile ricercare un programma effettuando una ricerca per azienda o per
     * tipologia di programma.
     */
    void cercaProgrammaFedelta();
    void apriMenuRicerca();
    void cercaPerAzienda(String nomeAzienda);
    void cercaPerTipologia(String tipologia);
    void effettuaAcquisto();
    void cronologiAcquisti();
    void visualizzaCatalogoPremi();
    void riscattaVantaggioProgrammaPunti(ProgrammaPunti p, int punti);
    void riscattaPremio (Premio p, ProgrammaPunti programmaPunti);
    void ottieniCouponSconto(ProgrammaPunti programmaPunti, int punti);
    void attivaProgrammaPunti();
    void iniziaSessione();
}

