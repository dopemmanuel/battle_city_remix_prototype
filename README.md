# Battle City Remix

## Introducción
Este proyecto estoy recreando el clásico juego **Battle City** utilizando Kotlin. El objetivo es inspirarme y usar lo que conozco de programación para rehacer un version mejorada del juego
también demostrar los conceptos de programación orientada a objetos (POO) y el uso de librerías externas.

## Estructura del Proyecto
- **Clases principales**:
    - `Jugador`: Esta clase representa al jugador.
    - `Enemigo`: Esta clase representa a los enemigos.
    - `Bala`:Esta clase representa los proyectiles.
    - `Direccion`: La enum Class que contiene las direcciones.
    - `Posicion`: La data class que contiene las posiciones.

## Instalación
1. Clona el repositorio.
2. Abre el proyecto en IntelliJ IDEA.
3. Ejecuta la clase `Main`.

## Respuestas a las preguntas planteadas
### Criterio Global 1: Instancia objetos y hacer uso de ellos
En el proyecto se instancian objetos como `Jugador` y `Enemigo`. Por ejemplo:
```kotlin
val jugador = Jugador(Posicion(1, 1))