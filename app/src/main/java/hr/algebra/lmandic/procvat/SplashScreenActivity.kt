package hr.algebra.lmandic.procvat

import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import hr.algebra.lmandic.procvat.databinding.ActivitySplashScreenBinding
import hr.algebra.lmandic.procvat.framework.applyAnimation
import hr.algebra.lmandic.procvat.framework.getBooleanPreference
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
        if (getBooleanPreference("first_run")){

            //insertDummyData()

            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<NewUserActivity>()},
                DELAY
            )
        } else {
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<LoginActivity>()},
                DELAY
            )
        }
    }

    private fun insertDummyData() {

        val values = ContentValues().apply {
            put(Artikl::naziv.name, "Ruža")
            put(Artikl::grupaId.name, "1")
            put(Artikl::jedinicnaKolicina.name, "10")
            put(Artikl::kolicinaUPakiranju.name, "100")
            put(Artikl::bojaId.name, "1")

        }

        contentResolver.insert(ARTIKLI_PROVIDER_CONTENT_URI, values)
    }

}