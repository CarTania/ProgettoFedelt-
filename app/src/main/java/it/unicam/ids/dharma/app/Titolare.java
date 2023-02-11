package it.unicam.ids.dharma.app;

public class Titolare {

    private int id;
    private String nome;
    private int partitaIva;
    private String azienda;
    private String email;

    public Titolare(int id, String nome, int partitaIva, String azienda, String email) {
        this.id= id;
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.azienda = azienda;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(int partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Titolare{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", partitaIva=" + partitaIva +
                ", azienda='" + azienda + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
