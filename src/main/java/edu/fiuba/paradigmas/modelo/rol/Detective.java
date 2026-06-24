package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.excepciones.rol.InvestigacionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;

public class Detective extends Rol {

    private Jugador ultimoInvestigado;

    public Detective(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        contador.sumarDetective();
    }

    @Override
    public String nombre() {
        return "Detective";
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