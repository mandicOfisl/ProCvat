package hr.algebra.lmandic.procvat.model

data class Dokument(
    var id: Int?,
    var vrstaDokumentaId: Int,
    var skladisteId: Int,
    var datumKreiranja: String,
    var ukupnaKolicina: Int,
    var napomena: String?,
    var statusId: Int,
    var partnetId: Int,
    var korisnikId: Int
)
