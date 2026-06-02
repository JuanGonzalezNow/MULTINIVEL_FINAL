package model;

public class Guerrero extends Personaje {
    public Guerrero(int id, String nombre, int nivel) {
        super(id, nombre, "Guerrero", nivel);
    }

    @Override
    public String habilidadEspecial() {
        return "Golpe poderoso";
    }
}
