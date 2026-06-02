package view;

import controller.Controlador;
import java.util.List;
import java.util.Scanner;
import model.Personaje;

public class Menu {
    private final Controlador controlador = new Controlador();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;

        do {
            mostrarOpciones();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    crearPersonaje();
                    break;
                case 2:
                    listarPersonajes();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    actualizarNivel();
                    break;
                case 5:
                    eliminarPersonaje();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println();
        System.out.println("=== RPG Manager ===");
        System.out.println("1. Crear personaje");
        System.out.println("2. Listar personajes");
        System.out.println("3. Buscar por nombre");
        System.out.println("4. Actualizar nivel");
        System.out.println("5. Eliminar personaje");
        System.out.println("0. Salir");
    }

    private void crearPersonaje() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Clase (Guerrero/Mago/Arquero): ");
        String clase = scanner.nextLine();

        int nivel = leerEntero("Nivel: ");

        controlador.crearPersonaje(nombre, clase, nivel);
        System.out.println("Personaje creado.");
    }

    private void listarPersonajes() {
        List<Personaje> personajes = controlador.listarPersonajes();

        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }

        for (Personaje personaje : personajes) {
            System.out.println(personaje);
        }
    }

    private void buscarPorNombre() {
        System.out.print("Nombre a buscar: ");
        String nombre = scanner.nextLine();

        Personaje personaje = controlador.buscarPorNombre(nombre);
        if (personaje == null) {
            System.out.println("No se encontro el personaje.");
        } else {
            System.out.println(personaje);
        }
    }

    private void actualizarNivel() {
        int id = leerEntero("ID del personaje: ");
        int nivel = leerEntero("Nuevo nivel: ");

        if (controlador.actualizarNivel(id, nivel)) {
            System.out.println("Nivel actualizado.");
        } else {
            System.out.println("No existe un personaje con ese ID.");
        }
    }

    private void eliminarPersonaje() {
        int id = leerEntero("ID del personaje: ");

        if (controlador.eliminarPersonaje(id)) {
            System.out.println("Personaje eliminado.");
        } else {
            System.out.println("No existe un personaje con ese ID.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
