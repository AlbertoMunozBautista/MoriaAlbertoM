class Carcaj(var flechas: Int) {

    //Método que resta una flecha del carcaj
    fun lanzarFlechas() {
        flechas--
    }

    //Método que suma las flechas que recibe al carcaj
    fun recargarFlechas(flechasRecargar: Int) {
        flechas = flechas + flechasRecargar
    }

}