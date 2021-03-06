package hr.algebra.lmandic.procvat.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import androidx.preference.PreferenceManager
import hr.algebra.lmandic.procvat.*
import hr.algebra.lmandic.procvat.dao.entities.*
import hr.algebra.lmandic.procvat.model.FlowerItem


fun View.applyAnimation(resourceId: Int)
    = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int)
        = startActivity(Intent(this, T::class.java).apply { putExtra(key, value) })

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))

fun Context.fetchArtikli() : MutableList<Artikl> {
    val items = mutableListOf<Artikl>()
    val artiklCursor = contentResolver?.query(
        ARTIKLI_PROVIDER_CONTENT_URI,
        null, null, null, null
    )
    if (artiklCursor != null) {
        while (artiklCursor.moveToNext()) {
            items.add(
                Artikl(
                    artiklCursor.getInt(artiklCursor.getColumnIndex(Artikl::_id.name)),
                    artiklCursor.getString(artiklCursor.getColumnIndex(Artikl::naziv.name)),
                    artiklCursor.getInt(artiklCursor.getColumnIndex(Artikl::grupaId.name)),
                    artiklCursor.getInt(artiklCursor.getColumnIndex(Artikl::jedinicnaKolicina.name)),
                    artiklCursor.getInt(artiklCursor.getColumnIndex(Artikl::kolicinaUPakiranju.name)),
                    artiklCursor.getIntOrNull(artiklCursor.getColumnIndex(Artikl::bojaId.name)),
                    artiklCursor.getStringOrNull(artiklCursor.getColumnIndex(Artikl::picturePath.name)),
                    artiklCursor.getStringOrNull(artiklCursor.getColumnIndex(Artikl::barkod.name)),
                    artiklCursor.getInt(artiklCursor.getColumnIndex(Artikl::statusId.name))
                )
            )
        }
    }

    artiklCursor?.close()

    return items
}

fun Context.fetchGrupe() : MutableList<Grupa> {
    val grupe = mutableListOf<Grupa>()

    val grupaCuror = contentResolver.query(
        GRUPE_PROVIDER_CONTENT_URI,
        null,
        null,
        null,
        null
    )
    if (grupaCuror != null) {
        while (grupaCuror.moveToNext()) {
            grupe.add(
                Grupa(
                    grupaCuror.getInt(grupaCuror.getColumnIndex(Grupa::_id.name)),
                    grupaCuror.getString(grupaCuror.getColumnIndex(Grupa::naziv.name))
                )
            )
        }
    }

    grupaCuror?.close()

    return grupe
}

fun Context.fetchBoje() : MutableList<Boja> {
    val boje = mutableListOf<Boja>()

    val bojaCursor = contentResolver.query(
        BOJE_PROVIDER_CONTENT_URI,
        null,
        null,
        null,
        null
    )
    if (bojaCursor != null) {
        while (bojaCursor.moveToNext()) {
            boje.add(
                Boja(
                    bojaCursor.getInt(bojaCursor.getColumnIndex(Boja::_id.name)),
                    bojaCursor.getString(bojaCursor.getColumnIndex(Boja::bojaHex.name)),
                    bojaCursor.getString(bojaCursor.getColumnIndex(Boja::naziv.name))
                )
            )
        }
    }

    bojaCursor?.close()

    return boje
}

fun Context.fetchStanjaSkladista() : MutableList<StanjeSkladista>{
    val stanja = mutableListOf<StanjeSkladista>()

    val stanjeCursor = contentResolver.query(
        STANJA_SKLADISTA_PROVIDER_CONTENT_URI,
        null,
        null,
        null,
        null
    )
    if (stanjeCursor != null){
        while (stanjeCursor.moveToNext()){
            stanja.add(
                StanjeSkladista(
                    stanjeCursor.getInt(stanjeCursor.getColumnIndex(StanjeSkladista::_id.name)),
                    stanjeCursor.getInt(stanjeCursor.getColumnIndex(StanjeSkladista::artiklId.name)),
                    stanjeCursor.getInt(stanjeCursor.getColumnIndex(StanjeSkladista::skladisteId.name)),
                    stanjeCursor.getInt(stanjeCursor.getColumnIndex(StanjeSkladista::klasa.name)),
                    stanjeCursor.getInt(stanjeCursor.getColumnIndex(StanjeSkladista::kolicina.name))
                )
            )
        }
    }

    stanjeCursor?.close()

    return stanja
}

fun Context.fetchNarudzbe() : MutableList<Narudzba>{
    val narudzbe = mutableListOf<Narudzba>()

    val narudzbaCursor = contentResolver.query(
        NARUDZBE_PROVIDER_CONTENT_URI,
        null,
        null,
        null,
        null
    )
    if (narudzbaCursor != null){
        while (narudzbaCursor.moveToNext()){
            narudzbe.add(
                Narudzba(
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::_id.name)),
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::dokumentId.name)),
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::artiklId.name)),
                    narudzbaCursor.getString(narudzbaCursor.getColumnIndex(Narudzba::datumNarudzbe.name)),
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::kolicina.name)),
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::statusId.name)),
                    narudzbaCursor.getInt(narudzbaCursor.getColumnIndex(Narudzba::korisnikId.name))
                )
            )
        }
    }

    narudzbaCursor?.close()

    return narudzbe
}

fun Context.fetchFlowerItems() : MutableList<FlowerItem>{
    var flowers: MutableList<FlowerItem> = mutableListOf()

    val artikli = fetchArtikli()
    val stanja = fetchStanjaSkladista()
    val narudzbe = fetchNarudzbe()
    val boje = fetchBoje()
    val grupe = fetchGrupe()

    for (artikl in artikli){
        flowers.add(
            FlowerItem(
                artikl,
                grupe.first {g -> g._id == artikl.grupaId}.naziv,
                boje.first {b -> b._id == artikl.bojaId}.naziv,
                boje.first { b -> b._id == artikl.bojaId }.bojaHex,
                stanja
                    .filter { s -> s.artiklId == artikl._id!! }
                    .sumOf { stanje -> stanje.kolicina },
                narudzbe
                    .filter {
                            n -> n.artiklId == artikl._id!!
                    }
                    .sumOf { nar -> nar.kolicina }
            )
        )
    }

    return flowers
}


fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.setStringPreference(key: String, value: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putString(key, value)
        .apply()

fun Context.getStringPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getString(key, "")
