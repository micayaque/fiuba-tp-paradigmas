package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Contruccion.Poblado;
import edu.fiuba.algo3.modelo.Tablero.Dados;
import edu.fiuba.algo3.modelo.Tablero.Factory.Coordenada;

import java.util.ArrayList;
import java.util.List;

public class SesionDeJuego {
    private static SesionDeJuego instancia;
    private Catan catan;
    private ManagerTurno managerTurno;

    private SesionDeJuego() {
        this.catan = new Catan();
    }

    public static SesionDeJuego obtenerInstancia() {
        if (instancia == null) {
            instancia = new SesionDeJuego();
        }
        return instancia;
    }

    public void setCatan(Catan catan) {
        this.catan = catan;
    }

    public void iniciarPartida(List<String> nombres, List<Color> colores) {
        // Reiniciar para nueva partida
        if(this.catan ==null)
            this.catan = new Catan(); // O catan.reiniciar()

        // Crear y agregar jugadores
        for (int i = 0; i < nombres.size(); i++) {
            Jugador jugador = new Jugador(nombres.get(i), colores.get(i));
            catan.agregarJugador(jugador);
        }

        // Iniciar partida en Catan
        this.managerTurno = Catan.getInstance().getManagerTurno();
    }

    // Métodos que la UI necesita
    public void colocarPoblado(Coordenada coordenada) {

            managerTurno.construirPoblado(coordenada);

    }

    public void mejorarACiudad(Coordenada coordenada) {

        managerTurno.mejorarACiudad(coordenada);

    }

//    public Dados tirarDados() {
//        return managerTurno.tirarDadosYDistribuir();
//    }

    public void pasarTurno() {
        managerTurno.siguienteTurno();
    }

    // Getters para la UI
    public ManagerTurno getManagerTurno() { return managerTurno; }
    public Catan getCatan() { return catan; }
    public boolean partidaIniciada() { return managerTurno != null; }
}
