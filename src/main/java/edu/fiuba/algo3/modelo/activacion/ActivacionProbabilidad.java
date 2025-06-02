package edu.fiuba.algo3.modelo.activacion;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import java.util.Random;
public class ActivacionProbabilidad implements Activacion {
    private int probabilidad;
    private boolean comodinUsable;

    public ActivacionProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
        this.comodinUsable = true;
    }

    @Override
    public boolean esActivable(ManoDePoker mano) {
        if (!comodinUsable) {
            return false;
        }

        Random random = new Random();
        int numeroAObtener = random.nextInt(probabilidad) + 1;
        int numeroGenerado = random.nextInt(probabilidad) + 1;

        if (numeroAObtener != numeroGenerado) {
            comodinUsable = false;
        }

        return comodinUsable;
    }

    public boolean estaRoto() {
        return !comodinUsable;
    }
}