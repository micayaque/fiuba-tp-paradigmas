package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.excepciones.rol.InvestigacionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.mazo.ContadorDeRoles;

public class Detective extends Rol {

    private Jugador ultimoInvestigado = new Jugador("para evitar null pointer exception", new Ciudadano());

    public Detective(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarDetective();
    }

    @Override
    public Bando investigarComoDetectiveA(Jugador sospechoso) {
        if (sospechoso == this.ultimoInvestigado) {
            throw new InvestigacionRepetidaExcepcion("El Detective no puede investigar al mismo jugador dos noches consecutivas.");
        }
        Bando bandoDescubierto = sospechoso.serInvestigado();
        this.ultimoInvestigado = sospechoso;
        return bandoDescubierto;
    }
}