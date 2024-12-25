package com.example.astonhomework4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {
    private lateinit var btnToFragmentC: Button
    private lateinit var btnBackToFragmentA: Button
    private lateinit var fragmentC: FragmentC

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        btnToFragmentC = view.findViewById(R.id.btnToFragmentC)
        btnBackToFragmentA = view.findViewById(R.id.btnBackToFragmentA)
        fragmentC = FragmentC.newInstance("Hello Fragment C")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBackToFragmentA.setOnClickListener {
            parentFragmentManager.popBackStack(FRAGMENT_A, 1)
        }

        btnToFragmentC.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, fragmentC).addToBackStack(
                FRAGMENT_B
            ).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentB()
    }
}