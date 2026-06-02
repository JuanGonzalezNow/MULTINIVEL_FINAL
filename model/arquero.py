from model.personaje import Personaje

class Arquero(Personaje):
    def habilidad_especial(self) -> str:
        return f"¡{self.nombre} ejecuta Disparo de Puntería! Ignora la armadura del objetivo."