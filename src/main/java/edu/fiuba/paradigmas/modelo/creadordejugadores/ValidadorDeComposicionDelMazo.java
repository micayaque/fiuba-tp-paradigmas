package edu.fiuba.paradigmas.modelo.creadordejugadores;

public class ValidadorDeComposicionDelMazo {

    private final ObservadorMazo observador;

    public ValidadorDeComposicionDelMazo(ObservadorMazo observador) {
        this.observador = observador;
    }

    private void notificarMesaLiberada() {
        observador.mesaLiberada();
    }

    private void notificarTopeGlobal() {
        observador.topeGlobalAlcanzado();
    }

    private void notificarTopeMafia() {
        observador.topeMafiaAlcanzado();
    }

    private void notificarTopeEspeciales() {
        observador.topeEspecialesAlcanzado();
    }

    public void evaluar(int cantidadJugadores, int ciudadanos, int mafiosos, int especialesCiudadanos, int padrinos) {
        int totalRoles = ciudadanos + mafiosos + especialesCiudadanos + padrinos;
        int totalBandoCiudadano = ciudadanos + especialesCiudadanos;
        int totalMafia = mafiosos + padrinos;
        int totalEspeciales = especialesCiudadanos + padrinos;

        notificarMesaLiberada();

        if (totalRoles >= cantidadJugadores || totalRoles >= 12) {
            notificarTopeGlobal();
            return;
        }

        int maxMafiaPorMazo = (totalRoles < 6) ? 2 : 3;
        int maxMafiaPorCiudadanos = Math.max(0, totalBandoCiudadano - 1);
        int limiteMafia = Math.min(maxMafiaPorMazo, maxMafiaPorCiudadanos);

        if (totalMafia >= limiteMafia) {
            notificarTopeMafia();
        }

        int maxEspeciales = (totalRoles < 6) ? 1 : ((totalRoles < 9) ? 2 : 4);

        if (totalEspeciales >= maxEspeciales) {
            notificarTopeEspeciales();
        }
    }
}