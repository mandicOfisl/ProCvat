package hr.algebra.lmandic.procvat.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dokument(
    @PrimaryKey
    var _id: Int?,
    var vrstaDokumentaId: Int,
    var skladisteId: Int,
    var datumKreiranja: String,
    var ukupnaKolicina: Int,
    var napomena: String?,
    var statusId: Int,
    var partnerId: Int,
    var korisnikId: Int
)
