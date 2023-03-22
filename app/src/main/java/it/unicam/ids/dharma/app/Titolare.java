package it.unicam.ids.dharma.app;

public record Titolare(int id, String nome, int partitaIva, String azienda, String email) {
    
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
