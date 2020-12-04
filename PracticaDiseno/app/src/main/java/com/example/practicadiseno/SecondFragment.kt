package com.example.practicadiseno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemplorecicledview.RecycledViewAdapter
import com.example.ejemplorecicledview.RecycledViewCarro
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), RecycledViewCarro.OnGuitarraClickListener {
    var carrito : ArrayList<Guitarra> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carrito = arguments?.getSerializable("carro") as ArrayList<Guitarra>
        recyclerView2.layoutManager = LinearLayoutManager(context)
        cargarGuitarras()
        buttonPagar.setOnClickListener {
            if(!carrito.isEmpty())
                Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_pedidoRealizado)
            else
                Toast.makeText(context, "El carrito esta vacío", Toast.LENGTH_SHORT).show()
        }
    }
    fun cargarGuitarras(){
        recyclerView2.adapter = context?.let { RecycledViewCarro(it,
            carrito, this) }
        var precioTotal = 0.0
        for (guitarra in carrito)
            precioTotal += guitarra.precio
        textViewPrecio.text="Precio total: "+precioTotal
    }
    override fun onItemClick(marca: String, modelo: String) {
        Toast.makeText(context,marca+" : "+modelo, Toast.LENGTH_SHORT).show()
    }

    override fun quitar(item: Guitarra) {
        carrito.remove(item)
        cargarGuitarras()
    }
}