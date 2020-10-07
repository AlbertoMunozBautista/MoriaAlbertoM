object Main {
    @JvmStatic
    // Las armas llevan !! para que salte la excepción kotlinNullPointerException si están a nulas

    fun main(args: Array<String>) {
        //Declaración de variables
        var tipoSala: Int
        var muerte = false

        //Inicialización de las armas
        val anillo = Anillo(false)
        val carcaj = Carcaj(aleatorio(1, 20))
        val vara = Vara(aleatorio(1, 30))

        //Inicialización de los personajes
        val gandalf = Personaje("Gandalf", true, "Mago", vara)
        val legolas = Personaje("Legolas", true, "Elfo", carcaj)
        val frodo = Personaje("Frodo", true, "Hobbit", anillo)


        //Bucle que acaba o bien cuando pasas las 36 salas de moria o bien cuando mueres en alguna de ella
        var i = 1
        do {

            //Se elige aleatoriamente a que sala de moria entras
            tipoSala = aleatorio(0, 2)
            when (tipoSala) {
                0 -> {
                    //Inicialización de sala mágica y llamada al método correspondiente
                    val salaMagica = SalaMagica(i, tipoSala, aleatorio(1, 10))
                    muerte = salaMagica(salaMagica, gandalf)
                }
                1 -> {
                    //Inicialización de sala acción y llamada al método correspondiente
                    val salaAccion = SalaAccion(i, tipoSala, aleatorio(1, 10), aleatorio(1, 10))
                    muerte = salaAccion(salaAccion, legolas)
                }
                2 -> {
                    //Inicialización de sala habilidad y llamada al método correspondiente
                    val salaHabilidad = Sala(i, tipoSala)
                    muerte = salaHabilidad(salaHabilidad, frodo)
                }
            }
            i++
        } while (i < 37 && muerte == false)

        if(muerte == true ){
            println("Has muerto")
        } else {
            println("Has superado Moria!!")
        }
    }

    //Método que te devuelve un aleatorio entre los dos números que le pases
    private fun aleatorio(a: Int, b: Int): Int {
        return Math.floor(Math.random() * (b - a + 1) + a).toInt()
    }

    //Sala habilidad, hay un 50% de que frodo se ponga el anillo, si se lo pone, superan la sala
    //el 90% de las veces, si no se lo pone superan la sala sólo el 20% de las veces, si no superan la sala
    //habrá un 80% de posibilidades de que huyan.
    private fun salaHabilidad(salaHabilidad: Sala, frodo: Personaje): Boolean {
        var muerte = false
        val peligro = aleatorio(0, 9)
        println("Sala nº: " + salaHabilidad.getnSala())
        println("Es una sala de tipo: " + salaHabilidad.getPeligro())
        println(frodo.nombre + " se hará cargo de la sala!!")
        val poneAnillo = aleatorio(0, 1)
        if (poneAnillo == 0) {
            println("Frodo se ha puesto el anillo.")
            frodo.anillo!!.ponerseAnillo()
        } else {
            println("Frodo se ha quitado el anillo.")
            frodo.anillo!!.quitarseAnillo()
        }
        if (frodo.anillo!!.puesto == true) {
            if (peligro <= 8) {
                println("Enhorabuena, has superado la sala. ")
            } else {
                muerte = noSuperaSala()
            }
        } else {
            if (peligro <= 1) {
                println("Enhorabuena, has superado la sala. ")
            } else {
                muerte = noSuperaSala()
            }
        }
        return muerte
    }

    //Sala Acción, légolas lanzará flechas hasta intentar eliminar a todos los enemigos, si los elimina
    //superan la sala, si se queda sin flechas, intentarán huir con un 80% de éxito
    private fun salaAccion(salaAccion: SalaAccion, legolas: Personaje): Boolean {
        var muerte = false
        println("Sala nº: " + salaAccion.getnSala())
        println("Es una sala de tipo: " + salaAccion.getPeligro())
        println(legolas.nombre + " se hará cargo de la sala!!")
        for (i in 0 until legolas.carcaj!!.flechas) {
            legolas.carcaj!!.lanzarFlechas()
            salaAccion.enemigos = salaAccion.enemigos - 1
        }
        if (salaAccion.enemigos == 0) {
            println("Te has quedado sin flechas")
            muerte = noSuperaSala()
        } else {
            println("Enhorabuena, has superado la sala. ")
            println("En la sala había " + salaAccion.flechas + " flechas que " + legolas.nombre + " se ga guardado en el carcaj.")
            legolas.carcaj!!.recargarFlechas(salaAccion.flechas)
        }
        return muerte
    }

    //Sala mágica, si el poder de la vara de Gandalf es mayor que la energía maligna de la sala
    //siempre se supera la sala, si es igual, se supera la sala el 60% de las veces y si es menor
    //el 20% de las veces, si no son capaces de superar la sala, intentarán huir con un 80% de éxito
    private fun salaMagica(salaMagica: SalaMagica, gandalf: Personaje): Boolean {
        var muerte = false
        val recargarVara = aleatorio(0, 9)
        println("Sala nº: " + salaMagica.getnSala())
        println("Es una sala de tipo: " + salaMagica.getPeligro())
        println(gandalf.nombre + " se hará cargo de la sala!!")
        gandalf.vara!!.recargarVara(recargarVara)
        println("Se está recargando la vara ")
        println("Ahora la vara tiene $recargarVara de poder!!!")
        if (gandalf.vara!!.poderVara() > salaMagica.peligroMagico) {
            println("Enhorabuena, has superado la sala. ")
        } else if (gandalf.vara!!.poderVara() == salaMagica.peligroMagico) {
            if (aleatorio(0, 9) < 6) {
                println("Enhorabuena, has superado la sala. ")
            } else {
                println("La vara no tenía suficiente energía")
                muerte = noSuperaSala()
            }
        } else {
            if (aleatorio(0, 9) < 3) {
                println("Enhorabuena, has superado la sala. ")
            } else {
                println("La vara no tenía suficiente energía")
                muerte = noSuperaSala()
            }
        }
        return muerte
    }

    //Método que se llama cuando no se supera la sala, hay un 80% de que huyan de ella
    private fun noSuperaSala(): Boolean {
        val superar: Int
        println("No ha superado la sala.")
        superar = aleatorio(0, 9)
        return if (superar <= 7) {
            println("Pero has conseguido huir a tiempo.")
            false
        } else {
            println("Y tampoco has conseguido huir a tiempo")
            true
        }
    }
}