package hr.algebra.lmandic.procvat.model

data class Artikl (
    var id: Int?,
    var naziv: String,
    var grupaId: Int,
    var jedinicnaKolicina: Int,
    var kolicinaUPakiranju: Int,
    var bojaId: String,
    var slika: ByteArray,
    var barkod: String,
    var statusId: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artikl

        if (id != other.id) return false
        if (naziv != other.naziv) return false
        if (barkod != other.barkod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + naziv.hashCode()
        result = 31 * result + barkod.hashCode()
        return result
    }
}