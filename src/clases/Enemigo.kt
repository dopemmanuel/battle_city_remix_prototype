package com.dam1.projectlibre.src.clases

import com.dam1.projectlibre.src.esPosicionValida

class Enemigo(var posicion: Posicion) {
    fun mover(mapa: Array<CharArray>): Boolean {
        val direccion = Direccion.entries.toTypedArray().random() // Usar el enum Direccion
        val nuevaPosicion = when (direccion) {
            Direccion.ARRIBA -> Posicion(posicion.x, posicion.y - 1)
            Direccion.ABAJO -> Posicion(posicion.x, posicion.y + 1)
            Direccion.IZQUIERDA -> Posicion(posicion.x - 1, posicion.y)
            Direccion.DERECHA -> Posicion(posicion.x + 1, posicion.y)
        }

        if (esPosicionValida(nuevaPosicion, mapa)) {
            posicion = nuevaPosicion
            return true
        }
        return false
    }
}