package edu.fiuba.paradigmas.modelo.rol;

public class ContadorDeRoles {

    private int cantidadCiudadanos;
    private int cantidadMafiosos;
    private int cantidadDetective;
    private int cantidadMedico;
    private int cantidadPadrino;
    private int cantidadSheriff;

    public ContadorDeRoles(){
        this.cantidadCiudadanos=0;
        this.cantidadMafiosos = 0;
        this.cantidadDetective=0;
    }

    public void sumarCiudadano() {
        this.cantidadCiudadanos++;
    }

    public void sumarMafioso() {
        this.cantidadMafiosos++;
    }

    public void sumarDetective() {
        this.cantidadDetective++;
    }

    public int cantidadDetectives() {
        return this.cantidadDetective;
    }

    public int cantidadMafiosos() {
        return this.cantidadMafiosos;
    }

    public int cantidadCiudadanos() {
        return this.cantidadCiudadanos;
    }

    public int cantidadMedicos() {
        return this.cantidadMedico;
    }

    public void sumarMedico() {
        this.cantidadMedico++;
    }

    public int cantidadPadrinos() {
        return  this.cantidadPadrino;
    }

    public void sumarPadrino() {
        this.cantidadPadrino++;
    }

    public int cantidadSheriffs() {
        return this.cantidadSheriff;
    }

    public void sumarSheriff() {
        this.cantidadSheriff++;
    }

}
