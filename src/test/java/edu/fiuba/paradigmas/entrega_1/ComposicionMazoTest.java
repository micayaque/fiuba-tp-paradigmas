package edu.fiuba.paradigmas.entrega_1;

import edu.fiuba.paradigmas.modelo.mazo.Mazo;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComposicionMazoTest {

    @Test
    public void mazoPara5JugadoresConDetectiveY1MafiosoTieneLaComposicionCorrecta() {
        List<Rol> rolesElegidos = List.of(new Detective(), new Mafioso(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        Mazo mazo = new Mazo();
        List<Rol> cartasGeneradas = mazo.generarPara(rolesElegidos);
        int cantidadRolesGenerados = 0;
        ContadorDeRoles contadorDeRolesGenerados = new ContadorDeRoles();
        for (Rol rol : cartasGeneradas) {
            rol.contarseEn(contadorDeRolesGenerados);
            cantidadRolesGenerados++;
        }

        int cantidadDetectivesEsperada = 1;
        int cantidadMafiososEsperada = 1;
        int cantidadCiudadanosEsperada = 3;
        int cantidadRolesEsperada = 5;

        assertEquals(cantidadRolesEsperada, cantidadRolesGenerados);
        assertEquals(cantidadDetectivesEsperada, contadorDeRolesGenerados.cantidadDetectives());
        assertEquals(cantidadMafiososEsperada, contadorDeRolesGenerados.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperada, contadorDeRolesGenerados.cantidadCiudadanos());
    }

    @Test
    public void mazoPara7JugadoresTieneLaComposicionCorrecta() {
        List<Rol> rolesElegidos = List.of(new Detective(), new Mafioso(), new Ciudadano(), new Ciudadano(), new Ciudadano(),
                                            new Medico(), new Mafioso());

        Mazo mazo = new Mazo();
        List<Rol> cartasGeneradas = mazo.generarPara(rolesElegidos);
        int cantidadRolesGenerados = 0;
        ContadorDeRoles contadorDeRolesGenerados = new ContadorDeRoles();
        for (Rol rol : cartasGeneradas) {
            rol.contarseEn(contadorDeRolesGenerados);
            cantidadRolesGenerados++;
        }

        int cantidadDetectivesEsperada = 1;
        int cantidadMafiososEsperada = 2;
        int cantidadCiudadanosEsperada = 3;
        int cantidadMedicosEsperada = 1;
        int cantidadRolesEsperada = 7;

        assertEquals(cantidadRolesEsperada, cantidadRolesGenerados);
        assertEquals(cantidadDetectivesEsperada, contadorDeRolesGenerados.cantidadDetectives());
        assertEquals(cantidadMafiososEsperada, contadorDeRolesGenerados.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperada, contadorDeRolesGenerados.cantidadCiudadanos());
        assertEquals(cantidadMedicosEsperada, contadorDeRolesGenerados.cantidadMedicos());
    }

    @Test
    public void mazoPara10JugadoresConPadrinoYSheriffTieneLaComposicionCorrecta() {
        List<Rol> rolesElegidos = List.of(new Detective(), new Mafioso(), new Ciudadano(), new Ciudadano(), new Ciudadano(),
                                            new Medico(), new Mafioso(), new Padrino(), new Mafioso(), new Ciudadano());

        Mazo mazo = new Mazo();
        List<Rol> cartasGeneradas = mazo.generarPara(rolesElegidos);
        int cantidadRolesGenerados = 0;
        ContadorDeRoles contadorDeRolesGenerados = new ContadorDeRoles();
        for (Rol rol : cartasGeneradas) {
            rol.contarseEn(contadorDeRolesGenerados);
            cantidadRolesGenerados++;
        }

        int cantidadDetectivesEsperada = 1;
        int cantidadMafiososEsperada = 3;
        int cantidadCiudadanosEsperada = 4;
        int cantidadMedicosEsperada = 1;
        int cantidadRolesEsperada = 10;

        assertEquals(cantidadRolesEsperada, cantidadRolesGenerados);
        assertEquals(cantidadDetectivesEsperada, contadorDeRolesGenerados.cantidadDetectives());
        assertEquals(cantidadMafiososEsperada, contadorDeRolesGenerados.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperada, contadorDeRolesGenerados.cantidadCiudadanos());
        assertEquals(cantidadMedicosEsperada, contadorDeRolesGenerados.cantidadMedicos());
    }
}