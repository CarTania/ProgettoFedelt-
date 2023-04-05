package it.unicam.ids.dharma.app;

import java.util.Objects;

/**
 * Rappresenta un cliente iscritto alla piattaforma.
 */
public record Cliente(int id, String name, int eta, String email) {

    //"id" verrà generato in qualche metodo.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
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
