package it.unicam.ids.dharma.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
@Entity
@Table(name = "commessi")
public class Commesso implements ElementoDB{
    @Id
    @Column(name = "id_commesso", nullable = false)
    private final int id;
    @Column(name = "nome_commesso")
    private final String nome;

    public Commesso(String nome) {
        this.id = IdGenerator.getGeneratoreId().riceviIdValido();
        this.nome = nome;
    }

    public Commesso(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commesso commesso = (Commesso) o;
        return id == commesso.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Commesso{" +
            "nome='" + nome + '\'' +
            ", identificativo=" + id +
            '}';
    }
}
