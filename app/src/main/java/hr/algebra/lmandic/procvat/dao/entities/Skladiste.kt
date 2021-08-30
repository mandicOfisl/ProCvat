package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skladiste(
    @PrimaryKey
    var _id: Int?,
    var naziv: String
) {
    companion object {
        fun fromContentValues(values: ContentValues): Skladiste =
            Skladiste(
                if (values.containsKey(Skladiste::_id.name)) values.getAsInteger(Skladiste::_id.name) else null,
                values.getAsString(Skladiste::naziv.name)
            )
    }
}
