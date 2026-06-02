from controller.conexion_bd import obtener_conexion
from model.guerrero import Guerrero
from model.mago import Mago
from model.arquero import Arquero

def instanciar_personaje(row):
   
    id_p, nombre, clase, nivel, _ = row
    if clase == "Guerrero":
        return Guerrero(nombre, nivel, id_p)
    elif clase == "Mago":
        return Mago(nombre, nivel, id_p)
    elif clase == "Arquero":
        return Arquero(nombre, nivel, id_p)
    return None

def crear_personaje(nombre, clase, nivel):
    conn = obtener_conexion()
    cursor = conn.cursor()
    query = "INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES (%s, %s, %s, NOW())"
    cursor.execute(query, (nombre, clase, nivel))
    conn.commit()
    cursor.close()
    conn.close()

def listar_personajes():
    conn = obtener_conexion()
    cursor = conn.cursor()
    cursor.execute("SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes")
    rows = cursor.fetchall()
    personajes = [instanciar_personaje(row) for row in rows]
    cursor.close()
    conn.close()
    return personajes

def buscar_por_nombre(nombre):
    conn = obtener_conexion()
    cursor = conn.cursor()
    query = "SELECT id, nombre, clase, nivel, fecha_creacion FROM personajes WHERE nombre = %s"
    cursor.execute(query, (nombre,))
    row = cursor.fetchone()
    cursor.close()
    conn.close()
    if row:
        return instanciar_personaje(row)
    return None

def actualizar_nivel(id_personaje, nuevo_nivel):
    conn = obtener_conexion()
    cursor = conn.cursor()
    
    # Validación de ID inexistente
    cursor.execute("SELECT id FROM personajes WHERE id = %s", (id_personaje,))
    if not cursor.fetchone():
        cursor.close()
        conn.close()
        return False
        
    query = "UPDATE personajes SET nivel = %s WHERE id = %s"
    cursor.execute(query, (nuevo_nivel, id_personaje))
    conn.commit()
    cursor.close()
    conn.close()
    return True

def eliminar_personaje(id_personaje):
    conn = obtener_conexion()
    cursor = conn.cursor()
    
    # Validación de ID inexistente
    cursor.execute("SELECT id FROM personajes WHERE id = %s", (id_personaje,))
    if not cursor.fetchone():
        cursor.close()
        conn.close()
        return False
        
    query = "DELETE FROM personajes WHERE id = %s"
    cursor.execute(query, (id_personaje,))
    conn.commit()
    cursor.close()
    conn.close()
    return True