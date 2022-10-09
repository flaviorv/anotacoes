package com.example.anotacoes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anotacoes.database.Anotacoes

class AnotacoesAdapter(val context: Context,
                  val clickInterface: ClickInterface,
                  val clickDeleteInterface: ClickDeleteInterface) : RecyclerView.Adapter<AnotacoesAdapter.ViewHolder>() {

    private val titulos = ArrayList<Anotacoes>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        var deleteImageView: ImageView = itemView.findViewById(R.id.deleteImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_titulo,
            parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.tvTitulo.text = titulos.get(position).titulo.capitalize()

        holder.deleteImageView.setOnClickListener {
            clickDeleteInterface.onDeleteInterface(titulos.get(position))
        }
        holder.itemView.setOnClickListener{
            clickInterface.onClickInterface(position)
        }

    }

    override fun getItemCount(): Int {
        return titulos.size
    }
    fun updateList(newList: List<Anotacoes>) {
        titulos.clear()
        titulos.addAll(newList)
        notifyDataSetChanged()
    }
}
interface ClickDeleteInterface {
    fun onDeleteInterface(anotacao: Anotacoes)
}
interface ClickInterface {
    fun onClickInterface(anotacao: Int)
}