package hr.algebra.lmandic.procvat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivitySkladisteBinding

class SkladisteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySkladisteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkladisteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setAdapter()

    }

    private fun setAdapter() {

    }
}