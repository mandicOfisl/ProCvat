package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StanjeSkladista(
    @PrimaryKey
    var _id: Int?,
    var artiklId: Int,
    var skladisteId: Int,
    var klasa: Int,
    var kolicina: Int,
)
