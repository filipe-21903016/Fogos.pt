package pt.ulusofona.deisi.cm2122.g21903016_21903361.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.NavigationManager
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.ItemFireBinding
import java.lang.StringBuilder

class FireListAdapter(
    private val onClick: (FireUi) -> Unit,
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
            onClick(items[position])
        }
        val district = items[position].district
        val concelho = items[position].concelho ?: items[position].missingInfoString()

        holder.binding.distritoFreguesia.text = "${trimLocationName(district)}, ${trimLocationName(concelho)}"
        holder.binding.datetime.text =  items[position].getDateTime()
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<FireUi>) {
        this.items = items
        notifyDataSetChanged()
    }


    private fun trimLocationName(name: String): String
    {
        val trimmedName = StringBuilder()
        val words = name.split(" ")
        if (words.size == 1)
            return name
        words.forEach {
            val i = words.indexOf(it)
            if (i == words.size - 1)
                trimmedName.append(it)
            else if (it.uppercase() != "DA" && it.uppercase() != "DO")
                trimmedName.append("${it[0]}. ")
        }
        return trimmedName.toString()
    }

}