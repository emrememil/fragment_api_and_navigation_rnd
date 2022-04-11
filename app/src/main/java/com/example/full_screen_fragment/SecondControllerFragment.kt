package com.example.full_screen_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.NavHostFragment


class SecondControllerFragment : Fragment(), SecondFragmentListener, InformationDialogListener {


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
        return inflater.inflate(R.layout.fragment_second_controller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.commitNow {
            replace(
                R.id.container,
                SecondFragment.newInstance(),
                SecondFragment.TAG
            )
        }
    }

    override fun onBackClicked() {
        NavHostFragment.findNavController(this).navigateUp()
        setFragmentResult(Extras.REQUEST_KEY_1, bundleOf(Extras.DATA to "CloudApper"))
    }

    override fun onOpenDialogClicked() {
        InformationDialog.newInstance().show(childFragmentManager,InformationDialog.TAG)
        childFragmentManager.setFragmentResult(Extras.REQUEST_KEY_2, bundleOf(Extras.DATA to "Android"))
    }

    override fun onStartFullScreenFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.thirdFragment)
    }
}