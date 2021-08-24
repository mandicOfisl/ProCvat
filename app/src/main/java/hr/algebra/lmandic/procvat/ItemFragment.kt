package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.lmandic.procvat.framework.fetchArtikli
import hr.algebra.lmandic.procvat.framework.fetchBoje
import hr.algebra.lmandic.procvat.framework.fetchNarudzbe
import hr.algebra.lmandic.procvat.framework.fetchStanjaSkladista
import hr.algebra.lmandic.procvat.model.FlowerItem
import hr.algebra.lmandic.procvat.model.StatusEnum


class ItemFragment : Fragment() {

    private lateinit var flowers: MutableList<FlowerItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val artikli = requireContext().fetchArtikli()
        val boje = requireContext().fetchBoje()
        val stanja = requireContext().fetchStanjaSkladista()
        val narudzbe = requireContext().fetchNarudzbe()

        for (artikl in artikli){
            flowers.add(
                FlowerItem(
                    artikl._id!!,
                    artikl.naziv,
                    boje
                        .first { b -> b._id == artikl.bojaId }.bojaHex,
                    artikl.picturePath,
                    stanja
                        .filter { s -> s.artiklId == artikl._id!! }
                        .sumOf { stanje -> stanje.kolicina },
                    narudzbe
                        .filter {
                            n -> n.artiklId == artikl._id!!
                                && n.statusId == StatusEnum.NEIZVRSENO.ordinal
                        }
                        .sumOf { nar -> nar.kolicina ?: 0 }
                )
            )
        }

        return inflater.inflate(R.layout.fragment_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = ItemAdapter(flowers, requireContext())
        view.findViewById<RecyclerView>(R.id.rvItems).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
        }
    }

}

