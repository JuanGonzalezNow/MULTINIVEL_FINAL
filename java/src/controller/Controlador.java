package controller;

import java.util.ArrayList;
import java.util.List;
import model.Arquero;
import model.Guerrero;
import model.Mago;
import model.Personaje;

public class Controlador {
    private final List<Personaje> personajes = new ArrayList<Personaje>();
    private int siguienteId = 1;

    public Controlador() {
        crearPersonaje("Arthas", "Guerrero", 80);
        crearPersonaje("Jaina", "Mago", 85);
        crearPersonaje("Sylvanas", "Arquero", 80);
    }

    public void crearPersonaje(String nombre, String clase, int nivel) {
        personajes.add(instanciarPersonaje(siguienteId, nombre, clase, nivel));
        siguienteId++;
    }

    public List<Personaje> listarPersonajes() {
        return personajes;
    }

    public Personaje buscarPorNombre(String nombre) {
        for (Personaje personaje : personajes) {
            if (personaje.getNombre().equalsIgnoreCase(nombre)) {
                return personaje;
            }
        }

        return null;
    }

    public boolean actualizarNivel(int idPersonaje, int nuevoNivel) {
        for (Personaje personaje : personajes) {
            if (personaje.getId() == idPersonaje) {
                personaje.setNivel(nuevoNivel);
                return true;
            }
        }

        return false;
    }

    public boolean eliminarPersonaje(int idPersonaje) {
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i).getId() == idPersonaje) {
                personajes.remove(i);
                return true;
            }
        }

        return false;
    }

    private Personaje instanciarPersonaje(int id, String nombre, String clase, int nivel) {
        if ("Guerrero".equalsIgnoreCase(clase)) {
            return new Guerrero(id, nombre, nivel);
        }

        if ("Mago".equalsIgnoreCase(clase)) {
            return new Mago(id, nombre, nivel);
        }

        if ("Arquero".equalsIgnoreCase(clase)) {
            return new Arquero(id, nombre, nivel);
        }

        return new Personaje(id, nombre, clase, nivel);
    }
}
