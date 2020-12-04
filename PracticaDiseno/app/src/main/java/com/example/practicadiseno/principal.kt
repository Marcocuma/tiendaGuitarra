package com.example.practicadiseno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_principal.*


/**
 * A simple [Fragment] subclass.
 * Use the [principal.newInstance] factory method to
 * create an instance of this fragment.
 */
class principal : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_principal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonComprar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_principal2_to_FirstFragment)
        }
    }
}