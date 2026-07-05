package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.fase.OrganizadorDeTurnosNocturnos;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.vistas.FaseNocturnaVista;
import edu.fiuba.paradigmas.vistas.App;

import java.util.List;
import java.util.Queue;

public class FaseNocturnaController {

    private final FaseNocturnaVista vista;
    private final App app;
    private final Moderador moderador;
    private final Queue<Jugador> turnosPendientes;

    private boolean nocheResuelta;

    public FaseNocturnaController(FaseNocturnaVista vista, App app, Moderador moderador) {
        this.vista = vista;
        this.app = app;
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
            this.app.irAEstadoPartidaPreDia(this.moderador, resultado);
        }
    }
}