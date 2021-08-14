package hr.algebra.lmandic.procvat.model

data class UnosStanjaArtikla(
    var id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var skladisteId: Int,
    var vrstaDokumentaId: Int,
    var kolicina: Int,
    var korisnikId: Int,
)
