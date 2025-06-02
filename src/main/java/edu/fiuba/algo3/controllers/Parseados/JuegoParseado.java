package edu.fiuba.algo3.controllers.Parseados;
import java.util.List;

public class JuegoParseado {
    private List<RondaParseada> rondas;
    private List<MazoParseado> mazo;

    public List<RondaParseada> getRondas() {
        return rondas;
    }

    public void setRondas(List<RondaParseada> rondas) {
        this.rondas = rondas;
    }

    public List<MazoParseado> getMazo() {
        return mazo;
    }

    public void setMazo(List<MazoParseado> mazo) {
        this.mazo = mazo;
    }}