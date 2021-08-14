package hr.algebra.lmandic.procvat.model

data class Partner(
    var id: Int?,
    var naziv: String,
    var oib: String?,
    var adresa: String?,
    var mjesto: String?,
    var postanskiBroj: String?,
    var brojMobitela: String?,
    var email: String?,
    var iban: String?
)
