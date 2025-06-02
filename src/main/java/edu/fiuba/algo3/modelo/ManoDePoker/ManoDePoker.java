package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Modificable.Modificable;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.Objects;


public abstract class ManoDePoker implements Modificable {
    protected String nombre;
    protected Puntaje puntaje;
    protected double probabilidad;

    public void jugar(ArrayList<Carta> cartas){
        for (Carta carta : cartas){
            this.puntaje.sumarConPuntajeYMultiplicadorMenos1(carta.obtenerPuntaje());
        }

    }

    public Boolean compararConNombre(String nombre){
        return this.nombre.equals(nombre);
    }

    public Boolean compararConProbabilidad(double probabilidad){
        return  Precision.equals(this.probabilidad, probabilidad, 0.03);
    }

    public Puntaje calcularPuntaje(){
        return this.puntaje;
    }

    public Puntaje devolverPuntaje(){
        return this.puntaje;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        ManoDePoker otraManoDePoker = (ManoDePoker) obj;
        return(Objects.equals(nombre,otraManoDePoker.nombre));
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }

    /*tanto carta como mano de poker tienen puntaje, por lo tanto ellos son los modificables
    * el puntaje que se modifica debería estar implementado dentro de cada uno de ellos*/

    @Override
    public void aplicarModificacion(Puntaje valor) {
        this.puntaje.aplicarModificacion(valor);
    }
    public boolean esIgual(Modificable otroModificable){
        return this.equals(otroModificable);
    }

    @Override
    public Boolean validarModificable(Modificador modificador){
        return modificador.validarTipo(this.nombre);
    }
}

