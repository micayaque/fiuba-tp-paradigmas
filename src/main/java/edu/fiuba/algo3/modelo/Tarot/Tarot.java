package edu.fiuba.algo3.modelo.Tarot;
import edu.fiuba.algo3.modelo.Modificable.Modificable;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;

public class Tarot implements Seleccionable {
    private Puntaje puntaje;
    private String descripcion;
    private String nombre;
    private Modificador modificador;

    public Tarot(String nombre, String descripcion, Puntaje puntaje, Modificador modificador) {
        this.puntaje = puntaje;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.modificador = modificador;
    }
    public void aplicarA(Modificable modificable) {
        if(modificador.modificar(modificable)) {
            modificable.aplicarModificacion(this.puntaje);
        }else{
            throw new ModificableEquivocadoException();
        }
    }

    public String obtenerNombre(){
        return this.nombre;
    }

    public boolean puedeModificar(Modificable modificable) {
        return modificador.modificar(modificable);
    }

    public boolean equals(Tarot tarot) {
        return this.nombre.equals(tarot.nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }
}
