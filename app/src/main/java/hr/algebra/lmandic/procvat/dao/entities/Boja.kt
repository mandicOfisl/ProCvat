package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Boja (
    @PrimaryKey
    val _id: Int?,
    val bojaHex: String,
    val naziv: String)