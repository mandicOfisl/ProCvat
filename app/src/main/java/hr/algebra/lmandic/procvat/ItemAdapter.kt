package hr.algebra.lmandic.procvat

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.lmandic.procvat.model.FlowerItem
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemAdapter(private val items: MutableList<FlowerItem>, private val context: Context)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            private val ivColorRec: ImageView = itemView.findViewById(R.id.ivItemFlowerColor)
            private val ivPicture: ImageView = itemView.findViewById(R.id.ivPicture)
            private val tvNaziv: TextView = itemView.findViewById(R.id.tvNaziv)
            private val tvNarudzbe: TextView = itemView.findViewById(R.id.tvKolicinaZaNarudzbe)
            private val tvSkladista: TextView = itemView.findViewById(R.id.tvKolicinaNaSkladistu)

            fun bind(flowerItem: FlowerItem){
                ivColorRec.foreground = ColorDrawable(flowerItem.colorHex.toInt(16))

                Picasso.get()
                    .load(File(flowerItem.picturePath!!))
                    .error(R.drawable.ic_flower)
                    .transform(RoundedCornersTransformation(20, 4))
                    .into(ivPicture)

                tvNaziv.text = flowerItem.name

                tvNarudzbe.text = flowerItem.kolicinaZaNarudzbe.toString()

                tvSkladista.text = flowerItem.kolicinaNaSkladistu.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            //context.startActivity<FlowerDetailsActivity>()
        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}