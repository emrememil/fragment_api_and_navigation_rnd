package com.example.full_screen_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener


class SecondFragment : Fragment() {

    lateinit var btnReturnBack: Button
    lateinit var btnOpenDialog: Button

    private var listener: SecondFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = parentFragment as SecondFragmentListener
        } catch (e: Exception) {
        }

        if (listener == null && context is SecondFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

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
            listener?.onBackClicked()
        }

        btnOpenDialog.setOnClickListener {
            listener?.onOpenDialogClicked()
        }
    }

    companion object {
        const val TAG = "SecondFragment"

        fun newInstance() = SecondFragment()
    }
}

interface SecondFragmentListener{
    fun onBackClicked()
    fun onOpenDialogClicked()
}