package hr.algebra.lmandic.procvat.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.lmandic.procvat.model.*

private const val DB_NAME = "procvat.db"
private const val DB_VERSION = 1

private const val GRUPA_TABLE_NAME = "Grupa"
private val GRUPA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $GRUPA_TABLE_NAME(" +
        "${Grupa::id.name} INTEGER NOT NULL UNIQUE," +
        "${Grupa::naziv.name} TEXT NOT NULL UNIQUE," +
        "PRIMARY KEY(${Grupa::id.name} AUTOINCREMENT)"


private const val PARTNER_TABLE_NAME = "Partner"
private val PARTNER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $PARTNER_TABLE_NAME(" +
        "${Partner::id.name} INTEGER NOT NULL UNIQUE," +
        "${Partner::naziv.name} TEXT NOT NULL UNIQUE," +
        "${Partner::oib.name} TEXT UNIQUE," +
        "${Partner::adresa.name} TEXT," +
        "${Partner::mjesto.name} TEXT," +
        "${Partner::postanskiBroj.name} TEXT," +
        "${Partner::brojMobitela.name} TEXT," +
        "${Partner::email.name} TEXT," +
        "${Partner::iban.name} TEXT," +
        "PRIMARY KEY(${Partner::id.name} AUTOINCREMENT)"

private const val SKLADISTE_TABLE_NAME = "Skladiste"
private val SKLADISTE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $SKLADISTE_TABLE_NAME(" +
        "${Skladiste::id.name} INTEGER NOT NULL UNIQUE," +
        "${Skladiste::naziv.name} TEXT NOT NULL UNIQUE," +
        "PRIMARY KEY(${Skladiste::id.name} AUTOINCREMENT)"

private const val VRSTA_DOKUMENTA_TABLE_NAME = "VrstaDokumenta"
private val VRSTA_DOKUMENTA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $VRSTA_DOKUMENTA_TABLE_NAME(" +
        "${VrstaDokumenta::id.name} INTEGER NOT NULL UNIQUE," +
        "${VrstaDokumenta::naziv.name} TEXT NOT NULL UNIQUE," +
        "${VrstaDokumenta::izlazni.name} TEXT NOT NULL," +
        "PRIMARY KEY(${VrstaDokumenta::id.name})"

private const val KORISNIK_TABLE_NAME = "Korisnik"
private val KORISNIK_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $KORISNIK_TABLE_NAME(" +
        "${Korisnik::id.name} INTEGER NOT NULL UNIQUE," +
        "${Korisnik::korisnickoIme.name} TEXT NOT NULL UNIQUE," +
        "${Korisnik::lozinka.name} TEXT NOT NULL UNIQUE," +
        "PRIMARY KEY(${Korisnik::id.name} AUTOINCREMENT)"

private const val STATUS_TABLE_NAME = "Status"
private val STATUS_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $STATUS_TABLE_NAME(" +
        "${Status::id.name} INTEGER NOT NULL UNIQUE," +
        "${Status::naziv.name} TEXT NOT NULL UNIQUE," +
        "${Status::opis.name} TEXT," +
        "PRIMARY KEY(${Status::id.name} AUTOINCREMENT)"

private const val BOJA_TABLE_NAME = "Boja"
private val BOJA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $BOJA_TABLE_NAME(" +
        "${Boja::id.name} INTEGER NOT NULL UNIQUE," +
        "${Boja::naziv.name} TEXT NOT NULL UNIQUE," +
        "${Boja::bojaHex} TEXT NOT NULL UNIQUE," +
        "PRIMARY KEY(${Boja::id.name} AUTOINCREMENT)"

private const val ARTIKL_TABLE_NAME = "Artikl"
private val ARTIKL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $ARTIKL_TABLE_NAME(" +
        "${Artikl::id.name} INTEGER NOT NULL UNIQUE," +
        "${Artikl::naziv.name} TEXT NOT NULL," +
        "${Artikl::grupaId.name} INTEGER NOT NULL," +
        "${Artikl::jedinicnaKolicina.name} INTEGER NOT NULL," +
        "${Artikl::kolicinaUPakiranju.name} INTEGER NOT NULL," +
        "${Artikl::bojaId.name} INTEGER," +
        "${Artikl::picturePath.name} TEXT," +
        "${Artikl::barkod.name} TEXT UNIQUE," +
        "${Artikl::statusId.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${Artikl::id.name} AUTOINCREMENT),"+
        "FOREIGN KEY(\"bojaId\") REFERENCES \"Boja\"(\"bojaId\")," +
        "FOREIGN KEY(\"grupaId\") REFERENCES \"Grupa\"(\"grupaId\")"

private const val DOKUMENT_TABLE_NAME = "Dokument"
private val DOKUMENT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $DOKUMENT_TABLE_NAME(" +
        "${Dokument::id.name} INTEGER NOT NULL UNIQUE," +
        "${Dokument::vrstaDokumentaId.name} INTEGER NOT NULL," +
        "${Dokument::skladisteId.name} INTEGER NOT NULL," +
        "${Dokument::datumKreiranja.name} TEXT NOT NULL," +
        "${Dokument::ukupnaKolicina.name} INTEGER NOT NULL," +
        "${Dokument::napomena.name} TEXT," +
        "${Dokument::statusId.name} INTEGER," +
        "${Dokument::partnetId.name} INTEGER," +
        "${Dokument::korisnikId.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${Dokument::id.name} AUTOINCREMENT),"+
        "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
        "FOREIGN KEY(\"partnerId\") REFERENCES \"Partner\"(\"id\")," +
        "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
        "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\")"

private const val NARUDZBA_TABLE_NAME = "Narudzba"
private val NARUDZBA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $NARUDZBA_TABLE_NAME(" +
        "${Narudzba::id.name} INTEGER NOT NULL UNIQUE," +
        "${Narudzba::dokumentId.name} INTEGER NOT NULL," +
        "${Narudzba::artiklId.name} INTEGER NOT NULL," +
        "${Narudzba::datumNarudzbe.name} TEXT NOT NULL," +
        "${Narudzba::kolicina.name} INTEGER," +
        "${Narudzba::statusId.name} INTEGER NOT NULL," +
        "${Narudzba::korisnikId.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${Narudzba::id.name} AUTOINCREMENT),"+
        "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
        "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
        "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
        "FOREIGN KEY(\"statusId\") REFERENCES \"Status\"(\"id\")"

private const val STANJE_SKLADISTA_TABLE_NAME = "StanjeSkladista"
private val STANJE_SKLADISTA_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS $STANJE_SKLADISTA_TABLE_NAME(" +
        "${StanjeSkladista::artiklId.name} INTEGER NOT NULL," +
        "${StanjeSkladista::skladisteId.name} INTEGER NOT NULL," +
        "${StanjeSkladista::klasa.name} INTEGER NOT NULL," +
        "${StanjeSkladista::kolicina.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${StanjeSkladista::artiklId.name}," +
            "${StanjeSkladista::artiklId.name}," +
            "${StanjeSkladista::artiklId.name}),"+
        "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
        "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\")"

private const val UNOS_STANJA_ARTIKLA_TABLE_NAME = "UnosStanjaArtikla"
private val UNOS_STANJA_ARTIKLA_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS $UNOS_STANJA_ARTIKLA_TABLE_NAME(" +
        "${UnosStanjaArtikla::id.name} INTEGER NOT NULL UNIQUE," +
        "${UnosStanjaArtikla::dokumentId.name} INTEGER NOT NULL UNIQUE," +
        "${UnosStanjaArtikla::artiklId.name} INTEGER NOT NULL," +
        "${UnosStanjaArtikla::skladisteId.name} INTEGER NOT NULL," +
        "${UnosStanjaArtikla::vrstaDokumentaId.name} INTEGER NOT NULL," +
        "${UnosStanjaArtikla::kolicina.name} INTEGER NOT NULL," +
        "${UnosStanjaArtikla::korisnikId.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${UnosStanjaArtikla::id.name}," +
            "${UnosStanjaArtikla::dokumentId.name})" +
        "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
        "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
        "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
        "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
        "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\")"

private const val PRODAJA_TABLE_NAME = "Prodaja"
private val PRODAJA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $PRODAJA_TABLE_NAME(" +
        "${Prodaja::id.name} INTEGER NOT NULL UNIQUE," +
        "${Prodaja::dokumentId.name} INTEGER NOT NULL UNIQUE," +
        "${Prodaja::artiklId.name} INTEGER NOT NULL," +
        "${Prodaja::skladisteId.name} INTEGER NOT NULL," +
        "${Prodaja::vrstaDokumentaId.name} INTEGER NOT NULL," +
        "${Prodaja::kolicina.name} INTEGER NOT NULL," +
        "${Prodaja::korisnikId.name} INTEGER NOT NULL," +
        "${Prodaja::statusId.name} INTEGER NOT NULL," +
        "PRIMARY KEY(${Prodaja::id.name} AUTOINCREMENT)" +
        "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
        "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
        "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
        "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
        "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\")" +
        "FOREIGN KEY(\"statusId\") REFERENCES \"Status\"(\"id\")"


class ProcvatSqlHelper(context: Context?)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), ProcvatRepo {

    override fun onCreate(db: SQLiteDatabase) {
        createDatabases(db)
    }

    private fun createDatabases(db: SQLiteDatabase) {
        db.execSQL(GRUPA_CREATE_TABLE)
        db.execSQL(PARTNER_CREATE_TABLE)
        db.execSQL(SKLADISTE_CREATE_TABLE)
        db.execSQL(VRSTA_DOKUMENTA_CREATE_TABLE)
        db.execSQL(KORISNIK_CREATE_TABLE)
        db.execSQL(STATUS_CREATE_TABLE)
        db.execSQL(BOJA_CREATE_TABLE)
        db.execSQL(ARTIKL_CREATE_TABLE)
        db.execSQL(DOKUMENT_CREATE_TABLE)
        db.execSQL(NARUDZBA_CREATE_TABLE)
        db.execSQL(STANJE_SKLADISTA_CREATE_TABLE)
        db.execSQL(UNOS_STANJA_ARTIKLA_CREATE_TABLE)
        db.execSQL(PRODAJA_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Not yet implemented")
    }

    override fun insert(values: ContentValues?): Long {
        TODO("Not yet implemented")
    }

    override fun query(
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun update(
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Not yet implemented")
    }

}