package hr.algebra.lmandic.procvat.dao

import androidx.room.*
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import hr.algebra.lmandic.procvat.dao.entities.relations.ArtiklWithGrupaAndBojaAndStatus

@Dao
interface ProcvatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtikl(artikl: Artikl)

    @Transaction
    @Query("SELECT * FROM Artikl")
    suspend fun getArtikli(): List<ArtiklWithGrupaAndBojaAndStatus>



}