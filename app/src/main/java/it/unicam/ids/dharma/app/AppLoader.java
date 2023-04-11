package it.unicam.ids.dharma.app;

import java.util.Scanner;

public class AppLoader {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";
        while(!input.equals("!")){
            System.out.println("Piattaforma avviata, scegli la modalità di accesso:\n 1. Cliente \n 2. Titolare"+
                "\n 3. Commesso...\nPremi ! per terminare.");
            input = s.nextLine();
            if(input.equals("1")){
                System.out.println("Inserisci il tuo nome: ");
                String nome = s.nextLine();
                System.out.println("Inserisci la tua eta: ");
                int eta = Integer.parseInt(s.nextLine());
                System.out.println("Inserisci la tua mail: ");
                String email = s.nextLine();

                Cliente utente = new Cliente(nome, eta, email);
                AreaRiservataCliente a = new AreaRiservataCliente(utente);
                a.iniziaSessione();
            }
            if(input.equals("2")){
                System.out.println("Inserisci il tuo nome: ");
                String nome = s.nextLine();
                System.out.println("Inserisci la tua partita Iva: ");
                int piva = Integer.parseInt(s.nextLine());
                System.out.println("Inserisci la tua mail: ");
                String email = s.nextLine();
                System.out.println("Inserisci la tua azienda: ");
                String azienda = s.nextLine();

                Titolare titolare = new Titolare(nome, piva, azienda, email);
                AreaRiservataTitolare t = new AreaRiservataTitolare(titolare);
                t.iniziaSessione();
            }
        }

    }
}
