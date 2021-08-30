package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import hr.algebra.lmandic.procvat.dao.ProcvatDatabase
import hr.algebra.lmandic.procvat.dao.entities.Korisnik
import hr.algebra.lmandic.procvat.databinding.ActivityLoginBinding
import hr.algebra.lmandic.procvat.framework.sendBroadcast
import hr.algebra.lmandic.procvat.framework.startActivity
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val dao = ProcvatDatabase.getInstance(this).procvatDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                toggleButton()
            }
        }

        binding.etUsername.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)

        binding.btnLogin.setOnClickListener {
            if (authenticateUser()) redirect() else handleIncorrectInput()
        }

        binding.btnNewUser?.setOnClickListener {
            Handler(Looper.getMainLooper()).post { startActivity<NewUserActivity>() }
        }
    }

    private fun handleIncorrectInput() {
        binding.etPassword.setText("", TextView.BufferType.EDITABLE)

        Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_SHORT).show()
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).post { startActivity<MenuActivity>() }
    }

    private fun authenticateUser() : Boolean {
        val inputUsername = binding.etUsername.text.toString()
        val inputPass = binding.etPassword.text.toString()

        var korisnik: Korisnik

        lifecycleScope.launch{
            korisnik = dao.getKorisnikByUsername(inputUsername)
        }

        val korisnikCursor = contentResolver.query(
            KORISNICI_PROVIDER_CONTENT_URI,
            null,
            "${Korisnik::korisnickoIme} LIKE ?",
            arrayOf(inputUsername),
            null,
        )

        if (korisnikCursor != null){
            while (korisnikCursor.moveToNext()){
                if (korisnikCursor.getString(korisnikCursor.getColumnIndex(Korisnik::lozinka.name))
                    == inputPass){
                        sendBroadcast<ProcvatReceiver>()
                        return true
                }
            }
        }

        korisnikCursor?.close()

        return false
    }

    private fun toggleButton() {
        binding.btnLogin.isEnabled =
            binding.etUsername.text.toString().isNotBlank()
                && binding.etPassword.text.toString().isNotBlank()
    }

}