open class Sala(private var nSala: Int, private var peligro: Int) {
    private val tipoPeligro = arrayOf("Mágica", "Acción", "Habilidad")
    fun getnSala(): Int {
        return nSala
    }

    fun setnSala(nSala: Int) {
        this.nSala = nSala
    }

    fun getPeligro(): String {
        return if (peligro == 0) {
            tipoPeligro[0]
        } else if (peligro == 1) {
            tipoPeligro[1]
        } else {
            tipoPeligro[2]
        }
    }

    fun setPeligro(peligro: Int) {
        this.peligro = peligro
    }

}