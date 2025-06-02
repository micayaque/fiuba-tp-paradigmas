package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Tarot.Tarot;

import java.util.ArrayList;

public class MazoDeTarots {
    ArrayList<Tarot> tarots;

    public MazoDeTarots(){
        this.tarots = new ArrayList<Tarot>();
    }

    public void agregar(Tarot tarot){
        if(tarots.size() >2 ){
            this.tarots.add(tarot);
        }
    }
}
