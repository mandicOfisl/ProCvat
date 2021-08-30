package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artikl (
    @PrimaryKey
    var _id: Int?,
    var naziv: String,
    var grupaId: Int,
    var jedinicnaKolicina: Int,
    var kolicinaUPakiranju: Int,
    var bojaId: Int?,
    var picturePath: String?,
    var barkod: String?,
    var statusId: Int,
){
    companion object{
        fun fromContentValues(values: ContentValues): Artikl =
            Artikl(
                if (values.containsKey(Artikl::_id.name)) values.getAsInteger(Artikl::_id.name) else null,
                values.getAsString(Artikl::naziv.name),
                values.getAsInteger(Artikl::_id.name),
                values.getAsInteger(Artikl::jedinicnaKolicina.name),
                values.getAsInteger(Artikl::kolicinaUPakiranju.name),
                if (values.containsKey(Artikl::bojaId.name)) values.getAsInteger(Artikl::bojaId.name) else null,
                if (values.containsKey(Artikl::picturePath.name)) values.getAsString(Artikl::_id.name) else null,
                if (values.containsKey(Artikl::barkod.name)) values.getAsString(Artikl::barkod.name) else null,
                values.getAsInteger(Artikl::statusId.name)
            )
    }


}