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

    @Query("DELETE FROM Artikl")
    suspend fun deleteArtikli(): Int

    @Query("DELETE FROM Artikl WHERE _id = :artiklId")
    suspend fun deleteArtikl(artiklId: Int): Int


    //Boja
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBoja(boja: Boja): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoja(boja: Boja): Int

    @Query("SELECT * FROM Boja")
    suspend fun getBoje(): List<Boja>

    @Query("SELECT * FROM Boja WHERE _id = :id")
    suspend fun getBoja(id: Int): Boja

    @Query("DELETE FROM Boja")
    suspend fun deleteBoje(): Int

    @Query("DELETE FROM Boja WHERE _id = :bojaId")
    suspend fun deleteBoja(bojaId: Int): Int

    
    //Dokument
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDokument(dokument: Dokument): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDokument(dokument: Dokument): Int

    @Query("SELECT * FROM Dokument")
    suspend fun getDokumenti(): List<DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik>

    @Query("SELECT * FROM Dokument WHERE _id = :id")
    suspend fun getDokument(id: Int): DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik

    @Query("DELETE FROM Dokument")
    suspend fun deleteDokumenti(): Int

    @Query("DELETE FROM Dokument WHERE _id = :dokumentId")
    suspend fun deleteDokument(dokumentId: Int): Int

    
    //Grupa
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGrupa(grupa: Grupa): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrupa(grupa: Grupa): Int

    @Query("SELECT * FROM Grupa")
    suspend fun getGrupe(): List<Grupa>

    @Query("SELECT * FROM Grupa WHERE _id = :id")
    suspend fun getGrupa(id: Int): Grupa

    @Query("DELETE FROM Grupa")
    suspend fun deleteGrupe(): Int

    @Query("DELETE FROM Grupa WHERE _id = :grupaId")
    suspend fun deleteGrupa(grupaId: Int): Int


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

    @Query("DELETE FROM Korisnik")
    suspend fun deleteKorisnici(): Int

    @Query("DELETE FROM Korisnik WHERE _id = :korisnikId")
    suspend fun deleteKorisnik(korisnikId: Int): Int


    //Narudzba
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNarudzba(narudzba: Narudzba): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNarudzba(narudzba: Narudzba): Int

    @Query("SELECT * FROM Narudzba")
    suspend fun getNarudzbe(): List<NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik>

    @Query("SELECT * FROM Narudzba WHERE _id = :id")
    suspend fun getNarudzba(id: Int): NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik

    @Query("DELETE FROM Narudzba")
    suspend fun deleteNarudzbe(): Int

    @Query("DELETE FROM Narudzba WHERE _id = :narudzbaId")
    suspend fun deleteNarudzba(narudzbaId: Int): Int

    
    //Partner
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePartner(partner: Partner): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartner(partner: Partner): Int

    @Query("SELECT * FROM Partner")
    suspend fun getPartneri(): List<Partner>

    @Query("SELECT * FROM Partner WHERE _id = :id")
    suspend fun getPartner(id: Int): Partner

    @Query("DELETE FROM Partner")
    suspend fun deletePartneri(): Int

    @Query("DELETE FROM Partner WHERE _id = :partnerId")
    suspend fun deletePartner(partnerId: Int): Int

    
    //Prodaja
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProdaja(prodaja: Prodaja): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProdaja(prodaja: Prodaja): Int

    @Query("SELECT * FROM Prodaja")
    suspend fun getProdaje(): List<ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik>

    @Query("SELECT * FROM Prodaja WHERE _id = :id")
    suspend fun getProdaja(id: Int): ProdajaWithDokumentAndArtiklAndSkladisteAndStatusAndKorisnik

    @Query("DELETE FROM Prodaja")
    suspend fun deleteProdaje(): Int

    @Query("DELETE FROM Prodaja WHERE _id = :prodajaId")
    suspend fun deleteProdaja(prodajaId: Int): Int
    

    //Skladiste
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSkladiste(skladiste: Skladiste): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkladiste(skladiste: Skladiste): Int

    @Query("SELECT * FROM Skladiste")
    suspend fun getSkladista(): List<Skladiste>

    @Query("SELECT * FROM Skladiste WHERE _id = :id")
    suspend fun getSkladiste(id: Int): Skladiste

    @Query("DELETE FROM Skladiste")
    suspend fun deleteSkladista(): Int

    @Query("DELETE FROM Skladiste WHERE _id = :skladisteId")
    suspend fun deleteSkladiste(skladisteId: Int): Int


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

    @Query("DELETE FROM StanjeSkladista")
    suspend fun deleteStanjaSkladista(): Int

    @Query("DELETE FROM StanjeSkladista WHERE _id = :stanjeSkladistaId")
    suspend fun deleteStanjeSkladista(stanjeSkladistaId: Int): Int

    
    //Status
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStatus(status: Status): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(status: Status): Int

    @Query("SELECT * FROM Status")
    suspend fun getStatusi(): List<Status>

    @Query("SELECT * FROM Status WHERE _id = :id")
    suspend fun getStatus(id: Int): Status

    @Query("DELETE FROM Status")
    suspend fun deleteStatusi(): Int

    @Query("DELETE FROM Status WHERE _id = :statusId")
    suspend fun deleteStatus(statusId: Int): Int
    

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

    @Query("DELETE FROM UnosStanjaArtikla")
    suspend fun deleteUnosiStanjaArtikla(): Int

    @Query("DELETE FROM UnosStanjaArtikla WHERE _id = :unosStanjaArtiklaId")
    suspend fun deleteUnosStanjaArtikla(unosStanjaArtiklaId: Int): Int
    

    //VrstaDokumenta
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVrstaDokumenta(vrstaDokumenta: VrstaDokumenta): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVrstaDokumenta(vrstaDokumenta: VrstaDokumenta): Int

    @Query("SELECT * FROM VrstaDokumenta")
    suspend fun getVrsteDokumenta(): List<VrstaDokumenta>

    @Query("SELECT * FROM VrstaDokumenta WHERE _id = :id")
    suspend fun getVrstaDokumenta(id: Int): VrstaDokumenta

    @Query("DELETE FROM VrstaDokumenta")
    suspend fun deleteVrsteDokumenta(): Int

    @Query("DELETE FROM VrstaDokumenta WHERE _id = :vrstaDokumentaId")
    suspend fun deleteVrstaDokumenta(vrstaDokumentaId: Int): Int
    
    
}