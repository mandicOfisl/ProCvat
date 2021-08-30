package hr.algebra.lmandic.procvat.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.lmandic.procvat.dao.entities.*

@Database(
    entities = [
        Artikl::class,
        Boja::class,
        Dokument::class,
        Grupa::class,
        Korisnik::class,
        Narudzba::class,
        Partner::class,
        Prodaja::class,
        Skladiste::class,
        StanjeSkladista::class,
        Status::class,
        UnosStanjaArtikla::class,
        VrstaDokumenta::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ProcvatDatabase : RoomDatabase() {

    abstract val procvatDao: ProcvatDao

    companion object{
        @Volatile
        private  var INSTANCE: ProcvatDatabase? = null

        fun getInstance(context: Context): ProcvatDatabase {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProcvatDatabase::class.java,
                    "procvat_db"
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }


}