package com.example.astonhomework4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentD : Fragment() {
    private lateinit var btnBackToFragmentB: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_d, container, false)
        btnBackToFragmentB = view.findViewById(R.id.btnBackToFragmentB)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBackToFragmentB.setOnClickListener {
            parentFragmentManager.popBackStack(FRAGMENT_B, 1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentD()
    }
}