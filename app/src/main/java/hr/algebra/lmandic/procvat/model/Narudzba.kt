package hr.algebra.lmandic.procvat.model

data class Narudzba(
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var datumNarudzbe: String,
    var kolicina: Int?,
    var statusId: Int,
    var korisnikId: Int
)
