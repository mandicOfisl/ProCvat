package hr.algebra.lmandic.procvat

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import hr.algebra.lmandic.procvat.dao.ProcvatDao
import hr.algebra.lmandic.procvat.dao.ProcvatDatabase
import hr.algebra.lmandic.procvat.dao.entities.*
import hr.algebra.lmandic.procvat.dao.entities.relations.*
import kotlinx.coroutines.runBlocking

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

    private lateinit var procvatDao: ProcvatDao

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var result: Int = -1
        
        when(URI_MATCHER.match(uri)){
            ARTIKLI -> {
                runBlocking { 
                    result = procvatDao.deleteArtikli()
                }
            }
            ARTIKLI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteArtikl(id.toInt())
                    }
            }
            BOJE -> {
                runBlocking {
                    result = procvatDao.deleteBoje()
                }
            }
            BOJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteBoja(id.toInt())
                    }
            }
            DOKUMENTI -> {
                runBlocking {
                    result = procvatDao.deleteDokumenti()
                }
            }
            DOKUMENTI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteDokument(id.toInt())
                    }
            }
            GRUPE -> {
                runBlocking {
                    result = procvatDao.deleteGrupe()
                }
            }
            GRUPE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteGrupa(id.toInt())
                    }
            }
            KORISNICI -> {
                runBlocking {
                    result = procvatDao.deleteKorisnici()
                }
            }
            KORISNICI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteKorisnik(id.toInt())
                    }
            }
            NARUDZBE -> {
                runBlocking {
                    result = procvatDao.deleteNarudzbe()
                }
            }
            NARUDZBE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteNarudzba(id.toInt())
                    }
            }
            PARTNERI -> {
                runBlocking {
                    result = procvatDao.deletePartneri()
                }
            }
            PARTNERI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deletePartner(id.toInt())
                    }
            }
            PRODAJE -> {
                runBlocking {
                    result = procvatDao.deleteProdaje()
                }
            }
            PRODAJE_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteProdaja(id.toInt())
                    }
            }
            SKLADISTA -> {
                runBlocking {
                    result = procvatDao.deleteSkladista()
                }
            }
            SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteSkladiste(id.toInt())
                    }
            }
            STANJA_SKLADISTA -> {
                runBlocking {
                    result = procvatDao.deleteStanjaSkladista()
                }
            }
            STANJA_SKLADISTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteStanjeSkladista(id.toInt())
                    }
            }
            STATUSI -> {
                runBlocking {
                    result = procvatDao.deleteStatusi()
                }
            }
            STATUSI_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteStatus(id.toInt())
                    }
            }
            UNOSI_STANJA_ARTIKLA -> {
                runBlocking {
                    result = procvatDao.deleteUnosiStanjaArtikla()
                }
            }
            UNOSI_STANJA_ARTIKLA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteUnosStanjaArtikla(id.toInt())
                    }
            }
            VRSTE_DOKUMENTA -> {
                runBlocking {
                    result = procvatDao.deleteVrsteDokumenta()
                }
            }
            VRSTE_DOKUMENTA_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                    runBlocking {
                        result = procvatDao.deleteVrstaDokumenta(id.toInt())
                    }
            }
            else -> throw IllegalArgumentException("Wrong URI!")
        }

        return result
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
        val contentUri: Uri
        val result: Int

        when(URI_MATCHER.match(uri)){
            ARTIKLI -> {
                contentUri = ARTIKLI_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertArtikl(Artikl.fromContentValues(values!!)).toInt()
                }
            }
            BOJE -> {
                contentUri = BOJE_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertBoja(Boja.fromContentValues(values!!)).toInt()
                }
            }
            DOKUMENTI -> {
                contentUri = DOKUMENTI_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertDokument(Dokument.fromContentValues(values!!)).toInt()
                }
            }
            GRUPE -> {
                contentUri = GRUPE_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertGrupa(Grupa.fromContentValues(values!!)).toInt()
                }
            }
            KORISNICI -> {
                contentUri = KORISNICI_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertKorisnik(Korisnik.fromContentValues(values!!)).toInt()
                }
            }
            NARUDZBE -> {
                contentUri = NARUDZBE_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertNarudzba(Narudzba.fromContentValues(values!!)).toInt()
                }
            }
            PARTNERI -> {
                contentUri = PARTNERI_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertPartner(Partner.fromContentValues(values!!)).toInt()
                }
            }
            PRODAJE -> {
                contentUri = PRODAJE_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertProdaja(Prodaja.fromContentValues(values!!)).toInt()
                }
            }
            SKLADISTA -> {
                contentUri = SKLADISTA_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertSkladiste(Skladiste.fromContentValues(values!!)).toInt()
                }
            }
            STANJA_SKLADISTA -> {
                contentUri = STANJA_SKLADISTA_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertStanjeSkladista(StanjeSkladista.fromContentValues(values!!)).toInt()
                }
            }
            STATUSI -> {
                contentUri = STATUSI_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertStatus(Status.fromContentValues(values!!)).toInt()
                }
            }
            UNOSI_STANJA_ARTIKLA -> {
                contentUri = UNOSI_STANJA_ARTIKALA_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertUnosStanjaArtikla(UnosStanjaArtikla.fromContentValues(values!!)).toInt()
                }
            }
            VRSTE_DOKUMENTA -> {
                contentUri = VRSTE_DOKUMENTA_PROVIDER_CONTENT_URI
                runBlocking {
                    result = procvatDao.insertVrstaDokumenta(VrstaDokumenta.fromContentValues(values!!)).toInt()
                }
            }
            else -> throw IllegalArgumentException("Wrong uri!")
        }

        return ContentUris.withAppendedId(contentUri, result.toLong())
    }

    override fun onCreate(): Boolean {
        procvatDao = ProcvatDatabase.getInstance(context!!).procvatDao
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        val cursor: MatrixCursor

        when(URI_MATCHER.match(uri)){
            ARTIKLI -> {
                val artikls: List<ArtiklWithGrupaAndBojaAndStatus>
                cursor = MatrixCursor(
                    arrayOf(
                        Artikl::_id.name,
                        Artikl::naziv.name,
                        Artikl::grupaId.name,
                        "grupaNaziv",
                        Artikl::jedinicnaKolicina.name,
                        Artikl::kolicinaUPakiranju.name,
                        Artikl::bojaId.name,
                        "bojaHex",
                        "bojaNaziv",
                        Artikl::picturePath.name,
                        Artikl::barkod.name,
                        Artikl::statusId.name,
                        "statusNaziv",
                        "statusOpis"
                    ))

                runBlocking {
                    artikls =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getArtikli()
                        else
                            arrayOf(procvatDao.getArtikl(selectionArgs.first().toInt())).toList()
                }

                for (artikl: ArtiklWithGrupaAndBojaAndStatus in artikls){
                    cursor.newRow()
                        .add(Artikl::_id.name, artikl.artikl._id)
                        .add(Artikl::naziv.name, artikl.artikl.naziv)
                        .add(Artikl::grupaId.name, artikl.artikl.grupaId)
                        .add("grupaNaziv", artikl.grupa.naziv)
                        .add(Artikl::jedinicnaKolicina.name, artikl.artikl.jedinicnaKolicina)
                        .add(Artikl::kolicinaUPakiranju.name, artikl.artikl.kolicinaUPakiranju)
                        .add(Artikl::bojaId.name, artikl.artikl.bojaId)
                        .add("bojaHex", artikl.boja.bojaHex)
                        .add("bojaNaziv", artikl.boja.naziv)
                        .add(Artikl::picturePath.name, artikl.artikl.picturePath)
                        .add(Artikl::barkod.name, artikl.artikl.barkod)
                        .add(Artikl::statusId.name, artikl.artikl.statusId)
                        .add("statusNaziv", artikl.status.naziv)
                        .add("statusOpis", artikl.status.opis)
                }

                return cursor
            }
            BOJE -> {
                val boje: List<Boja>
                cursor = MatrixCursor(
                    arrayOf(
                        Boja::_id.name,
                        Boja::bojaHex.name,
                        Boja::naziv.name
                    )
                )

                runBlocking {
                    boje =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getBoje()
                        else
                            arrayOf(procvatDao.getBoja(selectionArgs.first().toInt())).toList()

                }

                for (boja: Boja in boje) {
                    cursor.newRow()
                        .add(Boja::_id.name, boja._id)
                        .add(Boja::bojaHex.name, boja.bojaHex)
                        .add(Boja::naziv.name, boja.naziv)
                }

                return cursor
            }
            DOKUMENTI -> {
                val dokumenti: List<DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik>
                cursor = MatrixCursor(
                    arrayOf(
                        Dokument::_id.name,
                        Dokument::vrstaDokumentaId.name,
                        "vrstaDokumentaNaziv",
                        "vrstaDokumentaIzlazni",
                        Dokument::skladisteId.name,
                        "skladisteNaziv",
                        Dokument::datumKreiranja.name,
                        Dokument::ukupnaKolicina.name,
                        Dokument::napomena.name,
                        Dokument::statusId.name,
                        "statusNaziv",
                        "statusOpis",
                        Dokument::partnerId.name,
                        "partnerNaziv",
                        "partnerOib",
                        "partnerAdresa",
                        "partnerMjesto",
                        "partnerPostanskiBroj",
                        "partnerBrojMobitela",
                        "partnerEmail",
                        "partnerIban",
                        Dokument::korisnikId.name,
                        "korisnikIme",
                        "korisnikLozinka"
                    )
                )

                runBlocking {
                    dokumenti =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getDokumenti()
                        else
                            arrayOf(procvatDao.getDokument(selectionArgs.first().toInt())).toList()
                }

                for (dokument: DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik in dokumenti) {
                    cursor.newRow()
                        .add(Dokument::_id.name, dokument.dokument._id)
                        .add(Dokument::vrstaDokumentaId.name, dokument.dokument.vrstaDokumentaId)
                        .add("vrstaDokumentaNaziv", dokument.vrstaDokumenta.naziv)
                        .add("vrstaDokumentaIzlazni", dokument.vrstaDokumenta.izlazni)
                        .add(Dokument::skladisteId.name, dokument.dokument.skladisteId)
                        .add("skladisteNaziv", dokument.skladiste.naziv)
                        .add(Dokument::datumKreiranja.name, dokument.dokument.datumKreiranja)
                        .add(Dokument::ukupnaKolicina.name, dokument.dokument.ukupnaKolicina)
                        .add(Dokument::napomena.name, dokument.dokument.napomena)
                        .add(Dokument::statusId.name, dokument.dokument.statusId)
                        .add("statusNaziv", dokument.status.naziv)
                        .add("statusOpis", dokument.status.opis)
                        .add(Dokument::partnerId.name, dokument.dokument.partnerId)
                        .add("partnerNaziv", dokument.partner.naziv)
                        .add("partnerOib", dokument.partner.oib)
                        .add("partnerAdresa", dokument.partner.adresa)
                        .add("partnerMjesto", dokument.partner.mjesto)
                        .add("partnerPostanskiBroj", dokument.partner.postanskiBroj)
                        .add("partnerBrojMobitela", dokument.partner.brojMobitela)
                        .add("partnerEmail", dokument.partner.email)
                        .add("partnerIban", dokument.partner.iban)
                        .add(Dokument::korisnikId.name, dokument.dokument.korisnikId)
                        .add("korisnikIme", dokument.korisnik.korisnickoIme)
                        .add("korisnikLozinka", dokument.korisnik.lozinka)
                }

                return cursor
            }
            GRUPE -> {
                val grupe: List<Grupa>
                cursor = MatrixCursor(
                    arrayOf(
                        Grupa::_id.name,
                        Grupa::naziv.name,
                    )
                )

                runBlocking {
                    grupe =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getGrupe()
                        else
                            arrayOf(procvatDao.getGrupa(selectionArgs.first().toInt())).toList()

                }

                for (grupa: Grupa in grupe) {
                    cursor.newRow()
                        .add(Grupa::_id.name, grupa._id)
                        .add(Grupa::naziv.name, grupa.naziv)
                }

                return cursor
            }
            KORISNICI -> {
                val korisnici: List<Korisnik>
                cursor = MatrixCursor(
                    arrayOf(
                        Korisnik::_id.name,
                        Korisnik::korisnickoIme.name,
                        Korisnik::lozinka.name
                    )
                )

                runBlocking {
                    korisnici =
                        when {
                            selectionArgs.isNullOrEmpty() -> {
                                procvatDao.getKorisnici()
                            }
                            selectionArgs.first().toIntOrNull() == null -> {
                                arrayOf(procvatDao.getKorisnikByUsername(selectionArgs.first())).toList()
                            }
                            else -> {
                                arrayOf(procvatDao.getKorisnik(selectionArgs.first().toInt())).toList()
                            }
                        }

                }

                for (korisnik: Korisnik in korisnici) {
                    cursor.newRow()
                        .add(Korisnik::_id.name, korisnik._id)
                        .add(Korisnik::korisnickoIme.name, korisnik.korisnickoIme)
                        .add(Korisnik::lozinka.name, korisnik.lozinka)
                }

                return cursor
            }
            NARUDZBE -> {
                val narudzbe: List<NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik>
                cursor = MatrixCursor(
                    arrayOf(
                        Narudzba::_id.name,
                        Narudzba::dokumentId.name,                        
                        Narudzba::artiklId.name,
                        Artikl::naziv.name,
                        Artikl::grupaId.name,
                        Artikl::jedinicnaKolicina.name,
                        Artikl::kolicinaUPakiranju.name,
                        Artikl::bojaId.name,
                        Artikl::picturePath.name,
                        Artikl::barkod.name,
                        "artiklStatusId",
                        Narudzba::datumNarudzbe.name,
                        Narudzba::kolicina.name,
                        Narudzba::statusId.name,
                        "statusNaziv",
                        "statusOpis",
                        Narudzba::korisnikId.name,
                        "korisnikIme",
                        "korisnikLozinka"
                    )
                )

                runBlocking {
                    narudzbe =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getNarudzbe()
                        else
                            arrayOf(procvatDao.getNarudzba(selectionArgs.first().toInt())).toList()

                }

                for (narudzba: NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik in narudzbe) {
                    cursor.newRow()
                        .add(Narudzba::_id.name, narudzba.narudzba._id)
                        .add(Narudzba::dokumentId.name, narudzba.narudzba.dokumentId)
                        .add(Narudzba::artiklId.name, narudzba.narudzba.artiklId)
                        .add(Artikl::naziv.name, narudzba.artikl.naziv)
                        .add(Artikl::grupaId.name, narudzba.artikl.grupaId)                        
                        .add(Artikl::jedinicnaKolicina.name, narudzba.artikl.jedinicnaKolicina)
                        .add(Artikl::kolicinaUPakiranju.name, narudzba.artikl.kolicinaUPakiranju)
                        .add(Artikl::bojaId.name, narudzba.artikl.bojaId)
                        .add(Artikl::picturePath.name, narudzba.artikl.picturePath)
                        .add(Artikl::barkod.name, narudzba.artikl.barkod)
                        .add("artiklStatusId", narudzba.artikl.statusId)
                        .add(Narudzba::datumNarudzbe.name, narudzba.narudzba.datumNarudzbe)
                        .add(Narudzba::kolicina.name, narudzba.narudzba.kolicina)
                        .add(Narudzba::statusId.name, narudzba.narudzba.statusId)
                        .add("statusNaziv", narudzba.status.naziv)
                        .add("statusOpis", narudzba.status.opis)
                        .add(Narudzba::korisnikId.name, narudzba.narudzba.korisnikId)
                        .add("korisnikIme", narudzba.korisnik.korisnickoIme)
                        .add("korisnikLozinka", narudzba.korisnik.lozinka)
                }

                return cursor
            }
            PARTNERI -> {
                val partneri: List<Partner>
                cursor = MatrixCursor(
                    arrayOf(
                        Partner::_id.name,
                        Partner::naziv.name,
                        Partner::oib.name,
                        Partner::adresa.name,
                        Partner::mjesto.name,
                        Partner::postanskiBroj.name,
                        Partner::brojMobitela.name,
                        Partner::email.name,
                        Partner::iban.name                        
                    )
                )

                runBlocking {
                    partneri =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getPartneri()
                        else
                            arrayOf(procvatDao.getPartner(selectionArgs.first().toInt())).toList()

                }

                for (partner: Partner in partneri) {
                    cursor.newRow()
                        .add(Partner::_id.name, partner._id)
                        .add(Partner::naziv.name, partner.naziv)
                        .add(Partner::oib.name, partner.oib)
                        .add(Partner::adresa.name, partner.adresa)
                        .add(Partner::mjesto.name, partner.mjesto)
                        .add(Partner::postanskiBroj.name, partner.postanskiBroj)
                        .add(Partner::brojMobitela.name, partner.brojMobitela)
                        .add(Partner::email.name, partner.email)
                        .add(Partner::iban.name, partner.iban)
                }

                return cursor
            }
            PRODAJE -> {
                val prodaje: List<ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik>
                cursor = MatrixCursor(
                    arrayOf(
                        Prodaja::_id.name,
                        Prodaja::dokumentId.name,
                        Prodaja::artiklId.name,
                        Artikl::naziv.name,
                        Artikl::grupaId.name,
                        Artikl::jedinicnaKolicina.name,
                        Artikl::kolicinaUPakiranju.name,
                        Artikl::bojaId.name,
                        Artikl::picturePath.name,
                        Artikl::barkod.name,
                        "artiklStatusId",
                        Prodaja::skladisteId.name,
                        "skladisteNaziv",
                        Prodaja::kolicina.name,
                        Prodaja::statusId.name,
                        "statusNaziv",
                        "statusOpis",
                        Prodaja::korisnikId.name,
                        "korisnikIme",
                        "korisnikLozinka"
                    )
                )

                runBlocking {
                    prodaje =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getProdaje()
                        else
                            arrayOf(procvatDao.getProdaja(selectionArgs.first().toInt())).toList()

                }

                for (prodaja: ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik in prodaje) {
                    cursor.newRow()
                        .add(Prodaja::_id.name, prodaja.prodaja._id)
                        .add(Prodaja::dokumentId.name, prodaja.prodaja.dokumentId)
                        .add(Prodaja::artiklId.name, prodaja.prodaja.artiklId)
                        .add(Artikl::naziv.name, prodaja.artikl.naziv)
                        .add(Artikl::grupaId.name, prodaja.artikl.grupaId)
                        .add(Artikl::jedinicnaKolicina.name, prodaja.artikl.jedinicnaKolicina)
                        .add(Artikl::kolicinaUPakiranju.name, prodaja.artikl.kolicinaUPakiranju)
                        .add(Artikl::bojaId.name, prodaja.artikl.bojaId)
                        .add(Artikl::picturePath.name, prodaja.artikl.picturePath)
                        .add(Artikl::barkod.name, prodaja.artikl.barkod)
                        .add("artiklStatusId", prodaja.artikl.statusId)
                        .add(Prodaja::skladisteId.name, prodaja.prodaja.skladisteId)
                        .add("skladisteNaziv", prodaja.skladiste.naziv)
                        .add(Prodaja::kolicina.name, prodaja.prodaja.kolicina)
                        .add("statusNaziv", prodaja.status.naziv)
                        .add("statusOpis", prodaja.status.opis)
                        .add(Prodaja::korisnikId.name, prodaja.prodaja.korisnikId)
                        .add("korisnikIme", prodaja.korisnik.korisnickoIme)
                        .add("korisnikLozinka", prodaja.korisnik.lozinka)
                }

                return cursor
            }
            SKLADISTA -> {
                val skladista: List<Skladiste>
                cursor = MatrixCursor(
                    arrayOf(
                        Skladiste::_id.name,
                        Skladiste::naziv.name
                    )
                )

                runBlocking {
                    skladista =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getSkladista()
                        else
                            arrayOf(procvatDao.getSkladiste(selectionArgs.first().toInt())).toList()

                }

                for (skladiste: Skladiste in skladista) {
                    cursor.newRow()
                        .add(Skladiste::_id.name, skladiste._id)
                        .add(Skladiste::naziv.name, skladiste.naziv)
                }

                return cursor
            }
            STANJA_SKLADISTA -> {
                val stanjaSkladista: List<StanjeSkladistaWithArtiklAndSkladiste>
                cursor = MatrixCursor(
                    arrayOf(
                        StanjeSkladista::_id.name,
                        StanjeSkladista::artiklId.name,
                        Artikl::naziv.name,
                        Artikl::grupaId.name,
                        Artikl::jedinicnaKolicina.name,
                        Artikl::kolicinaUPakiranju.name,
                        Artikl::bojaId.name,
                        Artikl::picturePath.name,
                        Artikl::barkod.name,
                        "artiklStatusId",
                        StanjeSkladista::skladisteId.name,
                        "skladisteNaziv",
                        StanjeSkladista::klasa.name,
                        StanjeSkladista::kolicina.name
                    )
                )

                runBlocking {
                    stanjaSkladista =
                        when {
                            selectionArgs.isNullOrEmpty() -> {
                                procvatDao.getStanjaSkladista()
                            }
                            selection == StanjeSkladista::skladisteId.name -> {
                                procvatDao.getStanjaSkladistaBySkladisteId(selectionArgs.first().toInt())
                            }
                            selection == StanjeSkladista::artiklId.name -> {
                                procvatDao.getStanjaSkladistaByArtiklId(selectionArgs.first().toInt())
                            }
                            else -> throw IllegalArgumentException("Wrong uri!")
                        }
                }

                for (stanje: StanjeSkladistaWithArtiklAndSkladiste in stanjaSkladista) {
                    cursor.newRow()
                        .add(StanjeSkladista::_id.name, stanje.stanjeSkladista._id)
                        .add(StanjeSkladista::artiklId.name, stanje.stanjeSkladista.artiklId)
                        .add(Artikl::naziv.name, stanje.artikl.naziv)
                        .add(Artikl::grupaId.name, stanje.artikl.grupaId)
                        .add(Artikl::jedinicnaKolicina.name, stanje.artikl.jedinicnaKolicina)
                        .add(Artikl::kolicinaUPakiranju.name, stanje.artikl.kolicinaUPakiranju)
                        .add(Artikl::bojaId.name, stanje.artikl.bojaId)
                        .add(Artikl::picturePath.name, stanje.artikl.picturePath)
                        .add(Artikl::barkod.name, stanje.artikl.barkod)
                        .add("artiklStatusId", stanje.artikl.statusId)
                        .add(StanjeSkladista::skladisteId.name, stanje.stanjeSkladista.skladisteId)
                        .add("skladisteNaziv", stanje.skladiste.naziv)
                        .add(StanjeSkladista::klasa.name, stanje.stanjeSkladista.klasa)
                        .add(StanjeSkladista::kolicina.name, stanje.stanjeSkladista.kolicina)
                }

                return cursor
            }
            STATUSI -> {
                val statusi: List<Status>
                cursor = MatrixCursor(
                    arrayOf(
                        Status::_id.name,
                        Status::naziv.name,
                        Status::opis.name
                    )
                )

                runBlocking {
                    statusi =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getStatusi()
                        else
                            arrayOf(procvatDao.getStatus(selectionArgs.first().toInt())).toList()
                }

                for (status: Status in statusi) {
                    cursor.newRow()
                        .add(Status::_id.name, status._id)
                        .add(Status::naziv.name, status.naziv)
                        .add(Status::opis.name, status.opis)
                }

                return cursor
            }
            UNOSI_STANJA_ARTIKLA -> {
                val unosiStanja: List<UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik>
                cursor = MatrixCursor(
                    arrayOf(
                        UnosStanjaArtikla::_id.name,
                        UnosStanjaArtikla::datumUnosa.name,
                        UnosStanjaArtikla::artiklId.name,
                        Artikl::naziv.name,
                        Artikl::grupaId.name,
                        Artikl::jedinicnaKolicina.name,
                        Artikl::kolicinaUPakiranju.name,
                        Artikl::bojaId.name,
                        Artikl::picturePath.name,
                        Artikl::barkod.name,
                        "artiklStatusId",
                        UnosStanjaArtikla::skladisteId.name,
                        "skladisteNaziv",
                        UnosStanjaArtikla::kolicina.name,
                        UnosStanjaArtikla::korisnikId.name,
                        "korisnikIme",
                        "korisnikLozinka"
                    )
                )

                runBlocking {
                    unosiStanja =
                        when {
                            selectionArgs.isNullOrEmpty() -> {
                                procvatDao.getUnosiStanjaArtikla()
                            }
                            selection == UnosStanjaArtikla::artiklId.name -> {
                                procvatDao.getUnosiStanjaArtiklaByArtikl(selectionArgs.first().toInt())
                            }
                            selection == UnosStanjaArtikla::skladisteId.name -> {
                                procvatDao.getUnosiStanjaArtiklaBySkladiste(selectionArgs.first().toInt())
                            }
                            else -> throw IllegalArgumentException("Wrong uri!")
                        }
                }

                for (unos: UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik in unosiStanja) {
                    cursor.newRow()
                        .add(UnosStanjaArtikla::_id.name, unos.unosStanjaArtikla._id)
                        .add(UnosStanjaArtikla::datumUnosa.name, unos.unosStanjaArtikla.datumUnosa)
                        .add(UnosStanjaArtikla::artiklId.name, unos.unosStanjaArtikla.artiklId)
                        .add(Artikl::naziv.name, unos.artikl.naziv)
                        .add(Artikl::grupaId.name, unos.artikl.grupaId)
                        .add(Artikl::jedinicnaKolicina.name, unos.artikl.jedinicnaKolicina)
                        .add(Artikl::kolicinaUPakiranju.name, unos.artikl.kolicinaUPakiranju)
                        .add(Artikl::bojaId.name, unos.artikl.bojaId)
                        .add(Artikl::picturePath.name, unos.artikl.picturePath)
                        .add(Artikl::barkod.name, unos.artikl.barkod)
                        .add("artiklStatusId", unos.artikl.statusId)
                        .add(UnosStanjaArtikla::skladisteId.name, unos.unosStanjaArtikla.skladisteId)
                        .add("skladisteNaziv", unos.skladiste.naziv)
                        .add(UnosStanjaArtikla::kolicina.name, unos.unosStanjaArtikla.kolicina)
                        .add(UnosStanjaArtikla::korisnikId.name, unos.unosStanjaArtikla.korisnikId)
                        .add("korisnikIme", unos.korisnik.korisnickoIme)
                        .add("korisnikLozinka", unos.korisnik.lozinka)
                    
                }

                return cursor
            }
            VRSTE_DOKUMENTA -> {
                val vrste: List<VrstaDokumenta>
                cursor = MatrixCursor(
                    arrayOf(
                        VrstaDokumenta::_id.name,
                        VrstaDokumenta::naziv.name,
                        VrstaDokumenta::izlazni.name
                    )
                )

                runBlocking {
                    vrste =
                        if (selectionArgs.isNullOrEmpty())
                            procvatDao.getVrsteDokumenta()
                        else
                            arrayOf(procvatDao.getVrstaDokumenta(selectionArgs.first().toInt())).toList()
                }

                for (vrsta: VrstaDokumenta in vrste) {
                    cursor.newRow()
                        .add(VrstaDokumenta::_id.name, vrsta._id)
                        .add(VrstaDokumenta::naziv.name, vrsta.naziv)
                        .add(VrstaDokumenta::izlazni.name, vrsta.izlazni)
                }

                return cursor
            }
            else -> throw IllegalArgumentException("Wrong uri!")
        }

    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val result: Int
        when(URI_MATCHER.match(uri)){
            ARTIKLI_ID -> {
                runBlocking {
                    result = procvatDao.updateArtikl(Artikl.fromContentValues(values!!))
                }
            }
            BOJE_ID -> {
                runBlocking {
                    result = procvatDao.updateBoja(Boja.fromContentValues(values!!))
                }
            }
            DOKUMENTI_ID -> {
                runBlocking {
                    result = procvatDao.updateDokument(Dokument.fromContentValues(values!!))
                }
            }
            GRUPE_ID -> {
                runBlocking {
                    result = procvatDao.updateGrupa(Grupa.fromContentValues(values!!))
                }
            }
            KORISNICI_ID -> {
                runBlocking {
                    result = procvatDao.updateKorisnik(Korisnik.fromContentValues(values!!))
                }
            }
            NARUDZBE_ID -> {
                runBlocking {
                    result = procvatDao.updateNarudzba(Narudzba.fromContentValues(values!!))
                }
            }
            PARTNERI_ID -> {
                runBlocking {
                    result = procvatDao.updatePartner(Partner.fromContentValues(values!!))
                }
            }
            PRODAJE_ID -> {
                runBlocking {
                    result = procvatDao.updateProdaja(Prodaja.fromContentValues(values!!))
                }
            }
            SKLADISTA_ID -> {
                runBlocking {
                    result = procvatDao.updateSkladiste(Skladiste.fromContentValues(values!!))
                }
            }
            STANJA_SKLADISTA_ID -> {
                runBlocking {
                    result = procvatDao.updateStanjeSkladista(StanjeSkladista.fromContentValues(values!!))
                }
            }
            STATUSI_ID -> {
                runBlocking {
                    result = procvatDao.updateStatus(Status.fromContentValues(values!!))
                }
            }
            UNOSI_STANJA_ARTIKLA_ID -> {
                runBlocking {
                    result = procvatDao.updateUnosStanjaArtikla(UnosStanjaArtikla.fromContentValues(values!!))
                }
            }
            VRSTE_DOKUMENTA_ID -> {
                runBlocking {
                    result = procvatDao.updateVrstaDokumenta(VrstaDokumenta.fromContentValues(values!!))
                }
            }
            else -> throw IllegalArgumentException("Wrong URI!")
        }
        return result
    }
}