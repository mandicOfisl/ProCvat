package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StanjeSkladista(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var artiklId: Int,
    var skladisteId: Int,
    var klasa: Int,
    var kolicina: Int,
){
    companion object {
        fun fromContentValues(values: ContentValues): StanjeSkladista =
            StanjeSkladista(
                if (values.containsKey(StanjeSkladista::_id.name)) values.getAsInteger(StanjeSkladista::_id.name) else null,
                values.getAsInteger(StanjeSkladista::artiklId.name),
                values.getAsInteger(StanjeSkladista::skladisteId.name),
                values.getAsInteger(StanjeSkladista::klasa.name),
                values.getAsInteger(StanjeSkladista::kolicina.name)
            )
    }
}
