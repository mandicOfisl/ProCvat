package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv"], unique = true)])
data class VrstaDokumenta(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var naziv: String,
    var izlazni: Boolean
) {
    companion object {
        fun fromContentValues(values: ContentValues): VrstaDokumenta =
            VrstaDokumenta(
                if (values.containsKey(VrstaDokumenta::_id.name)) values.getAsInteger(VrstaDokumenta::_id.name) else null,
                values.getAsString(VrstaDokumenta::naziv.name),
                values.getAsBoolean(VrstaDokumenta::izlazni.name)
            )
    }
}
