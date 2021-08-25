package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Partner(
    @PrimaryKey
    var _id: Int?,
    var naziv: String,
    var oib: String?,
    var adresa: String?,
    var mjesto: String?,
    var postanskiBroj: String?,
    var brojMobitela: String?,
    var email: String?,
    var iban: String?
)
