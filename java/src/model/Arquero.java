package model;

public class Arquero extends Personaje {
    public Arquero(int id, String nombre, int nivel) {
        super(id, nombre, "Arquero", nivel);
    }

    @Override
    public String habilidadEspecial() {
        return "Flecha precisa";
    }
}
