from model.personaje import Personaje

class Guerrero(Personaje):
    def habilidad_especial(self) -> str:
        return f"¡{self.nombre} activa Embate con Escudo! Mitiga el daño físico entrante."