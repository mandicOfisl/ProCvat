package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Narudzba(
    @PrimaryKey
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var datumNarudzbe: String,
    var kolicina: Int,
    var statusId: Int,
    var korisnikId: Int
) {
    companion object {
        fun fromContentValues(values: ContentValues): Narudzba =
            Narudzba(
                if (values.containsKey(Narudzba::_id.name)) values.getAsInteger(Narudzba::_id.name) else null,
                values.getAsInteger(Narudzba::dokumentId.name),
                values.getAsInteger(Narudzba::artiklId.name),
                values.getAsString(Narudzba::datumNarudzbe.name),
                values.getAsInteger(Narudzba::kolicina.name),
                values.getAsInteger(Narudzba::statusId.name),
                values.getAsInteger(Narudzba::korisnikId.name)
            )
    }
}
