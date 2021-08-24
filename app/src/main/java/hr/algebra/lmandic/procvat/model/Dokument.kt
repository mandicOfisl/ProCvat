package hr.algebra.lmandic.procvat.model

data class Dokument(
    var _id: Int?,
    var vrstaDokumentaId: Int,
    var skladisteId: Int,
    var datumKreiranja: String,
    var ukupnaKolicina: Int,
    var napomena: String?,
    var statusId: Int,
    var partnerId: Int,
    var korisnikId: Int
)
