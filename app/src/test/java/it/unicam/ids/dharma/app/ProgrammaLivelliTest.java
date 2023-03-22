package it.unicam.ids.dharma.app;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammaLivelliTest {

    @Test
    public void inizialiazzaProgrammaTest()
    {
        Livello<Integer>  livello1= new Livello(1, 30, 20);
        Livello<Integer> livello2= new Livello(2, 40, 30);
        Livello<Integer> livello3= new Livello(3, 50, 40);

        List<Livello<Integer>> listaLivello= List.of(livello1, livello2,
                livello3);
        ProgrammaLivelli<Integer> programmaLivelli=
                new ProgrammaLivelli<>(3, listaLivello, LocalDate.now(),
            LocalDate.of(2023, 3, 22));
        Livello<Integer>[] livello= programmaLivelli.getLivelliProgramma();
        assertEquals(livello[0], livello1);
        assertEquals(livello[1], livello2);
        assertEquals(livello[2], livello3);

      Livello<Integer>  livello4= new Livello(4, 30, 20);
        Livello<Integer> livello5= new Livello(5, 40, 30);
        Livello<Integer> livello6= new Livello(6, 50, 40);
        List<Livello<Integer>> listaLivello2=  new java.util.ArrayList<>(List.of(livello4, livello5,
                livello6));
        ProgrammaLivelli<Integer> programmaLivelli2= new ProgrammaLivelli<>(3, listaLivello2, LocalDate.now(),
                        LocalDate.of(2023, 3, 22));

        a


    }
}