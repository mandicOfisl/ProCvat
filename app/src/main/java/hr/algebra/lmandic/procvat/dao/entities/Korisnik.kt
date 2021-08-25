package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Korisnik(
    @PrimaryKey
    var _id: Int?,
    var korisnickoIme: String,
    var lozinka: String
)
