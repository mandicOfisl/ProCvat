package hr.algebra.lmandic.procvat.model

data class StanjeSkladista(
    var _id: Int?,
    var artiklId: Int,
    var skladisteId: Int,
    var klasa: Int,
    var kolicina: Int,
)
