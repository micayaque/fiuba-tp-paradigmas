package edu.fiuba.algo3.controllers.Parseados;

public class ComodinParseado {
        private String nombre;
        private String descripcion;
        private Object  activacion;
        private EfectoParseado efecto;

        // Getters y setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Object  getActivacion() {
            return activacion;
        }

        public void setActivacion(Object  activacion) {
            this.activacion = activacion;
        }

        public EfectoParseado getEfecto() {
            return efecto;
        }

        public void setEfecto(EfectoParseado efecto) {
            this.efecto = efecto;
        }


}
