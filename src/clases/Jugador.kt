package com.dam1.projectlibre.src.clases

import com.dam1.projectlibre.src.esPosicionValida

class Jugador(var posicion: Posicion, var velocidad: Int = 1) {

    fun mover(direccion: Direccion, mapa: Array<CharArray>): Boolean {
        val nuevaPosicion = when (direccion) {
            Direccion.ARRIBA -> Posicion(posicion.x, posicion.y - velocidad)
            Direccion.ABAJO -> Posicion(posicion.x, posicion.y + velocidad)
            Direccion.IZQUIERDA -> Posicion(posicion.x - velocidad, posicion.y)
            Direccion.DERECHA -> Posicion(posicion.x + velocidad, posicion.y)
        }

        // Verificar si la nueva posición es válida
        if (esPosicionValida(nuevaPosicion, mapa)) {
            posicion = nuevaPosicion
            return true
        }
        return false
    }

    fun disparar(): Bala {
        return Bala(posicion, Direccion.ARRIBA) // Dirección inicial de la bala
    }
}