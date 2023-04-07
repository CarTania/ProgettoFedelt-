package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class AreaRiservataCommesso  implements ICommesso{

    private Commesso commesso;
    private List<Acquisto> AcquistiRegistrati ;

    public AreaRiservataCommesso(Commesso commesso) {
        this.commesso = commesso;
        AcquistiRegistrati = new LinkedList<>();
    }

    @Override
    public int acquisisciId(Prodotto p) {
        return p.getIdProdotto();
    }

    @Override
    public int inserisciCodice() {
        System.out.println("Inserisci il codice del prodotto");
        Scanner s = new Scanner(System.in);
        return Integer.parseInt(s.nextLine());
    }

    /**
     * Permette di identificare un cliente tramite l'id
     * @param id è l'id inserito con cui il cliente viene riconosciuto
     */
    @Override
    public void identificaCliente(int id) {
        Scanner s = new Scanner(System.in);
        String input ;
        boolean condizioneVera = true;
        while(condizioneVera) {
            System.out.println("Inserisci id cliente: ");
            input = s.nextLine();
            if (Integer.parseInt(input) == id) {
                System.out.println("Informazioni cliente: ");
                Predicate<Integer> p = i -> i == id;
                Optional<List<ElementoDB>> listaProgrammi = GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
                Optional<List<ElementoDB>> listaAcquisti = GestoreProgrammiFedelta.getGestoreProgrammi().ottieniElenco(p);
                if(listaProgrammi.isPresent() && listaAcquisti.isPresent()){
                    for (ElementoDB elemento: listaProgrammi.get()) {
                        System.out.println(elemento);
                    }
                    for (ElementoDB elemento: listaAcquisti.get()) {
                        System.out.println(elemento);
                    }
                }
             condizioneVera = false;
            } else {
                System.out.println("idCliente non valido: reinserisci l'id. ");
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
        for (Prodotto prodotto: listaSpesa) {
            Optional<Prodotto> p = Magazzino.getMagazzino().ricercaProdotto(acquisisciId(prodotto));
            if(p.isPresent()){
               Prodotto prod = Magazzino.getMagazzino().prelevaProdotto(p.get()).get();
                acquisto.aggiungiProdotto(prod);
            }
        }
        this.AcquistiRegistrati.add(Objects.requireNonNull(acquisto));
    }
}
