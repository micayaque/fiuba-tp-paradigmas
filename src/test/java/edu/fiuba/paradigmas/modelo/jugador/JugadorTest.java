package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.Urna;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.ContadorDeRoles;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void elJugadorConservaSuNombre() {
        Jugador jugador = new Jugador("Vito", new Mafioso());

        assertEquals("Vito", jugador.nombre());
    }

    @Test
    public void elJugadorSeCuentaSegunSuCartaEnElContador() {
        Jugador jugador = new Jugador("Vito", new Mafioso());
        ContadorDeRoles contador = new ContadorDeRoles();

        jugador.contarseEn(contador);

        assertEquals(1, contador.cantidadDeMafiosos());
        assertEquals(0, contador.cantidadDeCiudadanos());
    }

    @Test
    public void unJugadorRecienCreadoSeAgregaALaListaDeVivos() {
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador mafioso = new Jugador("mafioso", new Mafioso());

        List<Jugador> vivos = new ArrayList<>();
        ciudadano1.estaVivo(vivos);
        ciudadano2.estaVivo(vivos);
        mafioso.estaVivo(vivos);

        assertTrue(vivos.contains(ciudadano1), "El jugador vivo debería agregarse a la lista");
        assertTrue(vivos.contains(ciudadano2), "El jugador vivo debería agregarse a la lista");
        assertTrue(vivos.contains(mafioso), "El jugador vivo debería agregarse a la lista");
        assertEquals(3, vivos.size());
    }

    @Test
    public void alMorirUnJugadorNoSeAgregaALaListaDeVivos() {
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador mafioso = new Jugador("mafioso", new Mafioso());

        List<Jugador> vivos = new ArrayList<>();
        ciudadano1.morir();
        ciudadano1.estaVivo(vivos);
        ciudadano2.estaVivo(vivos);
        mafioso.estaVivo(vivos);

        assertFalse(vivos.contains(ciudadano1), "Un jugador muerto no debería agregarse a la lista de vivos");
        assertTrue(vivos.contains(ciudadano2), "El jugador vivo debería agregarse a la lista");
        assertTrue(vivos.contains(mafioso), "El jugador vivo debería agregarse a la lista");
        assertEquals(2, vivos.size());
    }

    @Test
    public void unJugadorVivoPuedeEmitirUnVotoEnLaUrna() {
        Jugador votante = new Jugador("votante", new Mafioso());
        Jugador victima = new Jugador("victima", new Ciudadano());
        Urna urna = new Urna();

        votante.votarComoVictimaA(victima, urna);

        assertEquals(victima, urna.jugadorMasVotado(), "El voto del jugador vivo debió registrarse en la urna");
    }

    @Test
    public void unJugadorMuertoIntentaVotarAOtroJugadorLanzaJugadorMuertoExcepcion() {
        Jugador votanteMuerto = new Jugador("votante muerto", new Mafioso());

        Jugador victimaDelMuerto = new Jugador("victima del muerto", new Ciudadano());

        Urna urna = new Urna();

        votanteMuerto.morir();

        assertThrows(JugadorMuertoExcepcion.class, () -> votanteMuerto.votarComoVictimaA(victimaDelMuerto, urna));

    }

    @Test
    public void unJugadorVivoIntentaVotarAUnJugadorMuertoLanzaExcepcion() {
        Jugador votanteVivo = new Jugador("votante vivo", new Mafioso());
        Jugador victimaMuerta = new Jugador("victima muerta", new Ciudadano());
        Urna urna = new Urna();

        victimaMuerta.morir();

        assertThrows(JugadorMuertoExcepcion.class, () -> { votanteVivo.votarComoVictimaA(victimaMuerta, urna);},
                "No se debería poder ingresar un voto si la víctima ya está muerta");
    }

    @Test
    public void unJugadorSiempreSeAgregaALaListaDeConocidosAlPreguntarPorSiMismo() {
        Jugador jugador = new Jugador("mafioso", new Mafioso());
        List<Jugador> complices = new ArrayList<>();

        jugador.puedeConocerElRolDe(jugador, complices);

        assertTrue(complices.contains(jugador), "Un jugador siempre debe poder ver su propio rol");
        assertEquals(1, complices.size());
    }

    @Test
    public void siSePreguntaSiElJugadorConoceAOtroJugadorLoDelegaEnSuCarta() {
        Jugador mafioso1 = new Jugador("mafioso1", new Mafioso());
        Jugador mafioso2 = new Jugador("mafioso2", new Mafioso());
        List<Jugador> conocidos = new ArrayList<>();

        mafioso1.puedeConocerElRolDe(mafioso2, conocidos);
        assertTrue(conocidos.contains(mafioso2), "El jugador debió delegar en su carta para conocer al otro");
    }

    @Test
    public void matarAUnJugadorQueYaEstaMuertoNoAlteraElJuego() {
        Jugador jugador = new Jugador("Kenny", new Ciudadano());
        List<Jugador> vivos = new ArrayList<>();

        jugador.morir();

        assertDoesNotThrow(jugador::morir, "Matar a jugador muerto no debería lanzar error, debería ser ignorado");

        jugador.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "El jugador debe seguir muerto");
    }

    @Test
    public void vistoPorMafiaDelegaEnLaCartaParaAgregarseALaLista() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        List<Jugador> complices = new ArrayList<>();

        mafioso.vistoPorMafia(complices);

        assertTrue(complices.contains(mafioso), "El jugador debió delegar en su carta y agregarse a la lista de cómplices");
    }

    @Test
    public void validarBandoYPostularseComoCandidatoDelegaEnLaCarta() {
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());
        List<Jugador> opciones = new ArrayList<>();

        ciudadano.validarBandoYPostularseComoCandidatoParaMafia(opciones);

        assertTrue(opciones.contains(ciudadano), "El jugador debió delegar en su carta para ser candidato");
    }

    @Test
    public void unJugadorMuertoNoDeberiaPostularseComoCandidatoParaLaMafia() {
        Jugador ciudadanoMuerto = new Jugador("ciudadano muerto", new Ciudadano());
        List<Jugador> opcionesParaMatar = new ArrayList<>();

        ciudadanoMuerto.morir();
        ciudadanoMuerto.postularseComoCandidatoParaMafia(opcionesParaMatar);

        assertFalse(opcionesParaMatar.contains(ciudadanoMuerto), "Un muerto no debería ser opción para la mafia");
    }

    @Test
    public void unMafiosoMuertoNoDeberiaAparecerEnLaListaDeComplices() {
        Jugador mafiosoMuerto = new Jugador("mafioso muerto", new Mafioso());
        List<Jugador> complices = new ArrayList<>();

        mafiosoMuerto.morir();
        mafiosoMuerto.vistoPorMafia(complices);

        assertFalse(complices.contains(mafiosoMuerto), "Un mafioso muerto no debe agregarse a la lista de cómplices");
    }

    @Test
    public void unCiudadanoVivoSePostulaComoCandidatoParaLaMafia() {
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());
        List<Jugador> opciones = new ArrayList<>();

        ciudadano.postularseComoCandidatoParaMafia(opciones);

        assertTrue(opciones.contains(ciudadano), "El ciudadano vivo debió delegar en su carta y agregarse");
        assertEquals(1, opciones.size());
    }

    @Test
    public void unCiudadanoMuertoNoSePostulaComoCandidatoParaLaMafia() {
        Jugador ciudadanoMuerto = new Jugador("ciudadano muerto", new Ciudadano());
        List<Jugador> opciones = new ArrayList<>();

        ciudadanoMuerto.morir();
        ciudadanoMuerto.postularseComoCandidatoParaMafia(opciones);

        assertFalse(opciones.contains(ciudadanoMuerto), "El ciudadano muerto debió ser frenado por su estado (Null Object)");
    }
}
