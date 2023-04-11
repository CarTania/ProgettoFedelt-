package it.unicam.ids.dharma.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Rappresenta un cliente iscritto alla piattaforma.
 */
@Entity
@Table(name = "clienti")
public class Cliente implements  ElementoDB{
    @Id
    @Column(name = "id_cliente", nullable = false)
    private final Integer id;
    @Column(name = "nome_cliente")
    private final String name;
    @Column(name = "eta")
    private final Integer eta;
    @Column(name = "email_cliente")
    private final String email;

    public Cliente(){
        id = null;
        name = null;
        eta = null;
        email = null;
    }
    public Cliente(String name, int eta, String email) {
        this.id = IdGenerator.getGeneratoreId().riceviIdValido();
        this.name = name;
        this.eta = eta;
        this.email = email;
    }

    public Cliente(int id, String name, int eta, String email) {
        this.id = id;
        this.name = name;
        this.eta = eta;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getEta() {
        return eta;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", eta=" + eta +
            ", email='" + email + '\'' +
            '}';
    }
}
