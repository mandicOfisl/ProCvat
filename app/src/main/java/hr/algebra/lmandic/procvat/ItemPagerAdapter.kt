package hr.algebra.lmandic.procvat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.lmandic.procvat.model.FlowerItem
import java.io.File

class ItemPagerAdapter(private val items: MutableList<FlowerItem>, private val context: Context) : RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val ivFlower: ImageView = itemView.findViewById(R.id.ivFlower)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvGrupa: TextView = itemView.findViewById(R.id.tvGrupa)
        private val tvBoja: TextView = itemView.findViewById(R.id.tvBoja)
        private val tvKolicinaSkladiste: TextView = itemView.findViewById(R.id.tvNaSkladistu)
        private val tvKolicinaNarudzba: TextView = itemView.findViewById(R.id.tvNarudzbe)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvJedinicnaKol: TextView = itemView.findViewById(R.id.tvJedKolicina)
        private val tvPakiranjeKol: TextView = itemView.findViewById(R.id.tvPakiranjeKol)

        fun bind(item: FlowerItem){
            Picasso.get()
               .load(File(item.artikl.picturePath ?: ""))
               .error(R.drawable.ic_flower)
               .into(ivFlower)
            tvName.text = item.artikl.naziv
            tvGrupa.text = item.grupaNaziv
            tvBoja.text = item.bojaNaziv
            tvKolicinaSkladiste.text = item.kolicinaNaSkladistu.toString()
            tvKolicinaNarudzba.text = item.kolicinaZaNarudzbe.toString()
            tvStatus.text = item.artikl.statusId.toString()
            tvJedinicnaKol.text = item.artikl.jedinicnaKolicina.toString()
            tvPakiranjeKol.text = item.artikl.kolicinaUPakiranju.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pager, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}