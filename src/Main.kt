package com.dam1.projectlibre.src



import kotlin.random.Random
import com.dam1.projectlibre.src.clases.Direccion
import com.dam1.projectlibre.src.clases.Posicion
import com.dam1.projectlibre.src.clases.Bala
import com.dam1.projectlibre.src.clases.Jugador
import com.dam1.projectlibre.src.clases.Enemigo

// Constantes del juego
const val ANCHO = 10
const val ALTO = 10
const val BLOQUE_DESTRUCTIBLE = '#'
const val BLOQUE_NO_DESTRUCTIBLE = 'X'
const val JUGADOR = 'P'
const val ENEMIGO = 'E'
const val BALA = '*'
const val VACIO = ' '


fun esPosicionValida(posicion: Posicion, mapa: Array<CharArray>): Boolean {
    return posicion.x in 0 until ANCHO &&
            posicion.y in 0 until ALTO &&
            mapa[posicion.y][posicion.x] != BLOQUE_NO_DESTRUCTIBLE
}


fun imprimirMapa(mapa: Array<CharArray>, jugador: Jugador, enemigos: List<Enemigo>, balas: List<Bala>) {
    val mapaTemp = mapa.map { it.clone() }.toTypedArray()

    // Dibujar jugador
    mapaTemp[jugador.posicion.y][jugador.posicion.x] = JUGADOR

    // Dibujar enemigos
    for (enemigo in enemigos) {
        mapaTemp[enemigo.posicion.y][enemigo.posicion.x] = ENEMIGO
    }

    // Dibujar balas
    for (bala in balas) {
        mapaTemp[bala.posicion.y][bala.posicion.x] = BALA
    }

    // Imprimir mapa
    for (fila in mapaTemp) {
        println(fila.joinToString(" "))
    }
}

// Función principal
fun main() {
    // Inicializar mapa
    val mapa = Array(ALTO) { CharArray(ANCHO) { VACIO } }
    for (i in 0 until ANCHO) {
        mapa[0][i] = BLOQUE_NO_DESTRUCTIBLE
        mapa[ALTO - 1][i] = BLOQUE_NO_DESTRUCTIBLE
    }
    for (i in 0 until ALTO) {
        mapa[i][0] = BLOQUE_NO_DESTRUCTIBLE
        mapa[i][ANCHO - 1] = BLOQUE_NO_DESTRUCTIBLE
    }

    // Colocar algunos bloques destructibles
    for (i in 1 until ALTO - 1) {
        for (j in 1 until ANCHO - 1) {
            if (Random.nextBoolean()) {
                mapa[i][j] = BLOQUE_DESTRUCTIBLE
            }
        }
    }

    // Inicializar jugador, enemigos y balas
    val jugador = Jugador(Posicion(1, 1))
    val enemigos = mutableListOf(Enemigo(Posicion(5, 5)), Enemigo(Posicion(8, 8)))
    var balas = mutableListOf<Bala>()

    // Bucle principal del juego
    var juegoActivo = true
    while (juegoActivo) {
        imprimirMapa(mapa, jugador, enemigos, balas)

        // Leer entrada del usuario
        print("Dirección (W: Arriba, S: Abajo, A: Izquierda, D: Derecha, Disparar: Espacio): ")
        val input = readLine()?.uppercase()

        when (input) {
            "W" -> jugador.mover(Direccion.ARRIBA, mapa)
            "S" -> jugador.mover(Direccion.ABAJO, mapa)
            "A" -> jugador.mover(Direccion.IZQUIERDA, mapa)
            "D" -> jugador.mover(Direccion.DERECHA, mapa)
            " " -> balas.add(jugador.disparar())
        }

        // Mover enemigos
        for (enemigo in enemigos) {
            enemigo.mover(mapa)
        }

        // Mover balas
        balas = balas.filter { it.mover(mapa) }.toMutableList()

        // Verificar colisiones (esto es muy básico)
        balas.forEach { bala ->
            enemigos.removeIf { enemigo -> bala.posicion == enemigo.posicion }
        }

        // Condición de salida (por ahora, solo salir manualmente)
        if (input == "Q") {
            juegoActivo = false
        }
    }
}