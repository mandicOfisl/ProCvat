package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.lmandic.procvat.databinding.FragmentSkladisteBinding
import hr.algebra.lmandic.procvat.framework.fetchFlowerItems
import hr.algebra.lmandic.procvat.model.FlowerItem

class SkladisteFragment : Fragment() {

    private lateinit var binding: FragmentSkladisteBinding
    private lateinit var flowers: MutableList<FlowerItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        flowers = requireContext().fetchFlowerItems()

        return inflater.inflate(R.layout.fragment_skladiste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSkladisteBinding.bind(view)

        val itemAdapter = ItemAdapter(flowers, requireContext())
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
        }
    }

}