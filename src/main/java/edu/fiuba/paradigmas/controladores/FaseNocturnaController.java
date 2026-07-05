package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.fase.OrganizadorDeTurnosNocturnos;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.vistas.FaseNocturnaVista;

import java.util.List;

public class FaseNocturnaController extends ControladorDeFasePorTurnos {

    private final FaseNocturnaVista vista;

    public FaseNocturnaController(FaseNocturnaVista vista, ControladorDeJuego orquestador, Moderador moderador) {
        super(orquestador, moderador, new OrganizadorDeTurnosNocturnos().armarColaDeTurnos(moderador.jugadoresVivos()));
        this.vista = vista;
        this.avanzarTurno();
    }

    @Override
    protected void configurarTurnoPara(Jugador jugadorActivo, List<Jugador> elegibles) {
        this.vista.cargarOpciones(elegibles);
        this.vista.iniciarTurnoOcultoDe(jugadorActivo.nombre());
        ConfiguradorDeTurnoUIController config = new ConfiguradorDeTurnoUIController(
                jugadorActivo, this.vista, this.moderador, this
        );
        config.configurarPantalla();
    }

    @Override
    protected void resolverFase() {
        ResultadoFase resultado = this.moderador.resolverVotacion();
        this.moderador.comenzarFaseDiurna();
        this.orquestador.irAEstadoPartidaPreDia(resultado);
    }
}