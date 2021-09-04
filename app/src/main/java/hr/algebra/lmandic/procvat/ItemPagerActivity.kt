package hr.algebra.lmandic.procvat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.databinding.ActivityItemPagerBinding
import hr.algebra.lmandic.procvat.framework.fetchFlowerItems
import hr.algebra.lmandic.procvat.model.FlowerItem

const val ITEM_POSITION = "hr.algebra.lmandic.procvat.item_position"

class ItemPagerActivity : AppCompatActivity() {

    private lateinit var items: MutableList<FlowerItem>
    private var itemPosition: Int = 0

    private lateinit var binding: ActivityItemPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemPagerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        items = fetchFlowerItems()
        itemPosition = intent.getIntExtra(ITEM_POSITION, 0)

        binding.viewPager.adapter = ItemPagerAdapter(items, this)
        binding.viewPager.currentItem = itemPosition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}