package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

public class RevelarCarta extends AccionJugador {
    Jugador revelado;
    Rol rolDescubierto;

    public RevelarCarta(Jugador revelado) {
        this.revelado = revelado;
    }

    @Override
    public void enVivo() {
        throw new JugadorVivoExcepcion("Un jugador vivo no debería revelar su carta de rol.");
    }

    @Override
    public void enMuerto() {
        this.rolDescubierto = this.revelado.continuarRevelandoCarta();
    }

    public Rol obtenerResultado() {
        return this.rolDescubierto;
    }
}
