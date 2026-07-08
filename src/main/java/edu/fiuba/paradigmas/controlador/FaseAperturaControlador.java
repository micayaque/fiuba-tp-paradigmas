package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vista.fase.PaseDispositivoVista;
import edu.fiuba.paradigmas.vista.fase.apertura.RevelarRolVista;

import java.util.ArrayList;
import java.util.List;

public class FaseAperturaControlador extends FaseControlador {

    public FaseAperturaControlador(JuegoControlador orquestador, Moderador moderador) {
        super(orquestador, moderador);
        this.mostrarPantallaDePase();
    }

    @Override
    protected void mostrarPantallaDePase() {
        Jugador jugadorEnTurno = this.jugadoresVivos.get(this.indiceActual);
        PaseDispositivoVista vistaPase = new PaseDispositivoVista(jugadorEnTurno.nombre());

        vistaPase.configurarBotonAvanzar(this::mostrarPantallaDeRol);
        this.orquestador.cambiarEscena(vistaPase);
    }

    private void mostrarPantallaDeRol() {
        Jugador jugadorEnTurno = this.jugadoresVivos.get(this.indiceActual);
        String nombreJugador = jugadorEnTurno.nombre();
        String nombreRol = this.traductorDeRoles.traducirRolDe(jugadorEnTurno);

        String complices = "";
        String colorTema = "#a0aec0";

        switch (nombreRol) {
            case "Mafioso":
            case "Padrino":
                complices = this.buscarComplices(jugadorEnTurno);
                colorTema = "#ff416c";
                break;
            case "Medico":
                colorTema = "#00b894";
                break;
            case "Detective":
                colorTema = "#3182ce";
                break;
        }

        RevelarRolVista vistaRol = new RevelarRolVista(nombreJugador, nombreRol, complices, colorTema);

        vistaRol.configurarBotonAvanzar(this::avanzarAlSiguienteTurno);
        this.orquestador.cambiarEscena(vistaRol);
    }

    private String buscarComplices(Jugador jugadorMafioso) {
        List<String> nombresComplices = new ArrayList<>();

        for (Jugador j : this.jugadoresVivos) {
            if (!j.equals(jugadorMafioso)) {
                String rolCompanero = this.traductorDeRoles.traducirRolDe(j);
                if (rolCompanero.equals("Mafioso") || rolCompanero.equals("Padrino")) {
                    nombresComplices.add(j.nombre() + " (" + rolCompanero + ")");
                }
            }
        }

        if (nombresComplices.isEmpty()) {
            return "Estás solo. \nSos el único integrante \nde la Mafia.";
        }
        return String.join("\n", nombresComplices);
    }

    @Override
    protected void finalizarFase() {
        this.orquestador.iniciarPrimeraNoche();
    }
}