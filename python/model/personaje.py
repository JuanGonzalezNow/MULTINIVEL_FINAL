from abc import ABC, abstractmethod

class Personaje(ABC):
    def __init__(self, nombre: str, nivel: int = 1, id_personaje: int = None):
        self._id = id_personaje
        self._nombre = nombre
        self._nivel = nivel

    @property
    def id(self):
        return self._id

    @property
    def nombre(self):
        return self._nombre

    @property
    def nivel(self):
        return self._nivel

    @nivel.setter
    def nivel(self, nuevo_nivel: int):
        if nuevo_nivel > 0:
            self._nivel = nuevo_nivel
        else:
            raise ValueError("El nivel debe ser un número entero mayor a 0.")

    @abstractmethod
    def habilidad_especial(self) -> str:
        """Método abstracto para asegurar el polimorfismo en las clases hijas."""
        pass