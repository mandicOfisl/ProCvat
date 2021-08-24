package hr.algebra.lmandic.procvat

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivityNewUserBinding
import hr.algebra.lmandic.procvat.model.Korisnik

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                toggleButton()
            }
        }

        binding.etNewUsername.addTextChangedListener(textWatcher)
        binding.etNewPassword.addTextChangedListener(textWatcher)
        binding.etNewPasswordConfirm.addTextChangedListener(textWatcher)

        binding.btnAddUser.setOnClickListener {
            val values = ContentValues().apply {
                put(Korisnik::korisnickoIme.name, binding.etNewUsername.text.toString())
                put(Korisnik::lozinka.name, binding.etNewPassword.text.toString())
            }

            contentResolver.insert(KORISNICI_PROVIDER_CONTENT_URI, values)

            Toast.makeText(this, getString(R.string.korisnik_je_kreiran), Toast.LENGTH_LONG).show()
        }
    }

    private fun toggleButton() {
        binding.btnAddUser.isEnabled =
            binding.etNewUsername.text.isNotBlank()
                && passwordsMatch()
    }

    private fun passwordsMatch(): Boolean =
        binding.etNewPassword.text.isNotBlank()
            && binding.etNewPasswordConfirm.text.isNotBlank()
            && binding.etNewPassword.text.toString() == binding.etNewPasswordConfirm.text.toString()
}