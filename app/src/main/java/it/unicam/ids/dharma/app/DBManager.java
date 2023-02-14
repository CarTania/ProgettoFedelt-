package it.unicam.ids.dharma.app;

import java.sql.*;

public class DBManager {
    public static void main(String[] args) {
        try{
            //esegue la connessione al db "loyaltyplatformdb"
            Connection c = DriverManager.
                getConnection("jdbc:postgresql://trumpet.db.elephantsql.com/zkrfpfxy",
                    "zkrfpfxy", "d_-OlqLSNapgRgEo7Tax8KsJyctXDUDg");
            //
            Statement stm = c.createStatement();

            ResultSet res = stm.executeQuery("select * from clienti;");

            while(res.next()){
                System.out.println("id: "+res.getString("id_cliente")+", "+
                    "nome: "+res.getString("nome_cliente"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
