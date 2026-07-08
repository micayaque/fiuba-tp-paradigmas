package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;

import java.util.*;

public class OrganizadorDeTurnosNocturnos {

    public Queue<Jugador> armarColaDeTurnos(List<Jugador> jugadoresVivos) {
        List<Jugador> mafiosos = new ArrayList<>();
        List<Jugador> detectives = new ArrayList<>();
        List<Jugador> medicos = new ArrayList<>();
        List<Jugador> rolesDiurnos = new ArrayList<>();

        for (Jugador vivo : jugadoresVivos) {
            vivo.identificarRolEn(new IdentificadorRol() {
                @Override public void esMafioso() { mafiosos.add(vivo); }
                @Override public void esPadrino() { mafiosos.add(vivo); }
                @Override public void esDetective() { detectives.add(vivo); }
                @Override public void esMedico() { medicos.add(vivo); }
                @Override public void esCiudadano() { rolesDiurnos.add(vivo); }
                @Override public void esSheriff() { rolesDiurnos.add(vivo); }
            });
        }

        Collections.shuffle(mafiosos);
        Collections.shuffle(rolesDiurnos);

        List<Jugador> ordenFinal = new LinkedList<>();
        ordenFinal.addAll(mafiosos);
        ordenFinal.addAll(detectives);
        ordenFinal.addAll(medicos);

        Random random = new Random();
        for (Jugador pasivo : rolesDiurnos) {
            int indiceAleatorio = random.nextInt(ordenFinal.size() + 1);
            ordenFinal.add(indiceAleatorio, pasivo);
        }

        return new LinkedList<>(ordenFinal);
    }
}