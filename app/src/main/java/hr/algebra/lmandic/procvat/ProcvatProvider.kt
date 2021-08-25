package hr.algebra.lmandic.procvat

import android.content.*
import android.database.Cursor
import android.net.Uri
import hr.algebra.lmandic.procvat.dao.ProcvatRepo
import hr.algebra.lmandic.procvat.dao.ProcvatSqlHelper
import hr.algebra.lmandic.procvat.factory.getProcvatRepo
import hr.algebra.lmandic.procvat.dao.entities.*

private const val AUTHORITY = "hr.algebra.lmandic.procvat.api.provider"

private const val ARTIKL_PATH = "artikli"
private const val ARTIKLI = 10
private const val ARTIKLI_ID = 11

private const val BOJA_PATH = "boje"
private const val BOJE = 20
private const val BOJE_ID = 21

private const val DOKUMENT_PATH = "dokumenti"
private const val DOKUMENTI = 30
private const val DOKUMENTI_ID = 31

private const val GRUPA_PATH = "grupe"
private const val GRUPE = 40
private const val GRUPE_ID = 41

private const val KORISNIK_PATH = "korisnici"
private const val KORISNICI = 50
private const val KORISNICI_ID = 51

private const val NARUDZBA_PATH = "narudzbe"
private const val NARUDZBE = 60
private const val NARUDZBE_ID = 61

private const val PARTNER_PATH = "partneri"
private const val PARTNERI = 70
private const val PARTNERI_ID = 71

private const val PRODAJA_PATH = "prodaje"
private const val PRODAJE = 80
private const val PRODAJE_ID = 81

private const val SKLADISTE_PATH = "skladista"
private const val SKLADISTA = 90
private const val SKLADISTA_ID = 91

private const val STANJE_SKLADISTA_PATH = "stanjaSkladista"
private const val STANJA_SKLADISTA = 100
private const val STANJA_SKLADISTA_ID = 101

private const val STATUS_PATH = "statusi"
private const val STATUSI = 110
private const val STATUSI_ID = 111

private const val UNOS_STANJA_ARTIKLA_PATH = "unosiStanjaArtikla"
private const val UNOSI_STANJA_ARTIKLA = 120
private const val UNOSI_STANJA_ARTIKLA_ID = 121

private const val VRSTA_DOKUMENTA_PATH = "vrsteDokumenta"
private const val VRSTE_DOKUMENTA = 130
private const val VRSTE_DOKUMENTA_ID = 131

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)){
    addURI(AUTHORITY, ARTIKL_PATH, ARTIKLI)
    addURI(AUTHORITY, "$ARTIKL_PATH/#", ARTIKLI_ID)
    addURI(AUTHORITY, BOJA_PATH, BOJE)
    addURI(AUTHORITY, "$BOJA_PATH/#", BOJE_ID)
    addURI(AUTHORITY, DOKUMENT_PATH, DOKUMENTI)
    addURI(AUTHORITY, "$DOKUMENT_PATH/#", DOKUMENTI_ID)
    addURI(AUTHORITY, GRUPA_PATH, GRUPE)
    addURI(AUTHORITY, "$GRUPA_PATH/#", GRUPE_ID)
    addURI(AUTHORITY, KORISNIK_PATH, KORISNICI)
    addURI(AUTHORITY, "$KORISNIK_PATH/#", KORISNICI_ID)
    addURI(AUTHORITY, NARUDZBA_PATH, NARUDZBE)
    addURI(AUTHORITY, "$NARUDZBA_PATH/#", NARUDZBE_ID)
    addURI(AUTHORITY, PARTNER_PATH, PARTNERI)
    addURI(AUTHORITY, "$PARTNER_PATH/#", PARTNERI_ID)
    addURI(AUTHORITY, PRODAJA_PATH, PRODAJE)
    addURI(AUTHORITY, "$PRODAJA_PATH/#", PRODAJE_ID)
    addURI(AUTHORITY, SKLADISTE_PATH, SKLADISTA)
    addURI(AUTHORITY, "$SKLADISTE_PATH/#", SKLADISTA_ID)
    addURI(AUTHORITY, STANJE_SKLADISTA_PATH, STANJA_SKLADISTA)
    addURI(AUTHORITY, "$STANJE_SKLADISTA_PATH/#", STANJA_SKLADISTA_ID)
    addURI(AUTHORITY, STATUS_PATH, STATUSI)
    addURI(AUTHORITY, "$STATUS_PATH/#", STATUSI_ID)
    addURI(AUTHORITY, UNOS_STANJA_ARTIKLA_PATH, UNOSI_STANJA_ARTIKLA)
    addURI(AUTHORITY, "$UNOS_STANJA_ARTIKLA_PATH/#", UNOSI_STANJA_ARTIKLA_ID)
    addURI(AUTHORITY, VRSTA_DOKUMENTA_PATH, VRSTE_DOKUMENTA)
    addURI(AUTHORITY, "$VRSTA_DOKUMENTA_PATH/#", VRSTE_DOKUMENTA_ID)
    this
}


