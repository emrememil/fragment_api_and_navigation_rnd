package com.example.full_screen_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class InformationDialog : BottomSheetDialogFragment() {

    lateinit var btnStartFullScreenFragment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(Extras.REQUEST_KEY_2) { _, bundle ->
            val value = bundle.getString(Extras.DATA)
            Toast.makeText(requireContext(), value ?: "null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_information, container, false)
        btnStartFullScreenFragment = view.findViewById(R.id.btnStartFragment)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartFullScreenFragment.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.thirdFragment)
        }
    }

    companion object {
        const val TAG = "InformationDialog"
        fun newInstance() = InformationDialog()
    }
}