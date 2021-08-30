package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivityMenuBinding
import hr.algebra.lmandic.procvat.framework.applyAnimation
import hr.algebra.lmandic.procvat.framework.startActivity

private const val DELAY : Long = 3000

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        startAnimations()

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnStanjeSkladista.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<SkladisteActivity>()},
                DELAY
            )
        }


    }

    private fun startAnimations() {
        binding.btnStanjeSkladista.applyAnimation(R.anim.slide_up)
        binding.btnUnosArtikala.applyAnimation(R.anim.slide_up)
        binding.btnKupciNarudzbe.applyAnimation(R.anim.slide_up)
        binding.btnKorisnickePostavke.applyAnimation(R.anim.slide_up)
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Odjava")
        builder.setMessage("Želite li se odjaviti?")

        builder.setPositiveButton("Da") { dialog, which ->
            super.onBackPressed()
        }

        builder.setNegativeButton("Ne") { dialog, which ->
        }

        builder.show()
    }
}
