package edu.fiuba.paradigmas.modelo.creadordejugadores;

public interface ObservadorMazo {
    void mesaLiberada();
    void topeGlobalAlcanzado();
    void topeMafiaAlcanzado();
    void topeEspecialesAlcanzado();
}