package edu.fiuba.algo3.modelo.Cartas;

import edu.fiuba.algo3.modelo.Recursos.Grano;
import edu.fiuba.algo3.modelo.Recursos.Lana;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.TipoDeRecurso;
import edu.fiuba.algo3.modelo.interfaces.Comprable;

import java.util.List;

abstract public class CartaDesarrollo {
    private int TurnoCreacion;

    public CartaDesarrollo(int turno) {
        this.TurnoCreacion = turno;
    }

    public boolean SePuedeUsar(int turno) {
        return !(TurnoCreacion == turno);
    }

    public static List<TipoDeRecurso> costoRecursos() {
        return List.of(
                new Grano(1), new Mineral(1),new Lana(1)
        );
    }

}