val ARTIKLI_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$ARTIKL_PATH")
val BOJE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$BOJA_PATH")
val DOKUMENTI_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$DOKUMENT_PATH")
val GRUPE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$GRUPA_PATH")
val KORISNICI_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$KORISNIK_PATH")
val NARUDZBE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$NARUDZBA_PATH")
val PARTNERI_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PARTNER_PATH")
val PRODAJE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PRODAJA_PATH")
val SKLADISTA_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$SKLADISTE_PATH")
val STANJA_SKLADISTA_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$STANJE_SKLADISTA_PATH")
val STATUSI_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$STATUS_PATH")
val UNOSI_STANJA_ARTIKALA_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$UNOS_STANJA_ARTIKLA_PATH")
val VRSTE_DOKUMENTA_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$VRSTA_DOKUMENTA_PATH")


class ProcvatProvider : ContentProvider() {

    private lateinit var repo: ProcvatRepo

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)){
            ARTIKLI -> return repo.delete(ProcvatSqlHelper.ARTIKL_TABLE_NAME, selection, selectionArgs)
            ARTIKLI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.ARTIKL_TABLE_NAME, "${Artikl::_id.name}=?", arrayOf(id))
            }
            BOJE -> return repo.delete(ProcvatSqlHelper.BOJA_TABLE_NAME, selection, selectionArgs)
            BOJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.BOJA_TABLE_NAME, "${Boja::_id.name}=?", arrayOf(id))
            }
            DOKUMENTI -> return repo.delete(ProcvatSqlHelper.DOKUMENT_TABLE_NAME, selection, selectionArgs)
            DOKUMENTI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.DOKUMENT_TABLE_NAME, "${Dokument::_id.name}=?", arrayOf(id))
            }
            GRUPE -> return repo.delete(ProcvatSqlHelper.GRUPA_TABLE_NAME, selection, selectionArgs)
            GRUPE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.GRUPA_TABLE_NAME, "${Grupa::_id.name}=?", arrayOf(id))
            }
            KORISNICI -> return repo.delete(ProcvatSqlHelper.KORISNIK_TABLE_NAME, selection, selectionArgs)
            KORISNICI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.KORISNIK_TABLE_NAME, "${Korisnik::_id.name}=?", arrayOf(id))
            }
            NARUDZBE -> return repo.delete(ProcvatSqlHelper.NARUDZBA_TABLE_NAME, selection, selectionArgs)
            NARUDZBE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.NARUDZBA_TABLE_NAME, "${Narudzba::_id.name}=?", arrayOf(id))
            }
            PARTNERI -> return repo.delete(ProcvatSqlHelper.PARTNER_TABLE_NAME, selection, selectionArgs)
            PARTNERI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.PARTNER_TABLE_NAME, "${Partner::_id.name}=?", arrayOf(id))
            }
            PRODAJE -> return repo.delete(ProcvatSqlHelper.PRODAJA_TABLE_NAME, selection, selectionArgs)
            PRODAJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.PRODAJA_TABLE_NAME, "${Prodaja::_id.name}=?", arrayOf(id))
            }
            SKLADISTA -> return repo.delete(ProcvatSqlHelper.SKLADISTE_TABLE_NAME, selection, selectionArgs)
            SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.SKLADISTE_TABLE_NAME, "${Skladiste::_id.name}=?", arrayOf(id))
            }
            STANJA_SKLADISTA -> return repo.delete(ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME, selection, selectionArgs)
            STANJA_SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME, "${StanjeSkladista::_id.name}=?", arrayOf(id))
            }
            STATUSI -> return repo.delete(ProcvatSqlHelper.STATUS_TABLE_NAME, selection, selectionArgs)
            STATUSI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.STATUS_TABLE_NAME, "${Status::_id.name}=?", arrayOf(id))
            }
            UNOSI_STANJA_ARTIKLA -> return repo.delete(ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME, selection, selectionArgs)
            UNOSI_STANJA_ARTIKLA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME, "${UnosStanjaArtikla::_id.name}=?", arrayOf(id))
            }
            VRSTE_DOKUMENTA -> return repo.delete(ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME, selection, selectionArgs)
            VRSTE_DOKUMENTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.delete(ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME, "${VrstaDokumenta::_id.name}=?", arrayOf(id))
            }
        }
        throw IllegalArgumentException("Wrong URI!")
    }

    override fun getType(uri: Uri): String {
        when(URI_MATCHER.match(uri)){
            ARTIKLI -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + ARTIKL_PATH
            ARTIKLI_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + ARTIKL_PATH
            BOJE -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + BOJA_PATH
            BOJE_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + BOJA_PATH
            DOKUMENTI -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + DOKUMENT_PATH
            DOKUMENTI_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + DOKUMENT_PATH
            GRUPE -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + GRUPA_PATH
            GRUPE_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + GRUPA_PATH
            KORISNICI -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + KORISNIK_PATH
            KORISNICI_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + KORISNIK_PATH
            NARUDZBE -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + NARUDZBA_PATH
            NARUDZBE_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + NARUDZBA_PATH
            PARTNERI -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PARTNER_PATH
            PARTNERI_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PARTNER_PATH
            PRODAJE -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PRODAJA_PATH
            PRODAJE_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PRODAJA_PATH
            SKLADISTA -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + SKLADISTE_PATH
            SKLADISTA_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + SKLADISTE_PATH
            STANJA_SKLADISTA -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + STANJE_SKLADISTA_PATH
            STANJA_SKLADISTA_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + STANJE_SKLADISTA_PATH
            STATUSI -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + STATUS_PATH
            STATUSI_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + STATUS_PATH
            UNOSI_STANJA_ARTIKLA -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + UNOS_STANJA_ARTIKLA_PATH
            UNOSI_STANJA_ARTIKLA_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + UNOS_STANJA_ARTIKLA_PATH
            VRSTE_DOKUMENTA -> return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + VRSTA_DOKUMENTA_PATH
            VRSTE_DOKUMENTA_ID -> return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + VRSTA_DOKUMENTA_PATH
        }
        throw IllegalArgumentException("Wrong URI!")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val tableName: String
        val contentUri: Uri

        when(URI_MATCHER.match(uri)){
            ARTIKLI -> {
                tableName = ProcvatSqlHelper.ARTIKL_TABLE_NAME
                contentUri = ARTIKLI_PROVIDER_CONTENT_URI
            }
            BOJE -> {
                tableName = ProcvatSqlHelper.BOJA_TABLE_NAME
                contentUri = BOJE_PROVIDER_CONTENT_URI
            }
            DOKUMENTI -> {
                tableName = ProcvatSqlHelper.DOKUMENT_TABLE_NAME
                contentUri = DOKUMENTI_PROVIDER_CONTENT_URI
            }
            GRUPE -> {
                tableName = ProcvatSqlHelper.GRUPA_TABLE_NAME
                contentUri = GRUPE_PROVIDER_CONTENT_URI
            }
            KORISNICI -> {
                tableName = ProcvatSqlHelper.KORISNIK_TABLE_NAME
                contentUri = KORISNICI_PROVIDER_CONTENT_URI
            }
            NARUDZBE -> {
                tableName = ProcvatSqlHelper.NARUDZBA_TABLE_NAME
                contentUri = NARUDZBE_PROVIDER_CONTENT_URI
            }
            PARTNERI -> {
                tableName = ProcvatSqlHelper.PARTNER_TABLE_NAME
                contentUri = PARTNERI_PROVIDER_CONTENT_URI
            }
            PRODAJE -> {
                tableName = ProcvatSqlHelper.PRODAJA_TABLE_NAME
                contentUri = PRODAJE_PROVIDER_CONTENT_URI
            }
            SKLADISTA -> {
                tableName = ProcvatSqlHelper.SKLADISTE_TABLE_NAME
                contentUri = SKLADISTA_PROVIDER_CONTENT_URI
            }
            STANJA_SKLADISTA -> {
                tableName = ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME
                contentUri = STANJA_SKLADISTA_PROVIDER_CONTENT_URI
            }
            STATUSI -> {
                tableName = ProcvatSqlHelper.STATUS_TABLE_NAME
                contentUri = STATUSI_PROVIDER_CONTENT_URI
            }
            UNOSI_STANJA_ARTIKLA -> {
                tableName = ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME
                contentUri = UNOSI_STANJA_ARTIKALA_PROVIDER_CONTENT_URI
            }
            VRSTE_DOKUMENTA -> {
                tableName = ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME
                contentUri = VRSTE_DOKUMENTA_PROVIDER_CONTENT_URI
            }
            else -> throw IllegalArgumentException("Wrong uri!")
        }

        val id = repo.insert(tableName, values)

        return ContentUris.withAppendedId(contentUri, id)
    }

    override fun onCreate(): Boolean {
        repo = getProcvatRepo(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val tableName: String
        when(URI_MATCHER.match(uri)){
            ARTIKLI -> tableName = ProcvatSqlHelper.ARTIKL_TABLE_NAME
            BOJE -> tableName = ProcvatSqlHelper.BOJA_TABLE_NAME
            DOKUMENTI -> tableName = ProcvatSqlHelper.DOKUMENT_TABLE_NAME
            GRUPE -> tableName = ProcvatSqlHelper.GRUPA_TABLE_NAME
            KORISNICI -> tableName = ProcvatSqlHelper.KORISNIK_TABLE_NAME
            NARUDZBE -> tableName = ProcvatSqlHelper.NARUDZBA_TABLE_NAME
            PARTNERI -> tableName = ProcvatSqlHelper.PARTNER_TABLE_NAME
            PRODAJE -> tableName = ProcvatSqlHelper.PRODAJA_TABLE_NAME
            SKLADISTA -> tableName = ProcvatSqlHelper.SKLADISTE_TABLE_NAME
            STANJA_SKLADISTA -> tableName = ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME
            STATUSI -> tableName = ProcvatSqlHelper.STATUS_TABLE_NAME
            UNOSI_STANJA_ARTIKLA -> tableName = ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME
            VRSTE_DOKUMENTA -> tableName = ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME
            else -> throw IllegalArgumentException("Wrong uri!")
        }

        return repo.query(tableName, projection, selection, selectionArgs, sortOrder)
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)){
            ARTIKLI -> return repo.update(ProcvatSqlHelper.ARTIKL_TABLE_NAME, values, selection, selectionArgs)
            ARTIKLI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.ARTIKL_TABLE_NAME, values, "${Artikl::_id.name}=?", arrayOf(id))
            }
            BOJE -> return repo.update(ProcvatSqlHelper.BOJA_TABLE_NAME, values, selection, selectionArgs)
            BOJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.BOJA_TABLE_NAME, values, "${Boja::_id.name}=?", arrayOf(id))
            }
            DOKUMENTI -> return repo.update(ProcvatSqlHelper.DOKUMENT_TABLE_NAME, values, selection, selectionArgs)
            DOKUMENTI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.DOKUMENT_TABLE_NAME, values, "${Dokument::_id.name}=?", arrayOf(id))
            }
            GRUPE -> return repo.update(ProcvatSqlHelper.GRUPA_TABLE_NAME, values, selection, selectionArgs)
            GRUPE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.GRUPA_TABLE_NAME, values, "${Grupa::_id.name}=?", arrayOf(id))
            }
            KORISNICI -> return repo.update(ProcvatSqlHelper.KORISNIK_TABLE_NAME, values, selection, selectionArgs)
            KORISNICI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.KORISNIK_TABLE_NAME, values, "${Korisnik::_id.name}=?", arrayOf(id))
            }
            NARUDZBE -> return repo.update(ProcvatSqlHelper.NARUDZBA_TABLE_NAME, values, selection, selectionArgs)
            NARUDZBE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.NARUDZBA_TABLE_NAME, values, "${Narudzba::_id.name}=?", arrayOf(id))
            }
            PARTNERI -> return repo.update(ProcvatSqlHelper.PARTNER_TABLE_NAME, values, selection, selectionArgs)
            PARTNERI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.PARTNER_TABLE_NAME, values, "${Partner::_id.name}=?", arrayOf(id))
            }
            PRODAJE -> return repo.update(ProcvatSqlHelper.PRODAJA_TABLE_NAME, values, selection, selectionArgs)
            PRODAJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.PRODAJA_TABLE_NAME, values, "${Prodaja::_id.name}=?", arrayOf(id))
            }
            SKLADISTA -> return repo.update(ProcvatSqlHelper.SKLADISTE_TABLE_NAME, values, selection, selectionArgs)
            SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.SKLADISTE_TABLE_NAME, values, "${Skladiste::_id.name}=?", arrayOf(id))
            }
            STANJA_SKLADISTA -> return repo.update(ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME, values, selection, selectionArgs)
            STANJA_SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.STANJE_SKLADISTA_TABLE_NAME, values, "${StanjeSkladista::_id.name}=?", arrayOf(id))
            }
            STATUSI -> return repo.update(ProcvatSqlHelper.STATUS_TABLE_NAME, values, selection, selectionArgs)
            STATUSI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.STATUS_TABLE_NAME, values, "${Status::_id.name}=?", arrayOf(id))
            }
            UNOSI_STANJA_ARTIKLA -> return repo.update(ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME, values, selection, selectionArgs)
            UNOSI_STANJA_ARTIKLA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.UNOS_STANJA_ARTIKLA_TABLE_NAME, values, "${UnosStanjaArtikla::_id.name}=?", arrayOf(id))
            }
            VRSTE_DOKUMENTA -> return repo.update(ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME, values, selection, selectionArgs)
            VRSTE_DOKUMENTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    return repo.update(ProcvatSqlHelper.VRSTA_DOKUMENTA_TABLE_NAME, values, "${VrstaDokumenta::_id.name}=?", arrayOf(id))
            }
        }
        throw IllegalArgumentException("Wrong URI!")
    }
}