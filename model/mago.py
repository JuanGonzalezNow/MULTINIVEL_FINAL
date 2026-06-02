from model.personaje import Personaje

class Mago(Personaje):
    def habilidad_especial(self) -> str:
        return f"¡{self.nombre} lanza Piromancia! Inflige daño de fuego crítico en área."