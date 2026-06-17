package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.excepciones.InvestigacionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Detective extends Rol {

    private Jugador ultimoInvestigado;

    public Detective(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarDetective();
    }

    @Override
    public ResultadoInvestigacion investigarComoDetectiveA(Jugador investigado) {
        if (investigado == this.ultimoInvestigado) {
            throw new InvestigacionRepetidaExcepcion("El Detective no puede investigar al mismo jugador dos noches consecutivas.");
        }
        this.ultimoInvestigado = investigado;
        return investigado.serInvestigado();
    }

}