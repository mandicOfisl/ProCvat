package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Prodaja(
    @PrimaryKey
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var skladisteId: Int,
    var kolicina: Int,
    var statusId: Int,
    var korisnikId: Int
)
