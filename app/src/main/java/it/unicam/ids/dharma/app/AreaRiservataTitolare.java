package it.unicam.ids.dharma.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AreaRiservataTitolare implements ITitolare {

    private final Titolare titolare;
    private final List<ProgrammaFedelta> listaProgrammiCreati;
    private final List<Catalogo> listaCataloghi;

    public AreaRiservataTitolare(Titolare titolare) {
        this.titolare = titolare;
        this.listaProgrammiCreati = new ArrayList<>();
        this.listaCataloghi = new ArrayList<>();
    }

    public Titolare getTitolare() {
        return titolare;
    }

    public List<ProgrammaFedelta> getListaProgrammiCreati() {
        return listaProgrammiCreati;
    }

    public List<Catalogo> getListaCataloghi() {
        return listaCataloghi;
    }

    @Override
    public boolean creaProgramma() {
        Scanner s = new Scanner(System.in);
        String input;
        boolean inserimentoInCorso = true;

        while (inserimentoInCorso) {
            System.out.println("Scegli la tipologia di programma da creare (1. punti, 2. livelli): ");
            input = s.nextLine();

            if (Objects.equals(input, "1")) {
                int[] inputData = new int[3];
                System.out.println("Inserisci la data di scadenza (3 interi: anno mese giorno): ");
                inputData[0] = Integer.parseInt(s.next());
                inputData[1] = Integer.parseInt(s.next());
                inputData[2] = Integer.parseInt(s.next());

                int costanteCambio;
                System.out.println("Inserisci la costante per il cambio dei punti: ");
                costanteCambio = s.nextInt();

                if (!listaCataloghi.isEmpty()) {
                    System.out.println("Scegli un catalogo da associare al programma (opzionale)");
                    //inserisce la posizione nell'arraylist
                    Catalogo c = listaCataloghi.get(Integer.parseInt(s.nextLine()));
                    ProgrammaPunti p = new ProgrammaPunti(LocalDate.of(inputData[0], inputData[1], inputData[2]),
                        costanteCambio, c);
                    listaProgrammiCreati.add(p);
                    GestoreDB.inserisciProgrammaPunti(p, this.titolare);
                } else {
                    ProgrammaPunti p = new ProgrammaPunti(LocalDate.of(inputData[0], inputData[1], inputData[2]),
                        costanteCambio);
                    listaProgrammiCreati.add(p);
                    GestoreDB.inserisciProgrammaPunti(p, this.titolare);
                }
                inserimentoInCorso = false;
            } else if (Objects.equals(input, "2")) {
                int[] inputData = new int[3];
                System.out.println("Inserisci la data di scadenza (3 interi: anno mese giorno): ");
                inputData[0] = Integer.parseInt(s.next());
                inputData[1] = Integer.parseInt(s.next());
                inputData[2] = Integer.parseInt(s.next());

                int numeroLivelli;
                System.out.println("Inserisci il numero di livelli da creare: ");
                numeroLivelli = s.nextInt();


                System.out.println("Inserisci il tipo di vantaggi: (coupon, premi");
                String inputVantaggio;

                if (s.nextLine().equals("coupon")) {
                    List<Coupon> vantaggi = new ArrayList<>();
                    int sconto;
                    System.out.println("Inserisci lo sconto: ");
                    for (int i = 0; i < numeroLivelli; i++) {
                        System.out.println("Inserisci lo sconto: ");
                        sconto = s.nextInt();
                        vantaggi.add(new Coupon(LocalDate.of(inputData[0], inputData[1], inputData[2]
                        ), sconto));
                    }
                    ProgrammaLivelli<Coupon> p =
                        new ProgrammaLivelli<>(
                            numeroLivelli, vantaggi, LocalDate.of(inputData[0], inputData[1], inputData[2])
                        );
                    GestoreDB.inserisciProgrammaLivelli(p, this.titolare);
                    inserimentoInCorso = false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean rimuoviProgramma() {
        Scanner s = new Scanner(System.in);
        int input;

        System.out.println("Indica il programma da rimuovere (inserisci l'id): ");
        input = s.nextInt();

        for (int i = 0; i < listaProgrammiCreati.size(); i++) {
            if (listaProgrammiCreati.get(i).getId() == input) {
                GestoreDB.ottieniProgrammaFedelta(input);
                listaProgrammiCreati.remove(input);
                System.out.println("Programma rimosso");
                return true;
            }
        }
        return false;
    }

    @Override
    public void aggiugiProdotto() {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci il nome del prodotto da aggiungere");
        String nome = s.nextLine();

        System.out.println("Inserisci il prezzo del prodotto da aggiungere");
        double prezzo = s.nextDouble();

        System.out.println("Inserisci la quantita");
        int quantita = s.nextInt();


        if(!Magazzino.getMagazzino().ricercaProdotto(nome).isPresent()){
            Magazzino.getMagazzino().aggiungiProdotto(nome, prezzo, quantita);
        }
    }


    @Override
    public void creaCatalogo() {
        Catalogo c = new Catalogo();
        Scanner s = new Scanner(System.in);
        boolean inserimento = true;

        while (inserimento) {
            String nome;
            int quantita, punti;
            System.out.println("Inserisci il nome del prodotto da aggiungere: ");
            nome = s.nextLine();
            System.out.println("Indica la quantità da aggiungere: ");
            quantita = s.nextInt();
            System.out.println("Indica i punti da associare: ");
            punti = s.nextInt();

            try {
                Premio p = new Premio(Magazzino.getMagazzino().ricercaProdotto(nome).get(), quantita, punti);
                c.inserisciPremio(p);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            String risposta;
            System.out.println("Vuoi inserire un nuovo premio?(si/no)");
            risposta = s.nextLine();

            if (risposta.equals("si")) {
                listaCataloghi.add(c);
                inserimento = false;
            }
        }
    }

    @Override
    public void rimuoviCatalogo() {
        Scanner s = new Scanner(System.in);
        int limite = this.listaCataloghi.size();
        System.out.println("inserisci l'indice del catalogo da rimuovere (compreso tra 0 e "+
            limite+" ): ");
        int indiceCatalogo = s.nextInt();
        GestoreDB.rimuoviCatalogo(indiceCatalogo);
        this.listaCataloghi.remove(indiceCatalogo);
    }

    public void iniziaSessione(){

    }
}

