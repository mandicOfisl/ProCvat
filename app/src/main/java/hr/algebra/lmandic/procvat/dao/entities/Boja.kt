package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Boja (
    @PrimaryKey
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