# RPG Manager Java

Estructura inicial MVC para Java con Ant.

```text
src/
├─ rpgmanager/
│  └─ Main.java
├─ model/
│  ├─ Personaje.java
│  ├─ Guerrero.java
│  ├─ Mago.java
│  └─ Arquero.java
├─ controller/
│  └─ Controlador.java
└─ view/
   └─ Menu.java
```

Por ahora el controlador usa una lista en memoria. El siguiente paso es agregar `ConexionBD.java` y cambiar el controlador para usar MySQL.
