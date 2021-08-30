package hr.algebra.lmandic.procvat.dao.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["naziv", "oib"], unique = true)])
data class Partner(
    @PrimaryKey(autoGenerate = true)
    var _id: Int?,
    var naziv: String,
    var oib: String?,
    var adresa: String?,
    var mjesto: String?,
    var postanskiBroj: String?,
    var brojMobitela: String?,
    var email: String?,
    var iban: String?
){
    companion object{
        fun fromContentValues(values: ContentValues): Partner =
            Partner(
                if (values.containsKey(Partner::_id.name)) values.getAsInteger(Partner::_id.name) else null,
                values.getAsString(Partner::naziv.name),
                if (values.containsKey(Partner::oib.name)) values.getAsString(Partner::oib.name) else null,
                if (values.containsKey(Partner::adresa.name)) values.getAsString(Partner::adresa.name) else null,
                if (values.containsKey(Partner::mjesto.name)) values.getAsString(Partner::mjesto.name) else null,
                if (values.containsKey(Partner::postanskiBroj.name)) values.getAsString(Partner::postanskiBroj.name) else null,
                if (values.containsKey(Partner::brojMobitela.name)) values.getAsString(Partner::brojMobitela.name) else null,
                if (values.containsKey(Partner::email.name)) values.getAsString(Partner::email.name) else null,
                if (values.containsKey(Partner::iban.name)) values.getAsString(Partner::iban.name) else null,

            )
    }
}
