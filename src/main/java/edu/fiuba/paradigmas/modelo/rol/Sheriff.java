package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.revelacion.EstadoRevelacion;
import edu.fiuba.paradigmas.modelo.rol.revelacion.NoRevelado;

import java.util.List;

public class Sheriff extends Rol{

    private EstadoRevelacion estadoRevelacion;

    public Sheriff(){
        super(new Ciudadanos());
        this.estadoRevelacion = new NoRevelado();
    }

    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        contador.sumarSheriff();
    }

    @Override
    public String nombre() {
        return "Sheriff";
    }

    @Override
    public void revelarComoSheriff() {
        this.estadoRevelacion.revelar(this);
    }

    public void cambiarEstadoRevelacion(EstadoRevelacion nuevoEstado) {
        this.estadoRevelacion = nuevoEstado;
    }

    @Override
    public void agregarComoObjetivoPrioritario(Jugador jugador, List<Jugador> objetivos) {
        this.estadoRevelacion.esObjetivoPrioritario(jugador, objetivos);
    }
}
