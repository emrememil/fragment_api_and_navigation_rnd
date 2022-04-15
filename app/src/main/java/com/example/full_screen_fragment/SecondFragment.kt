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


class SecondFragment : Fragment() {

    lateinit var btnReturnBack: Button
    lateinit var btnOpenDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(Extras.REQUEST_KEY_3) { _, bundle ->
            val value = bundle.getString(Extras.DATA)
            Toast.makeText(requireContext(), value ?: "null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        btnReturnBack = view.findViewById(R.id.btnReturnBack)
        btnOpenDialog = view.findViewById(R.id.btnOpenDialog)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnReturnBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
            setFragmentResult(Extras.REQUEST_KEY_1, bundleOf(Extras.DATA to "CloudApper"))
        }

        btnOpenDialog.setOnClickListener {
            InformationDialog.newInstance().show(childFragmentManager, InformationDialog.TAG)
            childFragmentManager.setFragmentResult(
                Extras.REQUEST_KEY_2,
                bundleOf(Extras.DATA to "Android")
            )
        }
    }
}