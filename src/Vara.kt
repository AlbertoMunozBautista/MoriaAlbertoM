class Vara(var energia: Int) {

    //Método que dada la energía recibida recarga la vara
    fun recargarVara(energiaRecargar: Int) {
        energia = energiaRecargar
    }

    //Método que devuelve la energía que tiene ahora mismo la vara
    fun poderVara(): Int {
        return energia
    }

}