package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dokument(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var vrstaDokumentaId: Int,
    var skladisteId: Int,
    var datumKreiranja: String,
    var ukupnaKolicina: Int,
    var napomena: String?,
    var statusId: Int,
    var partnerId: Int,
    var korisnikId: Int
){
    companion object{
        fun fromContentValues(values: ContentValues): Dokument =
            Dokument(
                if (values.containsKey(Dokument::_id.name)) values.getAsInteger(Dokument::_id.name) else null,
                values.getAsInteger(Dokument::vrstaDokumentaId.name),
                values.getAsInteger(Dokument::skladisteId.name),
                values.getAsString(Dokument::datumKreiranja.name),
                values.getAsInteger(Dokument::ukupnaKolicina.name),
                if (values.containsKey(Dokument::napomena.name)) values.getAsString(Dokument::napomena.name) else null,
                values.getAsInteger(Dokument::statusId.name),
                values.getAsInteger(Dokument::partnerId.name),
                values.getAsInteger(Dokument::korisnikId.name)
            )
    }
}
