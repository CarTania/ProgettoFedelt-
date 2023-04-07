package it.unicam.ids.dharma.app;

public class ServizioPremium implements VantaggioFedelta{

    private String nome;
    private String tipologia;

    public ServizioPremium(String nome, String tipologia) {
        this.nome = nome;
        this.tipologia = tipologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
