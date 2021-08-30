package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Korisnik(
    @PrimaryKey
    var _id: Int?,
    var korisnickoIme: String,
    var lozinka: String
){
    companion object {
        fun fromContentValues(values: ContentValues): Korisnik =
            Korisnik(
                if (values.containsKey(Korisnik::_id.name)) values.getAsInteger(Korisnik::_id.name) else null,
                values.getAsString(Korisnik::korisnickoIme.name),
                values.getAsString(Korisnik::lozinka.name)
            )
    }
}
