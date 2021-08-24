package hr.algebra.lmandic.procvat.model

data class Prodaja(
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var skladisteId: Int,
    var vrstaDokumentaId: Int,
    var kolicina: Int,
    var statusId: Int,
    var korisnikId: Int
)
