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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ejemplorecicledview.RecycledViewAdapter
import com.example.ejemplorecicledview.RecycledViewCarro
import kotlinx.android.synthetic.main.fragment_first.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),RecycledViewAdapter.OnGuitarraClickListener, RecycledViewCarro.OnGuitarraClickListener{
    lateinit var guitarras : ArrayList<Guitarra>
    var carrito : ArrayList<Guitarra> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        guitarras = java.util.ArrayList()
        conectarSimple()
        recyclerView.layoutManager = LinearLayoutManager(context)
        super.onViewCreated(view, savedInstanceState)
        buttonCarrito.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("carro",carrito)
            Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }
    }

    override fun onItemClick(marca: String, modelo: String) {
        Toast.makeText(context,marca+" : "+modelo,Toast.LENGTH_SHORT).show()
    }

    override fun quitar(item: Guitarra) {
        carrito.remove(item)
    }

    override fun anadir(item: Guitarra) {
        if(!carrito.contains(item)) {
            carrito.add(item)
            Toast.makeText(context,"Añadido correctamente",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Ya esta en el carrito",Toast.LENGTH_SHORT).show()
        }
    }

    fun conectarSimple(){
        val url ="http://iesayala.ddns.net/marcom/jsonguitarras.php"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,url, Response.Listener {
                response ->
            val jsonArray = JSONArray(response)
            for(i in 0 until jsonArray.length()){
                val jsonObject = JSONObject(jsonArray.getString(i))
                val marca = jsonObject.get("marca").toString()
                val modelo = jsonObject.get("modelo").toString()
                val precio = jsonObject.get("precio").toString()
                val foto = jsonObject.get("foto").toString()
                guitarras.add(Guitarra(marca,modelo,precio.toDouble(),foto))
            }
            recyclerView.adapter = context?.let { RecycledViewAdapter(it,
                guitarras, this) }
        },
            Response.ErrorListener {
                Toast.makeText(context, "Fallo al acceder", Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
        queue.start()
    }
}