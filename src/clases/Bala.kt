package com.dam1.projectlibre.src.clases

import com.dam1.projectlibre.src.BLOQUE_DESTRUCTIBLE
import com.dam1.projectlibre.src.VACIO
import com.dam1.projectlibre.src.esPosicionValida

class Bala(var posicion: Posicion, var direccion: Direccion) {
    fun mover(mapa: Array<CharArray>): Boolean {
        val nuevaPosicion = when (direccion) {
            Direccion.ARRIBA -> Posicion(posicion.x, posicion.y - 1)
            Direccion.ABAJO -> Posicion(posicion.x, posicion.y + 1)
            Direccion.IZQUIERDA -> Posicion(posicion.x - 1, posicion.y)
            Direccion.DERECHA -> Posicion(posicion.x + 1, posicion.y)
        }

        // Verificar si la bala choca con algo
        if (!esPosicionValida(nuevaPosicion, mapa)) {
            return false
        }

        // Destruir bloques destructibles
        if (mapa[nuevaPosicion.y][nuevaPosicion.x] == BLOQUE_DESTRUCTIBLE) {
            mapa[nuevaPosicion.y][nuevaPosicion.x] = VACIO
            return false
        }

        posicion = nuevaPosicion
        return true
    }
}
