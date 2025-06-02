/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.fiuba.algo3.modelo.carta;


import edu.fiuba.algo3.modelo.Modificable.Modificable;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Palo.Palo;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;

import java.util.Objects;


public class Carta implements Modificable, Seleccionable {
    private Palo palo;
    private Puntaje puntaje;
    private int valor;


    public Carta(Palo palo, int valor, int puntajeBase, int multiplicador){
           if(valor < 2 || valor > 14){
               throw new ValorDeCartaInvalido();
           }
           this.valor = valor;
           this.palo = palo;
           this.puntaje = new Puntaje(puntajeBase,multiplicador);

    }


    public boolean esMayor(Carta otraCarta) {
        return otraCarta.valor > this.valor;
    }

    public Boolean sonMismoPalo(Carta otraCarta) {
        return otraCarta.compararPaloCon(this.palo);
    }

    public boolean compararPaloCon(Palo palo) {
        return this.palo.sonMismoPalo(palo);
    }

    public Puntaje obtenerPuntaje(){
        return this.puntaje;
    }

    public Boolean esAntecesor(Carta otraCarta) {
        return otraCarta.valor == this.valor - 1;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Carta otraCarta = (Carta) obj;
        return(Objects.equals(valor,otraCarta.valor));
    }

    @Override
    public int hashCode(){
        return Objects.hash(valor);
    }


    public void aplicarModificacion(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public Boolean validarModificable(Modificador modificador) {
        return modificador.validarTipo("carta");
    }


    public Puntaje calcularPuntaje() {
        return puntaje;
    }

    public String obtenerNombre(){
        String valorEnString = String.valueOf(valor);
        return valorEnString;
    }

    public Palo getPalo(){ return this.palo;}
}
