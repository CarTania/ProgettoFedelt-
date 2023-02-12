package it.unicam.ids.dharma.app;

import java.sql.*;

public class DBManager {
    public static void main(String[] args) {
        try{
            //esegue la connessione al db "loyaltyplatformdb"
            Connection c = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/loyaltyplatformdb",
                    "postgres", "DharmaProject");
            //
            Statement stm = c.createStatement();

            ResultSet res = stm.executeQuery("select * from loyaltyplatformschema.clienti;");

            while(res.next()){
                System.out.println("id: "+res.getString("id_cliente")+", "+
                    "nome: "+res.getString("nome_cliente"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
