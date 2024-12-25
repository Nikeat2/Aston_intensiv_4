package com.example.astonhomework4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class InitialFragment : Fragment() {
    private lateinit var btnTask1: Button
    private lateinit var btnTask2: Button
    private val fragmentA = FragmentA.newInstance()
    private val fragmentWithUsers = UsersListFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_initial, container, false)
        btnTask1 = view.findViewById(R.id.btnTask1)
        btnTask2 = view.findViewById(R.id.btnTask2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTask1.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, fragmentA).addToBackStack(
                INITIAL_FRAGMENT
            ).commit()
        }

        btnTask2.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, fragmentWithUsers)
                .addToBackStack(
                    INITIAL_FRAGMENT
                ).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InitialFragment()
    }
}