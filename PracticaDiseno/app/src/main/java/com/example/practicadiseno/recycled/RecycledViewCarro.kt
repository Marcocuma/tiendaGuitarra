package com.example.ejemplorecicledview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicadiseno.Guitarra
import com.example.practicadiseno.R
import kotlinx.android.synthetic.main.carrito_row.view.*
import kotlinx.android.synthetic.main.guitarra_row.view.*
import kotlinx.android.synthetic.main.guitarra_row.view.imageView
import kotlinx.android.synthetic.main.guitarra_row.view.marcamodelo
import java.io.InputStream
import java.net.URL

class RecycledViewCarro(val context:Context, val listaGuitarra:List<Guitarra>, private val itemClickListener: OnGuitarraClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return CarroViewHolder(LayoutInflater.from(context).inflate(R.layout.carrito_row, parent, false))
    }
    interface OnGuitarraClickListener{
        fun onItemClick(marca:String,modelo:String)
        fun quitar(item:Guitarra)
    }
    //Si la funcion solo devuelve un dato
    override fun getItemCount(): Int = listaGuitarra.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if(holder is CarroViewHolder){
            holder.bind(listaGuitarra[position],position)
        }
    }

    inner class CarroViewHolder(itemView: View):BaseViewHolder<Guitarra>(itemView){
        override fun bind(item: Guitarra, position: Int) {
            Glide.with(context).load(item.imagenURL).into(itemView.imageView)
            itemView.marcamodelo.text = item.marca+" : "+item.modelo
            itemView.setOnClickListener {
                itemClickListener.onItemClick(item.marca,item.modelo)
            }
            itemView.button_eliminar.setOnClickListener {
                itemClickListener.quitar(item)
            }
        }
    }
}