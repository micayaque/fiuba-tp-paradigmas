package edu.fiuba.algo3.entrega_2.comodinTest;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.fiuba.algo3.controllers.Parseados.EfectoParseado;
import edu.fiuba.algo3.modelo.ManoDePoker.*;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.activacion.*;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.efectos.AumentarPuntaje;
import edu.fiuba.algo3.modelo.efectos.AumentarPuntajeYMultiplicador;
import edu.fiuba.algo3.modelo.efectos.Efecto;
import edu.fiuba.algo3.modelo.efectos.MultiplicarMultiplicador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class comodinTest {

    @Test
    // Verificar que al tener un comodín que sume 8 al multiplicador se aplique correctamente
    public void testComodinAplicaMultiplicadorCorrectamente() {
        Activacion activacion = new ActivacionSiempre();
        List<Activacion> activaciones = List.of(activacion);


        Puntaje puntaje = new Puntaje(1, 8);

        Efecto multiplicador =  new MultiplicarMultiplicador(puntaje);

        Comodin comodin = new Comodin("Comodín Multiplicador", "Suma 8 al multiplicador", multiplicador,  activaciones);

        ManoDePoker cartaAlta = new CartaMasAlta();
        Puntaje puntajeFinal = comodin.aplicarA(cartaAlta);

        Assertions.assertEquals(8, puntajeFinal.getMultiplicador());
    }


   @Test
   public void testComodinAplicaSumaDePuntosCorrectamente() {
        Activacion activacion = new ActivacionSiempre();
        List<Activacion> activaciones = List.of(activacion);

        Puntaje puntaje = new Puntaje(10, 1);

        Efecto aumentar =  new AumentarPuntaje(puntaje);

        Comodin comodin = new Comodin("aumentador de puntor", "aumenta los puntos en 10", aumentar, activaciones);

        ManoDePoker cartaAlta = new CartaMasAlta();

        Puntaje puntajeFinal = comodin.aplicarA(cartaAlta);

       Assertions.assertEquals(15, puntajeFinal.calcularPuntaje());
    }

    @Test
    //Verificar que si el jugador posee un comodin que tiene chance 1 sobre 1000 de romperse se rompa correctamente.
    public void testComodinProbabilidadSeRompe() {
        ActivacionProbabilidad activacionProba = new ActivacionProbabilidad(1000);
        ManoDePoker manoJugada = new Par();
        activacionProba.esActivable(manoJugada);
        Assertions.assertTrue(activacionProba.estaRoto());
    }

    @Test
    // El jugador activa un comodín con una combinación de efectos
    // bonus de mano jugada + puntaje aumentado + activación aleatoria
    //"descripcion": "x15  Mult. 1 en 6 de probabilidad y +100  fichas si la mano jugada contiene un trio",
    public void testComodinCombinacionesDeEfectos() {
        Puntaje puntaje = new Puntaje(100, 15);
        Efecto combinado = new AumentarPuntajeYMultiplicador(puntaje);
        ManoDePoker trio = new Trio();
        List<Activacion> activaciones = new ArrayList<>();
        activaciones.add(new ActivacionProbabilidad(1));
        activaciones.add(new ActivacionManoDePoker(trio));
        Comodin comodin = new Comodin("Comodín Combinado", "x15  Mult. 1 en 6 de probabilidad y +100  fichas si la mano jugada contiene un trio", combinado, activaciones );
        ManoDePoker manoJugada = new Trio(); // (30,3) -

        Puntaje puntajeEsperado = new Puntaje(130,45);
        Puntaje puntajeFinal = comodin.aplicarA(manoJugada);
        // 100 +30 * 3 + 15
        Assertions.assertEquals(puntajeEsperado.calcularPuntaje(), puntajeFinal.calcularPuntaje());
    }

}


