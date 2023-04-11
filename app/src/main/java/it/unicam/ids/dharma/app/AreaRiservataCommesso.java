package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;

public class AreaRiservataCommesso  implements ICommesso{

    private Commesso commesso;
    private List<Acquisto> acquistiRegistrati;

    public AreaRiservataCommesso(Commesso commesso) {
        this.commesso = commesso;
        this.acquistiRegistrati = new LinkedList<>();
    }

    @Override
    public int acquisisciId(Prodotto p) {
        return p.getId();
    }

    @Override
    public int inserisciCodice() {
        System.out.println("Inserisci il codice del prodotto: ");
        Scanner s = new Scanner(System.in);
        return Integer.parseInt(s.nextLine());
    }

    /**
     * Permette l'identificazione di un cliente tramite l'id.
     */
    @Override
    public void identificaCliente() {
        Scanner s = new Scanner(System.in);
        String input ;
        boolean identificazioneInCorso = true;
        while(identificazioneInCorso) {
            System.out.println("Inserisci id cliente: ");
            input = s.nextLine();
            if (GestoreDB.verificaPresenzaCliente(Integer.parseInt(input))) {
                System.out.println("Informazioni cliente: ");
                List<ProgrammaFedelta> listaProgrammi =
                    GestoreDB.ottieniProgrammiCliente(Integer.parseInt(input));

                List<Acquisto> listaAcquisti =
                    GestoreDB.ottieniAcquistiCliente(Integer.parseInt(input));
                if(!listaProgrammi.isEmpty()){
                    for (ProgrammaFedelta p: listaProgrammi) {
                        System.out.println(p);
                    }
                }
                if (!listaAcquisti.isEmpty()){
                    for (Acquisto a: listaAcquisti) {
                        System.out.println(a);
                    }
                }
             identificazioneInCorso = false;
            } else {
                System.out.println("idCliente non valido: reinserisci l'id.");
            }
        }
    }

    /**
     * Registra un acquisto effettuato dal cliente
     * @param cliente è il cliente che effettua l'acquisto
     * @param listaSpesa è la lista di prodotti acquistati
     */
    public void registraAcquisto(Cliente cliente, List<Prodotto> listaSpesa){
        Acquisto acquisto = new Acquisto(LocalDate.now(), cliente);
        for (Prodotto prodotto : listaSpesa) {
            Optional<Prodotto> p = Magazzino.getMagazzino().ricercaProdotto(acquisisciId(prodotto));
            if(p.isPresent()){
               Prodotto prod = Magazzino.getMagazzino().prelevaProdotto(p.get()).get();
                acquisto.aggiungiProdotto(prod);
            }
        }
        this.acquistiRegistrati.add(Objects.requireNonNull(acquisto));
    }
}
