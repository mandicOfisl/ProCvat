package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivityLoginBinding
import hr.algebra.lmandic.procvat.framework.startActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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

        binding.btnLogin.setOnClickListener {
            if (authenticateUser()) redirect() else handleIncorrectInput()
        }
    }

    private fun handleIncorrectInput() {
        binding.etPassword.setText("", TextView.BufferType.EDITABLE)

        Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_SHORT)
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).post { startActivity<MenuActivity>() }
    }

    private fun authenticateUser() : Boolean {
        //dohvati usera iz baze
        val inputPass = binding.etPassword.text.toString()
        //provjeri lozinku sa inputom

        return true
    }

    private fun toggleButton() {
        binding.btnLogin.isEnabled =
            binding.etUsername.text.toString().isNotBlank()
                && binding.etPassword.text.toString().isNotBlank()
    }

}