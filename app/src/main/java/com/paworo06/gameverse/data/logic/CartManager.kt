package com.paworo06.gameverse.data.logic

import com.paworo06.gameverse.data.model.CartItem
import com.paworo06.gameverse.data.model.Game

class CartManager {
    private val cartItems = mutableListOf<CartItem>()

    /**
     * Añade un juego al carrito o incrementa su cantidad si ya existe
     * @param game Objeto Game a añadir
     * @param quanty Int con la cantidad a añadir
     */
    fun addGame(game: Game, quanty: Int) {
        // Buscamos si el juego ya está en el carrito
        val existe = cartItems.find {
            it.game.id == game.id
        }

        if (existe != null) {
            // Si existe, incrementamos la cantidad
            // Obtenemos el indice del juego
            val index = cartItems.indexOf(existe)
            cartItems[index] = existe.copy(quanty = existe.quanty + quanty)
        } else {
            // Si no existe, añadimos el juego y la cantidad a la lista
            cartItems.add(CartItem(game, quanty))
        }
    }

    /**
     * Elimina un juego del carrito por su ID
     * @param gameId Int con el id del juego
     */
    fun removeGame(gameId: Int) {
        cartItems.removeAll {
            it.game.id == gameId
        }
    }

    /**
     * Actualiza la cantidad de un juego en el carrito
     * Si la cantidad es 0 o menor, elimina el item
     * @param gameId Int con el id del juego
     * @param newQuantity Int con la nueva cantidad del item deseado
     */
    fun updateQuantity(gameId: Int, newQuantity: Int) {
        // Si la cantidad es 0, borramos el item
        if (newQuantity <= 0) {
            removeGame(gameId)
            return
        }

        // Obtenemos el item de la lista
        val item = cartItems.find {
            it.game.id == gameId
        }
        if (item != null) {
            // Si obtenemos un item (existe) actualizamos la cantidad
            val index = cartItems.indexOf(item)
            cartItems[index] = item.copy(quanty = newQuantity)
        }
    }

    /**
     * Vacía completamente el carrito
     */
    fun clearCart() {
        cartItems.clear()
    }

    /**
     * Devuelve una copia de los items actuales del carrito
     * @return List<CartItem> con todos los items del carrito
     */
    fun getCartItems(): List<CartItem> {
        return cartItems
    }

    /**
     * Calcula el precio total del carrito
     * @return Double con el precio total
     */
    fun getTotal(): Double {
        return cartItems.sumOf {
            it.game.price * it.quanty
        }
    }

    /**
     * Cuenta el número total de items en el carrito
     * @return Int con el numero total de items
     */
    fun getTotalItems(): Int {
        return cartItems.sumOf {
            it.quanty
        }
    }

    /**
     * Verifica si el carrito está vacío
     * @return Boolean true si está vacío
     */
    fun isEmpty(): Boolean {
        return cartItems.isEmpty()
    }

    /**
     * Obtiene un item específico del carrito por ID de juego
     * @return CartItem que contiene el Game y la Cantidad del mismo
     */
    fun getCartItem(gameId: Int): CartItem? {
        for (item in cartItems) {
            if (item.game.id == gameId) {
                return item
            }
        }
        return null
    }
}