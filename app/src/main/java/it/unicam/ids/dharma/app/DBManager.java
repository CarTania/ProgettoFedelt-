package it.unicam.ids.dharma.app;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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

    public static DBManager getGestoreDb() {
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

    public boolean inserisciProdotto(Prodotto prodotto, int quantita) {
        DBManager.getGestoreDb().connect();
        String sql = "INSERT INTO prodotti VALUES (?, ?, ?, ?, null)";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, prodotto.getIdProdotto());
            preparedStatement.setString(2, prodotto.getNome());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, quantita);

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return false;
    }

    public Optional<Map.Entry<Prodotto, Integer>> recuperaProdotto(int idProdotto) {
        DBManager.getGestoreDb().connect();
        String sql = "SELECT * FROM prodotti WHERE id_prodotto=?";

        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, idProdotto);
            ResultSet rs = preparedStatement.executeQuery();
            int id = rs.getInt("id_prodotto");
            int quantita = rs.getInt("quantita");
            String nome = rs.getString("nome_prodotto");
            double prezzo = rs.getDouble("prezzo");
            Prodotto p = new Prodotto(nome, id, prezzo);
            return Optional.of(Map.entry(p, quantita));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean rimuoviProdotto(Prodotto prodotto) {
        DBManager.getGestoreDb().connect();
        String sql = "DELETE FROM prodotti WHERE id_prodotto=?";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, prodotto.getIdProdotto());

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return false;
    }

    public boolean inserisciCliente(Cliente cliente) {
        DBManager.getGestoreDb().connect();
        String sql = "INSERT INTO clienti VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.id());
            preparedStatement.setString(2, cliente.name());
            preparedStatement.setInt(3, cliente.eta());
            preparedStatement.setString(4, cliente.email());

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return false;
    }

    public Optional<Cliente> ottieniCliente(int idCliente) {
        DBManager.getGestoreDb().connect();
        String sql = "SELECT * FROM clienti WHERE id_cliente=?";

        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
                return Optional.empty();
            int id = rs.getInt("id_cliente");
            String nome = rs.getString("nome_cliente");
            int eta = rs.getInt("eta");
            String mail = rs.getString("email_cliente");
            return Optional.of(new Cliente(id, nome, eta, mail));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean rimuoviCliente(Cliente cliente) {
        DBManager.getGestoreDb().connect();
        String sql = "DELETE FROM clienti WHERE id_cliente=?";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.id());

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean inserisciTitolare(Titolare titolare) {
        DBManager.getGestoreDb().connect();
        String sql = "INSERT INTO titolari VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, titolare.id());
            preparedStatement.setInt(2, titolare.partitaIva());
            preparedStatement.setString(3, titolare.nome());
            preparedStatement.setString(4, titolare.email());
            preparedStatement.setString(4, titolare.azienda());

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<Titolare> ottieniTitolare(int idTitolare) {
        DBManager.getGestoreDb().connect();
        String sql = "SELECT * FROM titolari WHERE id_titolare=?";

        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, idTitolare);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
                return Optional.empty();
            int id = rs.getInt("id_titolare");
            String nome = rs.getString("nome_titolare");
            int piva = rs.getInt("piva");
            String mail = rs.getString("email_titolare");
            String azienda = rs.getString("azienda");
            return Optional.of(new Titolare(id, nome, piva, azienda, mail));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean rimuoviTitolare(Titolare titolare) {
        DBManager.getGestoreDb().connect();
        String sql = "DELETE FROM titolari WHERE id_titolare=?";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, titolare.id());

            preparedStatement.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean inserisciProgrammaPunti(ProgrammaPunti p, Titolare t){
        DBManager.getGestoreDb().connect();
        String sql1 = "INSERT INTO programmi_punti VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO attivazioni_titolare_programma VALUES (?, ?)";
        String sql3 = "INSERT INTO programmi_fedelta VALUES (?, ?, ?, ?)";

        if (p.getCatalogoOpzionale().isPresent()) {
            int idCatalogo = p.getCatalogoOpzionale().get().getId();
            try {
                PreparedStatement preparedStatement1 = c.prepareStatement(sql1);
                preparedStatement1.setInt(1, p.getId());
                preparedStatement1.setDouble(2, p.getCostanteCambio());
                preparedStatement1.setInt(3, idCatalogo);

                PreparedStatement preparedStatement2 = c.prepareStatement(sql2);
                preparedStatement2.setInt(1, t.id());
                preparedStatement2.setInt(2, p.getId());

                LocalDate d = p.dataAttivazione;
                LocalDate s = p.dataScadenza;
                Date attivazione = new Date(d.getYear(), d.getMonthValue(), d.getDayOfMonth());
                Date scadenza = new Date(s.getYear(), s.getMonthValue(), s.getDayOfMonth());
                PreparedStatement preparedStatement3 = c.prepareStatement(sql3);
                preparedStatement3.setInt(1, p.getId());
                preparedStatement3.setDate(2, attivazione);
                preparedStatement3.setDate(3, scadenza);
                preparedStatement3.setBoolean(4, true);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                PreparedStatement preparedStatement1 = c.prepareStatement(sql1);
                preparedStatement1.setInt(1, p.getId());
                preparedStatement1.setDouble(2, p.getCostanteCambio());
                preparedStatement1.setNull(3, Types.INTEGER);

                PreparedStatement preparedStatement2 = c.prepareStatement(sql2);
                preparedStatement2.setInt(1, t.id());
                preparedStatement2.setInt(2, p.getId());

                LocalDate d = p.dataAttivazione;
                LocalDate s = p.dataScadenza;
                Date attivazione = new Date(d.getYear(), d.getMonthValue(), d.getDayOfMonth());
                Date scadenza = new Date(s.getYear(), s.getMonthValue(), s.getDayOfMonth());
                PreparedStatement preparedStatement3 = c.prepareStatement(sql3);
                preparedStatement3.setInt(1, p.getId());
                preparedStatement3.setDate(2, attivazione);
                preparedStatement3.setDate(3, scadenza);
                preparedStatement3.setBoolean(4, true);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Optional<ProgrammaFedelta> ottieniProgrammaFedelta(ProgrammaFedelta p){
        DBManager.getGestoreDb().connect();
        String sql1 = "SELECT * FROM programmi_punti WHERE id_programma=?";
        String sql2 = "SELECT * FROM programmi_livelli WHERE id_programma=?";

        if(p instanceof ProgrammaPunti){
            try {
                PreparedStatement preparedStatement = c.prepareStatement(sql1);
                ResultSet rs = preparedStatement.executeQuery();
                if(!rs.next())
                    return Optional.empty();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }



}
