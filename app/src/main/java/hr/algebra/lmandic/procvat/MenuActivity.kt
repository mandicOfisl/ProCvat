package hr.algebra.lmandic.procvat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        startAnimations()
    }

    private fun startAnimations() {
        TODO("Not yet implemented")
    }
}