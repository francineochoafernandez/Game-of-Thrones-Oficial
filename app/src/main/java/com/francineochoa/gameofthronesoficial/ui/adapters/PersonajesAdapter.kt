package com.francineochoa.gameofthronesoficial.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.francineochoa.gameofthronesoficial.databinding.ElementoPersonajeBinding
import com.francineochoa.gameofthronesoficial.model.Personaje

class PersonajesAdapter (
    private var personajes: ArrayList<Personaje>,
    private var onPersonajeClicked: (Personaje) -> Unit) :RecyclerView.Adapter<PersonajesAdapter.ViewHolder>()
{
    class ViewHolder(private var binding: ElementoPersonajeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(personaje: Personaje) {
            Glide.with(itemView.context)
                .load(personaje.imageUrl)
                .into(binding.ivPersonaje)
            binding.tvNombre.text = personaje.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ElementoPersonajeBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =personajes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val personaje = personajes[position]

        holder.bind(personaje)

        holder.itemView.setOnClickListener{
            onPersonajeClicked(personaje)
        }
    }


}