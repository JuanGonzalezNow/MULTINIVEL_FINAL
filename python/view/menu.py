from controller import controlador


def ejecutar_interfaz():
    while True:
        print("\n====================================")
        print("          RPG MANAGER (MVC)         ")
        print("====================================")
        print("1. Crear nuevo personaje")
        print("2. Listar todos los personajes")
        print("3. Buscar personaje por nombre")
        print("4. Actualizar nivel de personaje")
        print("5. Eliminar personaje por ID")
        print("6. Salir")
        print("====================================")

        opcion = input("Seleccione una opcion (1-6): ").strip()

        try:
            if opcion == "1":
                nombre = input("Nombre del personaje: ").strip()
                if not nombre:
                    print("El nombre no puede estar vacio.")
                    continue

                clase = input("Clase (Guerrero / Mago / Arquero): ").strip().capitalize()
                if clase not in ["Guerrero", "Mago", "Arquero"]:
                    print("Clase no valida. Seleccion cancelada.")
                    continue

                nivel = int(input("Nivel inicial: "))
                controlador.crear_personaje(nombre, clase, nivel)
                print("Personaje creado e insertado en MySQL con exito.")

            elif opcion == "2":
                personajes = controlador.listar_personajes()
                if not personajes:
                    print("La base de datos se encuentra vacia.")
                else:
                    print("\n--- LISTADO DE PERSONAJES EN BD ---")
                    for p in personajes:
                        print(f"ID: {p.id} | Nombre: {p.nombre} | Clase: {type(p).__name__} | Nivel: {p.nivel}")
                        print(f" -> Accion: {p.habilidad_especial()}")

            elif opcion == "3":
                nombre = input("Ingrese el nombre exacto a buscar: ").strip()
                p = controlador.buscar_por_nombre(nombre)
                if p:
                    print(f"\n[Resultado] ID: {p.id} | Nombre: {p.nombre} | Clase: {type(p).__name__} | Nivel: {p.nivel}")
                    print(f" -> Accion: {p.habilidad_especial()}")
                else:
                    print("No se encontro ningun personaje con ese nombre.")

            elif opcion == "4":
                id_p = int(input("Ingrese el ID del personaje a modificar: "))
                nuevo_nivel = int(input("Ingrese el nuevo nivel: "))
                if controlador.actualizar_nivel(id_p, nuevo_nivel):
                    print("Registro actualizado correctamente en MySQL.")
                else:
                    print("Error: El ID proporcionado no existe en los registros.")

            elif opcion == "5":
                id_p = int(input("Ingrese el ID del personaje a eliminar: "))
                if controlador.eliminar_personaje(id_p):
                    print("Registro eliminado correctamente de la base de datos.")
                else:
                    print("Error: El ID proporcionado no existe en los registros.")

            elif opcion == "6":
                print("Cerrando el gestor RPG Manager. Exito en la sustentacion.")
                break
            else:
                print("Opcion invalida. Digite un numero entre 1 y 6.")

        except ValueError as ve:
            print(f"Error de entrada: Los campos numericos de nivel e ID deben ser enteros validos. ({ve})")
        except ConnectionError as ce:
            print(f"\n[ERROR DE PERSISTENCIA] {ce}")
        except Exception as e:
            print(f"\n[ERROR INESPERADO] {e}")


if __name__ == "__main__":
    ejecutar_interfaz()
