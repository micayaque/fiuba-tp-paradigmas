package edu.fiuba.paradigmas.unitarios.jugador;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JugadorTest {

    @Test
    public void elJugadorConservaSuNombre() {
        Jugador jugador = new Jugador("Vito", new Mafioso());

        assertEquals("Vito", jugador.nombre());
    }

    @Test
    public void elJugadorSeCuentaSegunSuCartaEnElContador() {
        Jugador jugador = new Jugador("Vito", new Mafioso());
        Random random = new Random();
        CreadorDeJugadores contador = new CreadorDeJugadores(random);

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

        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateNocturnoMafia());
        votante.votarComoMafiosoA(victima, urnaVotacion);

        ResultadoVotacion resultadoVotacion = urnaVotacion.contarVotos();
        resultadoVotacion.resolver().ejecutar(new FaseNocturna());

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);

        assertFalse(vivos.contains(victima), "El voto del jugador vivo debió registrarse, resolverse y matar a la víctima");
    }

    @Test
    public void unJugadorMuertoIntentaVotarAOtroJugadorLanzaJugadorMuertoExcepcion() {
        Jugador votanteMuerto = new Jugador("votante muerto", new Mafioso());

        Jugador victimaDelMuerto = new Jugador("victima del muerto", new Ciudadano());

        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateNocturnoMafia());

        votanteMuerto.morir();

        assertThrows(JugadorMuertoExcepcion.class, () -> votanteMuerto.votarComoMafiosoA(victimaDelMuerto, urnaVotacion));

    }

    @Test
    public void unJugadorVivoIntentaVotarAUnJugadorMuertoLanzaExcepcion() {
        Jugador votanteVivo = new Jugador("votante vivo", new Mafioso());
        Jugador victimaMuerta = new Jugador("victima muerta", new Ciudadano());
        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateNocturnoMafia());

        victimaMuerta.morir();

        assertThrows(JugadorMuertoExcepcion.class, () -> { votanteVivo.votarComoMafiosoA(victimaMuerta, urnaVotacion);},
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
    public void vistoPorMafiaDelegaEnLaCartaParaAgregarseALaLista() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        List<Jugador> complices = new ArrayList<>();

        mafioso.vistoPorMafia(complices);

        assertTrue(complices.contains(mafioso), "El jugador debió delegar en su carta y agregarse a la lista de cómplices");
    }

    @Test
    public void unJugadorMuertoNoDeberiaPostularseComoCandidatoParaLaMafia() {
        Jugador ciudadanoMuerto = new Jugador("ciudadano muerto", new Ciudadano());
        List<Jugador> opcionesParaMatar = new ArrayList<>();

        ciudadanoMuerto.morir();
        ciudadanoMuerto.vistoPorMafia(opcionesParaMatar);

        assertFalse(opcionesParaMatar.contains(ciudadanoMuerto), "Un muerto no debería ser opción para la mafia");
    }

    @Test
    public void unCiudadanoMuertoNoSePostulaComoCandidatoParaLaMafia() {
        Jugador ciudadanoMuerto = new Jugador("ciudadano muerto", new Ciudadano());
        List<Jugador> opciones = new ArrayList<>();

        ciudadanoMuerto.morir();
        ciudadanoMuerto.vistoPorMafia(opciones);

        assertFalse(opciones.contains(ciudadanoMuerto), "El ciudadano muerto debió ser frenado por su estado (Null Object)");
    }
    
    @Test
    public void continuarRevelandoIdentidadDelegaEnLaCarta() {
        Rol cartaMock = mock(Rol.class);
        Jugador sospechoso = new Jugador("Sospechoso", cartaMock);
        Jugador investigadorDummy = mock(Jugador.class);

        sospechoso.continuarRevelandoIdentidad();

        verify(cartaMock, times(1)).revelarBando();
    }
}
