from controller.conexion_bd import obtener_conexion
from model.guerrero import Guerrero
from model.mago import Mago
from model.arquero import Arquero
from mysql.connector import Error  # Importante: Importamos Error para capturar fallos de MySQL

def instanciar_personaje(row):
    id_p, nombre, clase, nivel, _ = row

    if clase == "Guerrero":
        return Guerrero(nombre, nivel, id_p)
    if clase == "Mago":
        return Mago(nombre, nivel, id_p)
    if clase == "Arquero":
        return Arquero(nombre, nivel, id_p)

    return None

def normalizar_clase(clase):
    if clase.lower() == "guerrero":
        return "Guerrero"
    if clase.lower() == "mago":
        return "Mago"
    if clase.lower() == "arquero":
        return "Arquero"
    return clase

def crear_personaje(nombre, clase, nivel):
    conn = None
    cursor = None
    try:
        conn = obtener_conexion()
        cursor = conn.cursor()
        query = "INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES (%s, %s, %s, NOW())"
        cursor.execute(query, (nombre, normalizar_clase(clase), nivel))
        conn.commit()  # Confirma los cambios de manera segura
        return True
    except Error as e:
        if conn:
            conn.rollback()  # Si algo falla, deshace cualquier cambio pendiente
        raise RuntimeError(f"Error de MySQL al crear personaje: {e}")
    finally:
        # El bloque finally asegura que la conexión se cierre SIEMPRE
        if cursor:
            cursor.close()
        if conn:
            conn.close()

def listar_personajes():
    conn = None
    cursor = None
    try:
        conn = obtener_conexion()
        cursor = conn.cursor()
        cursor.execute("SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes ORDER BY id")
        rows = cursor.fetchall()
        personajes = [personaje for personaje in (instanciar_personaje(row) for row in rows) if personaje]
        return personajes
    except Error as e:
        raise RuntimeError(f"Error de MySQL al listar personajes: {e}")
    finally:
        if cursor:
            cursor.close()
        if conn:
            conn.close()

def buscar_por_nombre(nombre):
    conn = None
    cursor = None
    try:
        conn = obtener_conexion()
        cursor = conn.cursor()
        query = "SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes WHERE nombre = %s"
        cursor.execute(query, (nombre,))
        row = cursor.fetchone()
        if row:
            return instanciar_personaje(row)
        return None
    except Error as e:
        raise RuntimeError(f"Error de MySQL al buscar personaje: {e}")
    finally:
        if cursor:
            cursor.close()
        if conn:
            conn.close()

def actualizar_nivel(id_personaje, nuevo_nivel):
    conn = None
    cursor = None
    try:
        conn = obtener_conexion()
        cursor = conn.cursor()
        query = "UPDATE personajes SET nivel = %s WHERE id = %s"
        cursor.execute(query, (nuevo_nivel, id_personaje))
        conn.commit()
        return cursor.rowcount > 0
    except Error as e:
        if conn:
            conn.rollback()
        raise RuntimeError(f"Error de MySQL al actualizar nivel: {e}")
    finally:
        if cursor:
            cursor.close()
        if conn:
            conn.close()

def eliminar_personaje(id_personaje):
    conn = None
    cursor = None
    try:
        conn = obtener_conexion()
        cursor = conn.cursor()
        query = "DELETE FROM personajes WHERE id = %s"
        cursor.execute(query, (id_personaje,))
        conn.commit()
        return cursor.rowcount > 0
    except Error as e:
        if conn:
            conn.rollback()
        raise RuntimeError(f"Error de MySQL al eliminar personaje: {e}")
    finally:
        if cursor:
            cursor.close()
        if conn:
            conn.close()