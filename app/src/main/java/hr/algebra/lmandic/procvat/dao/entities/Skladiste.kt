package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv"], unique = true)])
data class Skladiste(
    @PrimaryKey(autoGenerate = true)
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
