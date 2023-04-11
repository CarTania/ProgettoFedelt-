package it.unicam.ids.dharma.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class GestoreDB {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Commesso.class);
            configuration.addAnnotatedClass(Titolare.class);
            configuration.addAnnotatedClass(Premio.class);
            configuration.addAnnotatedClass(Prodotto.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }


    public static boolean inserisciProdotto(Prodotto p) {
        Session s = GestoreDB.getSessionFactory().openSession();
        if(GestoreDB.ottieniProdotto(p.getId()).isEmpty()){
            s.beginTransaction();
            s.persist("Prodotto", p);
            s.getTransaction().commit();
            s.close();
            return true;
        }
        return false;
    }

    public static Optional<Prodotto> ottieniProdotto(int id) {
        Session s = GestoreDB.getSessionFactory().openSession();

        Optional<Prodotto> p = Optional.ofNullable(s.get(Prodotto.class, id));

        s.close();
        return p;
    }

    public static boolean rimuoviProdotto(Prodotto p){
        Session s = GestoreDB.getSessionFactory().openSession();
        if(GestoreDB.ottieniProdotto(p.getId()).isPresent()){
            s.beginTransaction();
            s.remove(p);
            s.getTransaction().commit();
            s.close();
            return true;
        }
        return false;
    }


    public static void aumentaQuantitaProdotto(Prodotto prodotto, int quantitaDaAggiungere){
        Session s = GestoreDB.getSessionFactory().openSession();
        Prodotto p = s.get(Prodotto.class, prodotto.getId());

        s.beginTransaction();
        p.setQuantita(p.getQuantita() + quantitaDaAggiungere);
        s.merge(p);
        s.getTransaction().commit();

        s.close();
    }

    public static void decrementaQuantitaProdotto(Prodotto prodotto, int quantitaDaDecrementare){
        Session s = GestoreDB.getSessionFactory().openSession();
        Prodotto p = s.get(Prodotto.class, prodotto.getId());

        s.beginTransaction();
        p.setQuantita(p.getQuantita() - quantitaDaDecrementare);
        s.merge(p);
        s.getTransaction().commit();

        s.close();
    }

    public static List<Prodotto> ottieniProdottiPerNome(String nome){
        Session s = GestoreDB.getSessionFactory().openSession();

        List<Prodotto> p = s.createQuery("select p from Prodotto p where p.nome = :nome", Prodotto.class)
            .setParameter("nome", nome)
            .list();
        s.close();
        return p;
    }

    public static List<Prodotto> ottieniListaProdotti(){
        Session s = GestoreDB.getSessionFactory().openSession();

        List<Prodotto> p = s.createQuery("select p from Prodotto p", Prodotto.class)
            .list();
        s.close();
        return p;
    }

    public static Optional<Prodotto> ricercaProdottoPerId(int id){
        Session s = GestoreDB.getSessionFactory().openSession();

        Prodotto p = s.get(Prodotto.class, id);
        if (p != null){
            s.close();
            return Optional.of(p);
        }
        s.close();
        return Optional.empty();
    }


    public static void inserisciPremio(Premio premio){
        Session s = GestoreDB.getSessionFactory().openSession();

        s.beginTransaction();
        s.persist(premio);
        s.getTransaction().commit();

        s.close();
    }

    public static void rimuoviPremio(Premio premio){
        Session s = GestoreDB.getSessionFactory().openSession();

        s.beginTransaction();
        s.remove(premio);
        s.getTransaction().commit();

        s.close();
    }

    public static Optional<Premio> ottieniPremio(int id){
        Session s = GestoreDB.getSessionFactory().openSession();

        Premio p = s.get(Premio.class, id);
        s.close();
        if(p != null)
            return Optional.of(p);
        return Optional.empty();
    }

    public static void aumentaQuantitaPremio(Premio p, int quantita){
        Session s = GestoreDB.getSessionFactory().openSession();

        Premio premio = s.get(Premio.class, p.getPremio().getId());
        premio.setQuantitaPremio(premio.getQuantitaPremio() + quantita);

        s.beginTransaction();
        s.merge(premio);
        s.getTransaction().commit();

        s.close();
    }

    public static void decrementaQuantitaPremio(Premio p, int quantita){
        Session s = GestoreDB.getSessionFactory().openSession();

        Premio premio = s.get(Premio.class, p.getPremio().getId());
        premio.setQuantitaPremio(premio.getQuantitaPremio() - quantita);

        s.beginTransaction();
        s.merge(premio);
        s.getTransaction().commit();

        s.close();
    }

    public static List<ProgrammaFedelta> ottieniProgrammiAzienda(String nomeAzienda){
        return Collections.emptyList();
    }

    public static List<ProgrammaFedelta> ottieniProgrammiTipologia(String tipologia){
        return Collections.emptyList();
    }

    public static Optional<ProgrammaFedelta> ottieniProgrammaFedelta(int id){
        return null;
    }

    public static void attivaProgrammaPunti(ProgrammaPunti p, Cliente c){

    }
    public static void registraListaProdotti(){

    }
    public static boolean verificaPresenzaCliente(int idCliente) {
        return false;
    }

    public static List<ProgrammaFedelta> ottieniProgrammiCliente(int idCliente) {
        return Collections.emptyList();
    }

    public static List<Acquisto> ottieniAcquistiCliente(int idCliente) {
        return Collections.emptyList();
    }

    public static void inserisciProgrammaPunti(ProgrammaPunti p, Titolare titolare) {
    }

    public static void inserisciProgrammaLivelli(ProgrammaLivelli<Coupon> p, Titolare titolare) {
    }

    public static void rimuoviCatalogo(int idCatalogo) {
    }
}
