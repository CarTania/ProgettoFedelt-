package it.unicam.ids.dharma.app;

public class Commesso {
    private String name;
    private int Identificativo;

    public Commesso(String name, int identificativo) {
        this.name = name;
        Identificativo = identificativo;
    }


    public String getName() {
        return name;
    }

    public int getIdentificativo() {
        return Identificativo;
    }

    public void setIdentificativo(int identificativo) {
        Identificativo = identificativo;
    }
}
