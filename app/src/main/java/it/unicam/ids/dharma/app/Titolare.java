package it.unicam.ids.dharma.app;

/**
 * Rappresenta un titolare di un'azienda iscritto alla piattaforma.
 * @param id l'id del titolare.
 * @param nome il nome del titolare.
 * @param partitaIva la partitaIva del del titolare.
 * @param azienda l'azienda che gestisce il titolare.
 * @param email l' indirizzo email del titolare.
 */
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
