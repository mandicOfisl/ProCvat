package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.algebra.lmandic.procvat.databinding.FragmentMenuBinding
import hr.algebra.lmandic.procvat.framework.applyAnimation


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        startAnimations()
        setupListeners()
    }

    private fun startAnimations() {
        binding.btnStanjeSkladista.applyAnimation(R.anim.slide_up)
        binding.btnUnosArtikala.applyAnimation(R.anim.slide_up)
        binding.btnKupciNarudzbe.applyAnimation(R.anim.slide_up)
        binding.btnKorisnickePostavke.applyAnimation(R.anim.slide_up)
    }

    private fun setupListeners() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

}