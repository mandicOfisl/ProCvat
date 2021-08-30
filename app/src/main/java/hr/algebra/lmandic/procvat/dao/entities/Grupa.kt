package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv"], unique = true)])
data class Grupa(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var naziv: String
){
    companion object{
        fun fromContentValues(values: ContentValues): Grupa =
            Grupa(
                if (values.containsKey(Grupa::_id.name)) values.getAsInteger(Grupa::_id.name) else null,
                values.getAsString(Grupa::naziv.name)
            )
    }
}
