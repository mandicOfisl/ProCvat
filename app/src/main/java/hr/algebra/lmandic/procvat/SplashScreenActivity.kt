package hr.algebra.lmandic.procvat

import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.dao.entities.*
import hr.algebra.lmandic.procvat.databinding.ActivitySplashScreenBinding
import hr.algebra.lmandic.procvat.framework.applyAnimation
import hr.algebra.lmandic.procvat.framework.startActivity

private const val DELAY : Long = 3000

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.ivSplash.applyAnimation(R.anim.scale)
        binding.ivSplash.applyAnimation(R.anim.blink)
    }

    private fun redirect() {

        if (checkForUsers())
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<LoginActivity>()},
                DELAY
            )
        else
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<NewUserActivity>()},
                DELAY
            )
    }

    private fun checkForUsers(): Boolean {
        val korisnikCursor = contentResolver.query(
            KORISNICI_PROVIDER_CONTENT_URI,
            null,
            null,
            null,
            null,
        )

        var id = -1

        if (korisnikCursor != null){
            while (korisnikCursor.moveToNext()){
                if (korisnikCursor.getInt(korisnikCursor.getColumnIndex(Korisnik::_id.name)) > 0)
                    id = korisnikCursor.getInt(korisnikCursor.getColumnIndex(Korisnik::_id.name))
                    break
            }

            korisnikCursor.close()
        }

        return id > -1
    }

    private fun insertDummyData() {

        val grupe = mutableListOf<Grupa>(
            Grupa(0, "Ruže"),
            Grupa(0, "Tulipani"),
            Grupa(0, "Glavočike"),
            Grupa(0, "Ostalo")
        )

        for (grupa: Grupa in grupe){
            val values = ContentValues().apply {
                put(Grupa::naziv.name, grupa.naziv)
            }

            contentResolver.insert(GRUPE_PROVIDER_CONTENT_URI, values)
        }

        val boje = mutableListOf<Boja>(
            Boja(null, "#FF0000", "Crvena"),
            Boja(null, "#00FF00", "Zelena"),
            Boja(null, "#0000FF", "Plava"),
            Boja(null, "#FFDD00", "Žuta"),
            Boja(null, "#FF5BC4", "Roza"),
            Boja(null, "#FF6D00", "Narančasta"),
            Boja(null, "#880DFF", "Ljubičasta")
        )

        for (boja: Boja in boje){
            val values = ContentValues().apply {
                put(Boja::bojaHex.name, boja.bojaHex)
                put(Boja::naziv.name, boja.naziv)
            }

            contentResolver.insert(BOJE_PROVIDER_CONTENT_URI, values)
        }


        val statusi = mutableListOf<Status>(
            Status(null, "Na skladištu", "Spremno za prodaju"),
            Status(null, "Nije za prodaju", "Uvenulo")
        )

        for (status: Status in statusi){
            val values = ContentValues().apply {
                put(Status::naziv.name, status.naziv)
                put(Status::opis.name, status.opis)
            }

            contentResolver.insert(STATUSI_PROVIDER_CONTENT_URI, values)
        }

        val artikli: List<Artikl> =
            mutableListOf(
                Artikl(null, "Ruža", 1, 5, 30, 1, null, null, 1),
                Artikl(null, "Tulipan", 2, 5, 20, 5, null, null, 1),
                Artikl(null, "Maslačak", 3, 15, 60, 4, null, null, 1),
                Artikl(null, "Žuta ruža", 1, 5, 30, 4, null, null, 1),
                Artikl(null, "Roza ruža", 1, 10, 40, 5, null, null, 2),
                Artikl(null, "Ljubičica", 4, 12, 48, 7, null, null, 1),
            )

        for (artikl: Artikl in artikli) {
            val values = ContentValues().apply {
                put(Artikl::naziv.name, artikl.naziv)
                put(Artikl::grupaId.name, artikl.grupaId)
                put(Artikl::jedinicnaKolicina.name, artikl.jedinicnaKolicina)
                put(Artikl::kolicinaUPakiranju.name, artikl.kolicinaUPakiranju)
                put(Artikl::statusId.name, artikl.statusId)
            }

            contentResolver.insert(ARTIKLI_PROVIDER_CONTENT_URI, values)
        }
    }

}