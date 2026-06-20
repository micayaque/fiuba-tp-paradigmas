package edu.fiuba.paradigmas.modelo.moderador;

import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;

import java.util.List;

public class ModeradorFaseNocturna {

    private final FaseNocturna faseNocturna;

    public ModeradorFaseNocturna(FaseNocturna faseNocturna) {
        this.faseNocturna = faseNocturna;
    }
    
    public void ejecutarFaseNocturna(List<AccionJugador> votos, AccionJugador proteccion) {
        faseNocturna.recibirProteccion(proteccion.votante(), proteccion.votado());        
        this.procesarVotosYEjecutar(votos);
    }

    public void ejecutarFaseNocturna(List<AccionJugador> votos) {
        this.procesarVotosYEjecutar(votos);
    }

    private void procesarVotosYEjecutar(List<AccionJugador> votos) {
        for (AccionJugador intencion : votos) faseNocturna.recibirVoto(intencion.votante(), intencion.votado());
        AccionVotacion accionMafia = faseNocturna.ejecutarResultadoVotacion();
        accionMafia.ejecutar(this.faseNocturna);
    }
}