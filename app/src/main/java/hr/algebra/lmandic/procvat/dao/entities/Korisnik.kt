package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["korisnickoIme"], unique = true)])
data class Korisnik(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    var korisnickoIme: String,
    var lozinka: String
){
    companion object {
        fun fromContentValues(values: ContentValues): Korisnik =
            Korisnik(
                values.getAsInteger(Korisnik::_id.name),
                values.getAsString(Korisnik::korisnickoIme.name),
                values.getAsString(Korisnik::lozinka.name)
            )
    }
}
