package it.unicam.ids.dharma.app;

import java.util.List;

public interface ICommesso {

    public int acquisisciId(Prodotto p);
    public int inserisciCodice();
    public void identificaCliente();

    public void registraAcquisto(Cliente cliente, List<Prodotto> listaSpesa);
}
