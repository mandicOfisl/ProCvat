package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UnosStanjaArtikla(
    @PrimaryKey
    var _id: Int?,
    var datumUnosa: String,
    var artiklId: Int,
    var skladisteId: Int,
    var kolicina: Int,
    var korisnikId: Int,
)
