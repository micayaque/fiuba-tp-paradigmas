package edu.fiuba.algo3.controllers.Parseados;

public class TarotParseado {
    private String nombre;
    private String descripcion;
    private EfectoParseado efecto;
    private String sobre;
    private String ejemplar;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EfectoParseado getEfecto() {
        return efecto;
    }

    public void setEfectoParseado(EfectoParseado efecto) {
        this.efecto = efecto;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(String ejemplar) {
        this.ejemplar = ejemplar;
    }
}