package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.Arquero;
import model.Guerrero;
import model.Mago;
import model.Personaje;

public class Controlador {
    public boolean crearPersonaje(String nombre, String clase, int nivel) {
        String sql = "INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES (?, ?, ?, NOW())";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, normalizarClase(clase));
            stmt.setInt(3, nivel);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear personaje en MySQL: " + e.getMessage());
            return false;
        }
    }

    public List<Personaje> listarPersonajes() {
        List<Personaje> personajes = new ArrayList<Personaje>();
        String sql = "SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes ORDER BY id";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personajes.add(instanciarPersonaje(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar personajes desde MySQL: " + e.getMessage());
        }

        return personajes;
    }

    public Personaje buscarPorNombre(String nombre) {
        String sql = "SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes WHERE nombre = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return instanciarPersonaje(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar personaje en MySQL: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarNivel(int idPersonaje, int nuevoNivel) {
        String sql = "UPDATE personajes SET nivel = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nuevoNivel);
            stmt.setInt(2, idPersonaje);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar personaje en MySQL: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersonaje(int idPersonaje) {
        String sql = "DELETE FROM personajes WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersonaje);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar personaje en MySQL: " + e.getMessage());
            return false;
        }
    }

    private Personaje instanciarPersonaje(ResultSet rs) throws SQLException {
        return instanciarPersonaje(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("clase"),
                rs.getInt("nivel")
        );
    }

    private Personaje instanciarPersonaje(int id, String nombre, String clase, int nivel) {
        String claseNormalizada = normalizarClase(clase);

        if ("Guerrero".equals(claseNormalizada)) {
            return new Guerrero(id, nombre, nivel);
        }

        if ("Mago".equals(claseNormalizada)) {
            return new Mago(id, nombre, nivel);
        }

        if ("Arquero".equals(claseNormalizada)) {
            return new Arquero(id, nombre, nivel);
        }

        return new Personaje(id, nombre, clase, nivel);
    }

    private String normalizarClase(String clase) {
        if ("guerrero".equalsIgnoreCase(clase)) {
            return "Guerrero";
        }

        if ("mago".equalsIgnoreCase(clase)) {
            return "Mago";
        }

        if ("arquero".equalsIgnoreCase(clase)) {
            return "Arquero";
        }

        return clase;
    }
}
