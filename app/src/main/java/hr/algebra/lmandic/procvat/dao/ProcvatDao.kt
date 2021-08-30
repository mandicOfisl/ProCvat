package hr.algebra.lmandic.procvat.dao

import androidx.room.*
import hr.algebra.lmandic.procvat.dao.entities.*
import hr.algebra.lmandic.procvat.dao.entities.relations.*

@Dao
interface ProcvatDao {

    //Artikl
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtikl(artikl: Artikl): Int

    @Transaction
    @Query("SELECT * FROM Artikl")
    suspend fun getArtikli(): List<ArtiklWithGrupaAndBojaAndStatus>

    @Transaction
    @Query("SELECT * FROM Artikl WHERE _id = :id")
    suspend fun getArtikl(id: Int): ArtiklWithGrupaAndBojaAndStatus

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArtikl(artikl: Artikl): Int


    //Boja
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBoja(boja: Boja): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoja(boja: Boja): Int

    @Query("SELECT * FROM Boja")
    suspend fun getBoje(): List<Boja>

    @Query("SELECT * FROM Boja WHERE _id = :id")
    suspend fun getBoja(id: Int): Boja


    //Dokument
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDokument(dokument: Dokument): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDokument(dokument: Dokument): Int

    @Query("SELECT * FROM Dokument")
    suspend fun getDokumenti(): List<DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik>

    @Query("SELECT * FROM Dokument WHERE _id = :id")
    suspend fun getDokument(id: Int): DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik


    //Grupa
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGrupa(grupa: Grupa): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrupa(grupa: Grupa): Int

    @Query("SELECT * FROM Grupa")
    suspend fun getGrupe(): List<Grupa>

    @Query("SELECT * FROM Grupa WHERE _id = :id")
    suspend fun getGrupa(id: Int): Grupa


    //Korisnik
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateKorisnik(korisnik: Korisnik): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKorisnik(korisnik: Korisnik): Int

    @Query("SELECT * FROM Korisnik")
    suspend fun  getKorisnici(): List<Korisnik>

    @Query("SELECT * FROM Korisnik WHERE _id = :id")
    suspend fun  getKorisnik(id: Int): Korisnik

    @Query("SELECT * FROM Korisnik WHERE korisnickoIme = :name")
    suspend fun  getKorisnikByUsername(name: String): Korisnik


    //Narudzba
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNarudzba(narudzba: Narudzba): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNarudzba(narudzba: Narudzba): Int

    @Query("SELECT * FROM Narudzba")
    suspend fun getNarudzbe(): List<NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik>

    @Query("SELECT * FROM Narudzba WHERE _id = :id")
    suspend fun getNarudzba(id: Int): NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik


    //Partner
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePartner(partner: Partner): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartner(partner: Partner): Int

    @Query("SELECT * FROM Partner")
    suspend fun getPartneri(): List<Partner>

    @Query("SELECT * FROM Partner WHERE _id = :id")
    suspend fun getPartner(id: Int): Partner


    //Prodaja
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProdaja(prodaja: Prodaja): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProdaja(prodaja: Prodaja): Int

    @Query("SELECT * FROM Prodaja")
    suspend fun getProdaje(): List<ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik>

    @Query("SELECT * FROM Prodaja WHERE _id = :id")
    suspend fun getProdaja(id: Int): ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik


    //Skladiste
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSkladiste(skladiste: Skladiste): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkladiste(skladiste: Skladiste): Int

    @Query("SELECT * FROM Skladiste")
    suspend fun getSkladista(): List<Skladiste>

    @Query("SELECT * FROM Skladiste WHERE _id = :id")
    suspend fun getSkladiste(id: Int): Skladiste


    //StanjeSkladista
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStanjeSkladista(stanjeSkladista: StanjeSkladista): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStanjeSkladista(stanjeSkladista: StanjeSkladista): Int

    @Query("SELECT * FROM StanjeSkladista")
    suspend fun getStanjaSkladista(): List<StanjeSkladistaWithArtiklAndSkladiste>

    @Query("SELECT * FROM StanjeSkladista WHERE skladisteId = :id")
    suspend fun getStanjaSkladistaBySkladisteId(id: Int): List<StanjeSkladistaWithArtiklAndSkladiste>

    @Query("SELECT * FROM StanjeSkladista WHERE artiklId = :id")
    suspend fun getStanjaSkladistaByArtiklId(id: Int): List<StanjeSkladistaWithArtiklAndSkladiste>


    //Status
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStatus(status: Status): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(status: Status): Int

    @Query("SELECT * FROM Status")
    suspend fun getStatusi(): List<Status>

    @Query("SELECT * FROM Status WHERE _id = :id")
    suspend fun getStatus(id: Int): Status


    //UnosStanjaArtikla
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnosStanjaArtikla(unosStanjaArtikla: UnosStanjaArtikla): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnosStanjaArtikla(unosStanjaArtikla: UnosStanjaArtikla): Int

    @Query("SELECT * FROM UnosStanjaArtikla")
    suspend fun getUnosiStanjaArtikla(): List<UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik>

    @Query("SELECT * FROM UnosStanjaArtikla WHERE artiklId = :artiklId")
    suspend fun getUnosiStanjaArtiklaByArtikl(artiklId: Int): List<UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik>

    @Query("SELECT * FROM UnosStanjaArtikla WHERE skladisteId = :skladisteId")
    suspend fun getUnosiStanjaArtiklaBySkladiste(skladisteId: Int): List<UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik>


    //VrstaDokumenta
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVrstaDokumenta(vrstaDokumenta: VrstaDokumenta): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVrstaDokumenta(vrstaDokumenta: VrstaDokumenta): Int

    @Query("SELECT * FROM VrstaDokumenta")
    suspend fun getVrsteDokumenta(): List<VrstaDokumenta>

    @Query("SELECT * FROM VrstaDokumenta WHERE _id = :id")
    suspend fun getVrstaDokumenta(id: Int): VrstaDokumenta
}