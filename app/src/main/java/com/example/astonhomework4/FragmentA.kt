package com.example.astonhomework4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentA : Fragment() {
    private lateinit var btnToFragmentB: Button
    private lateinit var fragmentB: FragmentB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)
        btnToFragmentB = view.findViewById(R.id.btnToFragmentB)
        fragmentB = FragmentB.newInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToFragmentB.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, fragmentB).addToBackStack(
                FRAGMENT_A
            ).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}