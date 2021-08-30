package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UnosStanjaArtikla(
    @PrimaryKey
    var _id: Int?,
    var datumUnosa: String,
    var artiklId: Int,
    var skladisteId: Int,
    var kolicina: Int,
    var korisnikId: Int,
) {
    companion object {
        fun fromContentValues(values: ContentValues): UnosStanjaArtikla =
            UnosStanjaArtikla(
                if (values.containsKey(UnosStanjaArtikla::_id.name)) values.getAsInteger(UnosStanjaArtikla::_id.name) else null,
                values.getAsString(UnosStanjaArtikla::datumUnosa.name),
                values.getAsInteger(UnosStanjaArtikla::artiklId.name),
                values.getAsInteger(UnosStanjaArtikla::skladisteId.name),
                values.getAsInteger(UnosStanjaArtikla::kolicina.name),
                values.getAsInteger(UnosStanjaArtikla::korisnikId.name)
            )
    }
}
