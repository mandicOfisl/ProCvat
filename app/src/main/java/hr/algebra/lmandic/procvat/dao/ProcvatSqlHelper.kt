package hr.algebra.lmandic.procvat.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.lmandic.procvat.dao.entities.*



class ProcvatSqlHelper(context: Context?)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), ProcvatRepo {

    companion object {
        const val DB_NAME = "procvat.db"
        const val DB_VERSION = 1

        const val GRUPA_TABLE_NAME = "Grupa"
        val GRUPA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $GRUPA_TABLE_NAME(" +
                "${Grupa::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Grupa::naziv.name} TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(${Grupa::_id.name} AUTOINCREMENT))"
        val GRUPA_DROP_TABLE = "DROP TABLE IF EXISTS $GRUPA_TABLE_NAME"

        const val PARTNER_TABLE_NAME = "Partner"
        val PARTNER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $PARTNER_TABLE_NAME(" +
                "${Partner::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Partner::naziv.name} TEXT NOT NULL UNIQUE," +
                "${Partner::oib.name} TEXT UNIQUE," +
                "${Partner::adresa.name} TEXT," +
                "${Partner::mjesto.name} TEXT," +
                "${Partner::postanskiBroj.name} TEXT," +
                "${Partner::brojMobitela.name} TEXT," +
                "${Partner::email.name} TEXT," +
                "${Partner::iban.name} TEXT," +
                "PRIMARY KEY(${Partner::_id.name} AUTOINCREMENT))"
        val PARTNER_DROP_TABLE = "DROP TABLE IF EXISTS $PARTNER_TABLE_NAME"

        const val SKLADISTE_TABLE_NAME = "Skladiste"
        val SKLADISTE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $SKLADISTE_TABLE_NAME(" +
                "${Skladiste::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Skladiste::naziv.name} TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(${Skladiste::_id.name} AUTOINCREMENT))"
        val SKLADISTE_DROP_TABLE = "DROP TABLE IF EXISTS $SKLADISTE_TABLE_NAME"

        const val VRSTA_DOKUMENTA_TABLE_NAME = "VrstaDokumenta"
        val VRSTA_DOKUMENTA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $VRSTA_DOKUMENTA_TABLE_NAME(" +
                "${VrstaDokumenta::_id.name} INTEGER NOT NULL UNIQUE," +
                "${VrstaDokumenta::naziv.name} TEXT NOT NULL UNIQUE," +
                "${VrstaDokumenta::izlazni.name} TEXT NOT NULL," +
                "PRIMARY KEY(${VrstaDokumenta::_id.name}))"
        val VRSTA_DOKUMENTA_DROP_TABLE = "DROP TABLE IF EXISTS $VRSTA_DOKUMENTA_TABLE_NAME"

        const val KORISNIK_TABLE_NAME = "Korisnik"
        val KORISNIK_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $KORISNIK_TABLE_NAME(" +
                "${Korisnik::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Korisnik::korisnickoIme.name} TEXT NOT NULL UNIQUE," +
                "${Korisnik::lozinka.name} TEXT NOT NULL," +
                "PRIMARY KEY(${Korisnik::_id.name} AUTOINCREMENT))"
        val KORISNIK_DROP_TABLE = "DROP TABLE IF EXISTS $KORISNIK_TABLE_NAME"

        const val STATUS_TABLE_NAME = "Status"
        val STATUS_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $STATUS_TABLE_NAME(" +
                "${Status::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Status::naziv.name} TEXT NOT NULL UNIQUE," +
                "${Status::opis.name} TEXT," +
                "PRIMARY KEY(${Status::_id.name} AUTOINCREMENT))"
        val STATUS_DROP_TABLE = "DROP TABLE IF EXISTS $STATUS_TABLE_NAME"

        const val BOJA_TABLE_NAME = "Boja"
        val BOJA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $BOJA_TABLE_NAME(" +
                "${Boja::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Boja::naziv.name} TEXT NOT NULL UNIQUE," +
                "${Boja::bojaHex.name} TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(${Boja::_id.name} AUTOINCREMENT))"
        val BOJA_DROP_TABLE = "DROP TABLE IF EXISTS $BOJA_TABLE_NAME"

        const val ARTIKL_TABLE_NAME = "Artikl"
        val ARTIKL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $ARTIKL_TABLE_NAME(" +
                "${Artikl::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Artikl::naziv.name} TEXT NOT NULL," +
                "${Artikl::grupaId.name} INTEGER NOT NULL," +
                "${Artikl::jedinicnaKolicina.name} INTEGER NOT NULL," +
                "${Artikl::kolicinaUPakiranju.name} INTEGER NOT NULL," +
                "${Artikl::bojaId.name} INTEGER," +
                "${Artikl::picturePath.name} TEXT," +
                "${Artikl::barkod.name} TEXT UNIQUE," +
                "${Artikl::statusId.name} INTEGER NOT NULL," +
                "PRIMARY KEY(${Artikl::_id.name} AUTOINCREMENT),"+
                "FOREIGN KEY(\"bojaId\") REFERENCES \"Boja\"(\"bojaId\")," +
                "FOREIGN KEY(\"grupaId\") REFERENCES \"Grupa\"(\"grupaId\"))"
        val ARTIKL_DROP_TABLE = "DROP TABLE IF EXISTS $ARTIKL_TABLE_NAME"

        const val DOKUMENT_TABLE_NAME = "Dokument"
        val DOKUMENT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $DOKUMENT_TABLE_NAME(" +
                "${Dokument::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Dokument::vrstaDokumentaId.name} INTEGER NOT NULL," +
                "${Dokument::skladisteId.name} INTEGER NOT NULL," +
                "${Dokument::datumKreiranja.name} TEXT NOT NULL," +
                "${Dokument::ukupnaKolicina.name} INTEGER NOT NULL," +
                "${Dokument::napomena.name} TEXT," +
                "${Dokument::statusId.name} INTEGER," +
                "${Dokument::partnerId.name} INTEGER," +
                "${Dokument::korisnikId.name} INTEGER NOT NULL," +
                "PRIMARY KEY(${Dokument::_id.name} AUTOINCREMENT),"+
                "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
                "FOREIGN KEY(\"partnerId\") REFERENCES \"Partner\"(\"id\")," +
                "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
                "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\"))"
        val DOKUMENT_DROP_TABLE = "DROP TABLE IF EXISTS $DOKUMENT_TABLE_NAME"

        const val NARUDZBA_TABLE_NAME = "Narudzba"
        val NARUDZBA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $NARUDZBA_TABLE_NAME(" +
                "${Narudzba::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Narudzba::dokumentId.name} INTEGER NOT NULL," +
                "${Narudzba::artiklId.name} INTEGER NOT NULL," +
                "${Narudzba::datumNarudzbe.name} TEXT NOT NULL," +
                "${Narudzba::kolicina.name} INTEGER," +
                "${Narudzba::statusId.name} INTEGER NOT NULL," +
                "${Narudzba::korisnikId.name} INTEGER NOT NULL," +
                "PRIMARY KEY(${Narudzba::_id.name} AUTOINCREMENT),"+
                "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
                "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
                "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
                "FOREIGN KEY(\"statusId\") REFERENCES \"Status\"(\"id\"))"
        val NARUDZBA_DROP_TABLE = "DROP TABLE IF EXISTS $NARUDZBA_TABLE_NAME"

        const val STANJE_SKLADISTA_TABLE_NAME = "StanjeSkladista"
        val STANJE_SKLADISTA_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $STANJE_SKLADISTA_TABLE_NAME(" +
                    "${StanjeSkladista::_id.name} INTEGER NOT NULL UNIQUE," +
                    "${StanjeSkladista::artiklId.name} INTEGER NOT NULL," +
                    "${StanjeSkladista::skladisteId.name} INTEGER NOT NULL," +
                    "${StanjeSkladista::klasa.name} INTEGER NOT NULL," +
                    "${StanjeSkladista::kolicina.name} INTEGER NOT NULL," +
                    "PRIMARY KEY(${StanjeSkladista::_id.name}),"+
                    "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
                    "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\"))"
        val STANJE_SKLADISTA_DROP_TABLE = "DROP TABLE IF EXISTS $STANJE_SKLADISTA_TABLE_NAME"

        const val UNOS_STANJA_ARTIKLA_TABLE_NAME = "UnosStanjaArtikla"
        val UNOS_STANJA_ARTIKLA_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $UNOS_STANJA_ARTIKLA_TABLE_NAME(" +
                    "${UnosStanjaArtikla::_id.name} INTEGER NOT NULL UNIQUE," +
                    "${UnosStanjaArtikla::artiklId.name} INTEGER NOT NULL," +
                    "${UnosStanjaArtikla::skladisteId.name} INTEGER NOT NULL," +
                    "${UnosStanjaArtikla::kolicina.name} INTEGER NOT NULL," +
                    "${UnosStanjaArtikla::korisnikId.name} INTEGER NOT NULL," +
                    "PRIMARY KEY(${UnosStanjaArtikla::_id.name}," +
                    "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
                    "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
                    "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
                    "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
                    "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\"))"
        val UNOS_STANJA_ARTIKLA_DROP_TABLE = "DROP TABLE IF EXISTS $UNOS_STANJA_ARTIKLA_TABLE_NAME"

        const val PRODAJA_TABLE_NAME = "Prodaja"
        val PRODAJA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $PRODAJA_TABLE_NAME(" +
                "${Prodaja::_id.name} INTEGER NOT NULL UNIQUE," +
                "${Prodaja::dokumentId.name} INTEGER NOT NULL UNIQUE," +
                "${Prodaja::artiklId.name} INTEGER NOT NULL," +
                "${Prodaja::skladisteId.name} INTEGER NOT NULL," +
                "${Prodaja::kolicina.name} INTEGER NOT NULL," +
                "${Prodaja::korisnikId.name} INTEGER NOT NULL," +
                "${Prodaja::statusId.name} INTEGER NOT NULL," +
                "PRIMARY KEY(${Prodaja::_id.name} AUTOINCREMENT)" +
                "FOREIGN KEY(\"artiklId\") REFERENCES \"Artikl\"(\"id\")," +
                "FOREIGN KEY(\"dokumentId\") REFERENCES \"Dokument\"(\"id\")," +
                "FOREIGN KEY(\"vrstaDokumentaId\") REFERENCES \"VrstaDokumenta\"(\"id\")," +
                "FOREIGN KEY(\"korisnikId\") REFERENCES \"Korisnik\"(\"id\")," +
                "FOREIGN KEY(\"skladisteId\") REFERENCES \"Skladiste\"(\"id\")" +
                "FOREIGN KEY(\"statusId\") REFERENCES \"Status\"(\"id\"))"
        val PRODAJA_DROP_TABLE = "DROP TABLE IF EXISTS $PRODAJA_TABLE_NAME"
    }

    
    override fun onCreate(db: SQLiteDatabase) {
        createTables(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dropTables(db)
        createTables(db!!)
    }

    override fun delete(tableName: String, selection: String?, selectionArgs: Array<String>?)
        = writableDatabase.delete(tableName, selection, selectionArgs)

    override fun insert(tableName: String, values: ContentValues?)
        = writableDatabase.insert(tableName, null, values)

    override fun query(
        tableName: String,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = readableDatabase.query(
        tableName,
        projection,
        selection,
        selectionArgs,
        null,
        null,
        sortOrder)

    override fun update(
        tableName: String,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ) = writableDatabase.update(tableName, values, selection, selectionArgs)

    private fun createTables(db: SQLiteDatabase) {
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


    private fun dropTables(db: SQLiteDatabase?) {
        db!!.execSQL(PRODAJA_DROP_TABLE)
        db.execSQL(UNOS_STANJA_ARTIKLA_DROP_TABLE)
        db.execSQL(STANJE_SKLADISTA_DROP_TABLE)
        db.execSQL(NARUDZBA_DROP_TABLE)
        db.execSQL(DOKUMENT_DROP_TABLE)
        db.execSQL(ARTIKL_DROP_TABLE)
        db.execSQL(BOJA_DROP_TABLE)
        db.execSQL(STATUS_DROP_TABLE)
        db.execSQL(KORISNIK_DROP_TABLE)
        db.execSQL(VRSTA_DOKUMENTA_DROP_TABLE)
        db.execSQL(SKLADISTE_DROP_TABLE)
        db.execSQL(PARTNER_DROP_TABLE)
        db.execSQL(GRUPA_DROP_TABLE)
    }

}
