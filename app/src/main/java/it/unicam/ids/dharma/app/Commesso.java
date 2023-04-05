package it.unicam.ids.dharma.app;

import java.util.Objects;

public record Commesso(String nome, int identificativo) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commesso commesso = (Commesso) o;
        return identificativo == commesso.identificativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificativo);
    }

    @Override
    public String toString() {
        return "Commesso{" +
            "nome='" + nome + '\'' +
            ", identificativo=" + identificativo +
            '}';
    }
}
