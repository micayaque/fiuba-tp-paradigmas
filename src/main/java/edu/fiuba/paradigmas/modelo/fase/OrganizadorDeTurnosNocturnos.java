package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;

import java.util.*;

public class OrganizadorDeTurnosNocturnos implements IdentificadorRol {

    private final List<Jugador> mafiosos = new ArrayList<>();
    private final List<Jugador> detectives = new ArrayList<>();
    private final List<Jugador> medicos = new ArrayList<>();
    private final List<Jugador> rolesDiurnos = new ArrayList<>();

    private Jugador jugadorEvaluado;

    public Queue<Jugador> armarColaDeTurnos(List<Jugador> jugadoresVivos) {
        for (Jugador vivo : jugadoresVivos) {
            this.jugadorEvaluado = vivo;
            vivo.identificarRolEn(this);
        }

        Collections.shuffle(this.mafiosos);
        Collections.shuffle(this.medicos);
        Collections.shuffle(this.detectives);
        Collections.shuffle(this.rolesDiurnos);

        List<Jugador> ordenFinal = new LinkedList<>();
        ordenFinal.addAll(this.mafiosos);
        ordenFinal.addAll(this.medicos);
        ordenFinal.addAll(this.detectives);

        Random random = new Random();
        for (Jugador pasivo : this.rolesDiurnos) {
            int indiceAleatorio = random.nextInt(ordenFinal.size() + 1);
            ordenFinal.add(indiceAleatorio, pasivo);
        }

        return new LinkedList<>(ordenFinal);
    }

    @Override public void esMafioso() { this.mafiosos.add(this.jugadorEvaluado); }
    @Override public void esPadrino() { this.mafiosos.add(this.jugadorEvaluado); }
    @Override public void esDetective() { this.detectives.add(this.jugadorEvaluado); }
    @Override public void esMedico() { this.medicos.add(this.jugadorEvaluado); }

    @Override public void esCiudadano() { this.rolesDiurnos.add(this.jugadorEvaluado); }
    @Override public void esSheriff() { this.rolesDiurnos.add(this.jugadorEvaluado); }
}