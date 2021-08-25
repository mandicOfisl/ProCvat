package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grupa(
    @PrimaryKey
    var _id: Int?,
    var naziv: String
)
