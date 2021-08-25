package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Narudzba(
    @PrimaryKey
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var datumNarudzbe: String,
    var kolicina: Int?,
    var statusId: Int,
    var korisnikId: Int
)
