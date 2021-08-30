package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Prodaja(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var dokumentId: Int,
    var artiklId: Int,
    var skladisteId: Int,
    var kolicina: Int,
    var statusId: Int,
    var korisnikId: Int
){
    companion object {
        fun fromContentValues(values: ContentValues): Prodaja =
            Prodaja(
                if (values.containsKey(Prodaja::_id.name)) values.getAsInteger(Prodaja::_id.name) else null,
                values.getAsInteger(Prodaja::dokumentId.name),
                values.getAsInteger(Prodaja::artiklId.name),
                values.getAsInteger(Prodaja::skladisteId.name),
                values.getAsInteger(Prodaja::kolicina.name),
                values.getAsInteger(Prodaja::statusId.name),
                values.getAsInteger(Prodaja::korisnikId.name)
            )
    }
}
