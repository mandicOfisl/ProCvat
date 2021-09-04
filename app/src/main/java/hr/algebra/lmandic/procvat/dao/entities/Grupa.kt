package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv"], unique = true)])
data class Grupa(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    var naziv: String
){
    companion object{
        fun fromContentValues(values: ContentValues): Grupa =
            Grupa(
                values.getAsInteger(Grupa::_id.name) ?: 0,
                values.getAsString(Grupa::naziv.name)
            )
    }
}
