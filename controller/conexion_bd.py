import mysql.connector
from mysql.connector import Error

def obtener_conexion():
    try:
        conexion = mysql.connector.connect(
            host='localhost',
            user='root',          # Cambia el usuario según tu entorno de MySQL
            password='root',          # Cambia la contraseña según tu entorno de MySQL
            database='rpg_manager_db'
        )
        if conexion.is_connected():
            return conexion
    except Error as e:
        raise ConnectionError(f"No se pudo establecer conexión con MySQL: {e}")