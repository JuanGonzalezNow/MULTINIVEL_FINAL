package model;

public class Personaje {
    private int id;
    private String nombre;
    private String clase;
    private int nivel;

    public Personaje(int id, String nombre, String clase, int nivel) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String habilidadEspecial() {
        return "Habilidad basica";
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Clase: " + clase
                + " | Nivel: " + nivel
                + " | Habilidad: " + habilidadEspecial();
    }
}
