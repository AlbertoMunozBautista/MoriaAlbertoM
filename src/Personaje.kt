class Personaje {
    //Las variables de las clases tienen que llevar obligatoriamente una ?
    //eso significa que aceptan valores nulos.

    var nombre: String
    var isEstado: Boolean
    var raza: String
    var anillo: Anillo? = null
    var carcaj: Carcaj? = null
    var vara: Vara? = null

    constructor(nombre: String, estado: Boolean, raza: String, anillo: Anillo) {
        this.nombre = nombre
        isEstado = estado
        this.raza = raza
        this.anillo = anillo
    }

    constructor(nombre: String, estado: Boolean, raza: String, carcaj: Carcaj?) {
        this.nombre = nombre
        isEstado = estado
        this.raza = raza
        this.carcaj = carcaj
    }

    constructor(nombre: String, estado: Boolean, raza: String, vara: Vara?) {
        this.nombre = nombre
        isEstado = estado
        this.raza = raza
        this.vara = vara
    }

}