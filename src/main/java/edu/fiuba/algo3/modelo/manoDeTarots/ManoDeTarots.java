package edu.fiuba.algo3.modelo.manoDeTarots;

import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;

public class ManoDeTarots {
    private ArrayList<Tarot> tarots;
    private int cantidadMaxima;

    public ManoDeTarots(){
        this.tarots = new ArrayList<Tarot>();
        this.cantidadMaxima = 2;

    }
    public void guardar(Seleccionable tarot){
        Tarot tarotGuardado = (Tarot) tarot;
        this.tarots.add(tarotGuardado);
    }

    public Tarot usarTarot(Tarot tarot){
        Tarot  tarotUsado;
        if(this.tarots.contains(tarot)){
            tarotUsado = this.tarots.get(this.tarots.indexOf(tarot));
            this.tarots.remove(tarot);
        }else {
            throw new TarotInexistente();
        }
        return tarotUsado;
    }
}
