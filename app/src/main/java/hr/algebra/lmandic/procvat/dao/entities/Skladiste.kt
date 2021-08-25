package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skladiste(
    @PrimaryKey
    var _id: Int?,
    var naziv: String
)
