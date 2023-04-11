package it.unicam.ids.dharma.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Rappresenta un titolare di un'azienda iscritto alla piattaforma.
 *
 */
@Entity
@Table(name = "titolari")
public class Titolare implements ElementoDB{
    @Id
    @Column(name = "id_titolare", nullable = false)
    private final int id;
    @Column(name = "nome_titolare")
    private final String nome;
    @Column(name = "piva")
    private final int partitaIva;
    private final String azienda;
    @Column(name = "email_titolare")
    private final String email;

    public Titolare() {
        id = 0;
        nome = null;
        partitaIva = 0;
        azienda = null;
        email = null;
    }

    public Titolare(String nome, int partitaIva, String azienda, String email) {
        this.id = IdGenerator.getGeneratoreId().riceviIdValido();
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.azienda = azienda;
        this.email = email;
    }

    public Titolare(int id, String nome, int partitaIva, String azienda, String email) {
        this.id = id;
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.azienda = azienda;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPartitaIva() {
        return partitaIva;
    }

    public String getAzienda() {
        return azienda;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titolare titolare = (Titolare) o;
        return id == titolare.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
