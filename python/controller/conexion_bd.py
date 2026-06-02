import mysql.connector
from mysql.connector import Error


def obtener_conexion():
    try:
        return mysql.connector.connect(
            host="localhost",
            user="root",
            password="root",
            database="rpg_manager_db"
        )
    except Error as e:
        raise ConnectionError(f"No se pudo establecer conexion con MySQL: {e}")
