package pt.ulusofona.deisi.cm2122.g21903016_21903361.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.filipe.tomas.g21903016_21903361.databinding.ItemFireBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.NavigationManager

class FireListAdapter(
    private val supportFragmentManager: FragmentManager,
    private var items: List<FireUi> = listOf(),
) : RecyclerView.Adapter<FireListAdapter.FireListViewHolder>(){
    class FireListViewHolder(val binding: ItemFireBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FireListViewHolder {
        return FireListViewHolder(
            ItemFireBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FireListViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            NavigationManager.goToFireDetails(supportFragmentManager, items[position])
        }
        holder.binding.distritoFreguesia.text = "${items[position].district}, ${items[position].district}"
        holder.binding.datetime.text =  items[position].getDateTime()
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<FireUi>) {
        this.items = items
        notifyDataSetChanged()
    }

}