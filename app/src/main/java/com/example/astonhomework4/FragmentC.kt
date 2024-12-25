package com.example.astonhomework4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class FragmentC : Fragment() {
    private var textFromFragmentB: String? = null
    private lateinit var btnToFragmentD: Button
    private lateinit var btnBackToFragmentA: Button
    private lateinit var tvText: TextView
    private lateinit var fragmentD: FragmentD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textFromFragmentB = it.getString(FRAGMENT_B)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_c, container, false)
        btnToFragmentD = view.findViewById(R.id.btnToFragmentD)
        btnBackToFragmentA = view.findViewById(R.id.btnBackToFragmentA)
        tvText = view.findViewById(R.id.tvText)
        fragmentD = FragmentD.newInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvText.text = textFromFragmentB

        btnBackToFragmentA.setOnClickListener {
            parentFragmentManager.popBackStack(FRAGMENT_A, 1)
        }

        btnToFragmentD.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, fragmentD)
                .addToBackStack(FRAGMENT_C).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) = FragmentC().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_B, param)
            }
        }
    }
}