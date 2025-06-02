package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.controllers.Parser.ErrorAlParsearJson;
import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.ManoDePoker.*;

import java.util.ArrayList;
import java.util.List;

public class FactoryDeManosDePoker {
    private final List<ManoDePoker> manosDePoker;

    public FactoryDeManosDePoker() {
        this.manosDePoker = new ArrayList<ManoDePoker>();
        this.manosDePoker.add(new CartaMasAlta());
        this.manosDePoker.add(new Color());
        this.manosDePoker.add(new DoblePar());
        this.manosDePoker.add(new EscaleraColor());
        this.manosDePoker.add(new EscaleraReal());
        this.manosDePoker.add(new EscaleraSimple());
        this.manosDePoker.add(new FullHouse());
        this.manosDePoker.add(new Par());
        this.manosDePoker.add(new Poker());
        this.manosDePoker.add(new Trio());
    }


    public ManoDePoker generarManoDePokerPorNombre(String nombre) {
        ManoDePoker manoBuscada = null;
        for (ManoDePoker mano: this.manosDePoker) {
            if (mano.compararConNombre(nombre)) {
                manoBuscada = mano;
            }
        }
        if (manoBuscada == null) {
            throw new ErrorAlParsearJson();
        }
        return manoBuscada;
    }

    public ManoDePoker generarManoDePokerPorProbabilidad(Double probabilidad) {
        ManoDePoker manoBuscada = null;
        Double probalidadBuscada = 1 / probabilidad;
        for (ManoDePoker mano: this.manosDePoker) {
            if (mano.compararConProbabilidad(probalidadBuscada)){
                manoBuscada = mano;
            }
        }
        if (manoBuscada == null) {
            throw new ErrorAlParsearJson();
        }
        return manoBuscada;
    }


}
