package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["bojaHex", "naziv"], unique = true)])
data class Boja (
    @PrimaryKey(autoGenerate = true)
    val _id: Int?,
    val bojaHex: String,
    val naziv: String
    ){
    companion object{
        fun fromContentValues(values: ContentValues): Boja =
            Boja(
                if (values.containsKey(Boja::_id.name)) values.getAsInteger(Boja::_id.name) else null,
                values.getAsString(Boja::bojaHex.name),
                values.getAsString(Boja::naziv.name)
            )
    }
}