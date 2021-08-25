package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artikl (
    @PrimaryKey
    var _id: Int?,
    var naziv: String,
    var grupaId: Int,
    var jedinicnaKolicina: Int,
    var kolicinaUPakiranju: Int,
    var bojaId: Int?,
    var picturePath: String?,
    var barkod: String?,
    var statusId: Int,
)