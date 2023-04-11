package it.unicam.ids.dharma.app;

public class IdGenerator {
    private int prossimoIdValido;

    private static IdGenerator generatoreId;

    private IdGenerator(){
        prossimoIdValido = 0;
    }

    public static IdGenerator getGeneratoreId(){
        if (generatoreId == null)
            generatoreId = new IdGenerator();
        return generatoreId;
    }

    public int riceviIdValido(){
        int id = this.prossimoIdValido;
        this.prossimoIdValido++;
        return id;
    }
}
