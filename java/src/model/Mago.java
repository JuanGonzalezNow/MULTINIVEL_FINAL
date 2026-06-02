package model;

public class Mago extends Personaje {
    public Mago(int id, String nombre, int nivel) {
        super(id, nombre, "Mago", nivel);
    }

    @Override
    public String habilidadEspecial() {
        return "Bola de fuego";
    }
}
