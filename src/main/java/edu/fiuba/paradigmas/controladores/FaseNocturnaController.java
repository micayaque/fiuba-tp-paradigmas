package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.fase.OrganizadorDeTurnosNocturnos;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.vistas.FaseNocturnaVista;

import java.util.List;
import java.util.Queue;

public class FaseNocturnaController {

    private final FaseNocturnaVista vista;
    private final ControladorDeJuego orquestador;
    private final Moderador moderador;
    private final Queue<Jugador> turnosPendientes;

    private boolean nocheResuelta;

    public FaseNocturnaController(FaseNocturnaVista vista, ControladorDeJuego orquestador, Moderador moderador) {
        this.vista = vista;
        this.orquestador = orquestador;
        this.moderador = moderador;
        this.nocheResuelta = false;

        List<Jugador> vivos = this.moderador.jugadoresVivos();
        this.vista.cargarOpciones(vivos);
        OrganizadorDeTurnosNocturnos organizador = new OrganizadorDeTurnosNocturnos();
        this.turnosPendientes = organizador.armarColaDeTurnos(vivos);

        this.avanzarTurno();
    }

    public void avanzarTurno() {
        if (this.nocheResuelta) {
            return;
        }
        Jugador jugadorActivo = this.turnosPendientes.poll();
        if (jugadorActivo != null) {
            this.vista.iniciarTurnoOcultoDe(jugadorActivo.nombre());
            ConfiguradorDeTurnoUIController config = new ConfiguradorDeTurnoUIController(
                    jugadorActivo, this.vista, this.moderador, this
            );
            config.configurarPantalla();
        } else {
            this.nocheResuelta = true;
            ResultadoFase resultado = this.moderador.resolverVotacion();
            this.moderador.comenzarFaseDiurna();
            this.orquestador.irAEstadoPartidaPreDia(resultado);
        }
    }
}