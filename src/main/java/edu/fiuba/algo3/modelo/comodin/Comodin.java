package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;
import edu.fiuba.algo3.modelo.activacion.Activacion;
import edu.fiuba.algo3.modelo.efectos.Efecto;
import java.util.List;

public class Comodin implements Seleccionable {
    private Efecto efecto;
    private String descripcion;
    private String nombre;
    private List<Activacion> activacion;

    public Comodin(String nombre, String descripcion,Efecto efecto, List<Activacion> activacion) {
        this.efecto = efecto;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.activacion = activacion;
    }
    public Puntaje aplicarA(ManoDePoker mano) {
        Puntaje puntajeMano = mano.devolverPuntaje();
        Puntaje puntajeModificado = puntajeMano;
        int contadorDeActivacion = 0;
        for(Activacion activacion : activacion) {
            if (activacion.esActivable(mano)) {
                contadorDeActivacion++;
            }
        }
        if(contadorDeActivacion == activacion.size()) {
            puntajeModificado = efecto.aplicarEfecto(puntajeMano);
        }
        return puntajeModificado;
    }

    public String obtenerNombre(){
        return this.nombre;
    }
    public boolean equals(Comodin comodin) {
        return this.nombre.equals(comodin.nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }
}


