package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;
import edu.fiuba.paradigmas.vistas.FaseNocturnaVista;

public class ConfiguradorDeTurnoUIController implements IdentificadorRol {

    private final Jugador jugadorActivo;
    private final FaseNocturnaVista vista;
    private final Moderador moderador;
    private final FaseNocturnaController controlador;

    public ConfiguradorDeTurnoUIController(Jugador jugadorActivo, FaseNocturnaVista vista, Moderador moderador, FaseNocturnaController controlador) {
        this.jugadorActivo = jugadorActivo;
        this.vista = vista;
        this.moderador = moderador;
        this.controlador = controlador;
    }

    public void configurarPantalla() {
        this.jugadorActivo.identificarRolEn(this);
    }

    @Override
    public void esMafioso() {
        this.vista.setTitulo("Mafia - " + this.jugadorActivo.nombre());
        this.vista.setInstruccion("Elegí a tu víctima para esta noche.");

        this.vista.configurarBotonConValidacionDeSeleccion("Asesinar", () -> {
            Jugador victima = this.vista.obtenerJugadorSeleccionado();
            if (this.jugadorActivo == victima) {
                this.vista.mostrarMensaje("No podés votarte a vos mismo.");
                return;
            }
            try {
                this.moderador.registrarVoto(this.jugadorActivo, victima);
                this.controlador.avanzarTurno();

            } catch (RuntimeException excepcion) {
                this.vista.mostrarMensaje(excepcion.getMessage());
            }
        });
    }

    @Override
    public void esPadrino() {
        this.esMafioso();
    }

    @Override
    public void esMedico() {
        this.vista.setTitulo("Médico - " + this.jugadorActivo.nombre());
        this.vista.setInstruccion("Elegí a qué jugador querés proteger esta noche.");

        this.vista.configurarBotonConValidacionDeSeleccion("Proteger", () -> {
            Jugador paciente = this.vista.obtenerJugadorSeleccionado();

            this.moderador.registrarProteccion(this.jugadorActivo, paciente);
            this.controlador.avanzarTurno();
        });
    }

    @Override
    public void esDetective() {
        this.vista.setTitulo("Detective - " + this.jugadorActivo.nombre());
        this.vista.setInstruccion("Elegí a un jugador para investigar su bando.");

        this.vista.configurarBotonConValidacionDeSeleccion("Investigar", () -> {
            Jugador sospechoso = this.vista.obtenerJugadorSeleccionado();

            Bando bando = this.moderador.registrarInvestigacion(this.jugadorActivo, sospechoso);
            String textoBando = bando instanceof Mafia ? "Mafia" : "Ciudadano";
            this.vista.mostrarMensaje("Resultado de la investigación: " +  textoBando);
            this.vista.ocultarSelector();
            this.vista.configurarBotonLibre("Ocultar investigación y continuar", this.controlador::avanzarTurno);
        });
    }

    @Override
    public void esCiudadano() {
        this.vista.setTitulo("Ciudadano - " + this.jugadorActivo.nombre());
        this.vista.setInstruccion("Sos un ciudadano común. No tenés acciones nocturnas.\nTomate unos segundos y simulá estar eligiendo a alguien para no levantar sospechas.");
        this.vista.ocultarSelector();
        this.vista.configurarBotonLibre("Ocultar y continuar", this.controlador::avanzarTurno);
    }

    @Override
    public void esSheriff() {
        this.vista.setTitulo("Sheriff - " + this.jugadorActivo.nombre());
        this.vista.setInstruccion("Tu rol es de acción diurna. No tenés acciones nocturnas.\nHacé tiempo unos segundos para despistar a la mafia.");
        this.vista.ocultarSelector();
        this.vista.configurarBotonLibre("Ocultar y continuar", this.controlador::avanzarTurno);
    }
}