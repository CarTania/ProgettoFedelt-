package it.unicam.ids.dharma.app;

public class Cliente {

    private int id;
    private String name;
    private int eta;

    private String email;
    private boolean possiedeCartaFedelta;

    //"id" verrà generato in qualche metodo.
    public Cliente(int id, String name, int eta, boolean possiedeCartaFedelta, String email) {
        this.id = id;
        this.name = name;
        this.eta = eta;
        this.possiedeCartaFedelta = possiedeCartaFedelta;
        this.email= email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public boolean isPossiedeCartaFedelta() {
        return possiedeCartaFedelta;
    }

    public void setPossiedeCartaFedelta(boolean possiedeCartaFedelta) {
        this.possiedeCartaFedelta = possiedeCartaFedelta;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email= email;
    }

}
