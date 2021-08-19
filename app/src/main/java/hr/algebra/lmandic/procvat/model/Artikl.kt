package hr.algebra.lmandic.procvat.model

data class Artikl (
    var id: Int?,
    var naziv: String,
    var grupaId: Int,
    var jedinicnaKolicina: Int,
    var kolicinaUPakiranju: Int,
    var bojaId: Int?,
    var picturePath: String?,
    var barkod: String?,
    var statusId: Int,
)