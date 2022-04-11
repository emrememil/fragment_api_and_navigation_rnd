package com.example.full_screen_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.NavHostFragment


class FirstFragment : Fragment() {

    lateinit var btnOpenDialog: Button
    lateinit var btnOpenDialog2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(Extras.REQUEST_KEY_1) { _, bundle ->
            val value = bundle.getString(Extras.DATA)
            Toast.makeText(requireContext(), value ?: "null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        btnOpenDialog = view.findViewById(R.id.btnOpenDialog)
        btnOpenDialog2 = view.findViewById(R.id.btnOpenDialog2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOpenDialog.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.secondControllerFragment)
        }

        btnOpenDialog2.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.secondControllerFragment)
            setFragmentResult(Extras.REQUEST_KEY_3, bundleOf(Extras.DATA to "DATA"))
        }
    }
}