package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv"], unique = true)])
data class Status(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    var naziv: String,
    var opis: String
){
    companion object {
        fun fromContentValues(values: ContentValues): Status =
            Status(
                values.getAsInteger(Status::_id.name) ?: 0,
                values.getAsString(Status::naziv.name),
                values.getAsString(Status::opis.name)
            )
    }
}
