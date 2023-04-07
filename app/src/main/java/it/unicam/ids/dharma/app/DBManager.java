package it.unicam.ids.dharma.app;

import java.sql.*;

public class DBManager {
    private static DBManager gestoreDb;
    private final String url, user, password;
    private Connection c;

    private DBManager() {
        this.url = "jdbc:postgresql://trumpet.db.elephantsql.com/zkrfpfxy";
        this.user = "zkrfpfxy";
        this.password = "d_-OlqLSNapgRgEo7Tax8KsJyctXDUDg";
        this.c = null;
    }

    public static DBManager getGestoreDb(){
        if (gestoreDb == null)
            gestoreDb = new DBManager();
        return gestoreDb;
    }

    /**
     * Effettua la connessione con il database remoto.
     */
    private void connect() {
        try {
            Class.forName(("org.postgresql.Driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("Driver postgres non trovato.");
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(url,
                user, password);
            System.out.println("Database connesso");
        } catch (SQLException e) {
            System.out.println("Problema nell'aprire la connessione");
            e.printStackTrace();
        }
    }

    public <T extends ElementoDB> void inserisci(T elem){
        DBManager.getGestoreDb().connect();
        if(elem instanceof Prodotto){
            try {
                Statement stm = c.createStatement();
                ResultSet res = stm.executeQuery("insert into prodotti values ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
