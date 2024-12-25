package com.example.astonhomework4

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val fragmentInitial = InitialFragment.newInstance()
    private lateinit var btnToInitialFragment: Button
    private var isButtonVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnToInitialFragment = findViewById(R.id.btnInitialFragment)

        if (savedInstanceState != null) {
            isButtonVisible = savedInstanceState.getBoolean("isButtonVisible", true)
        }
        btnToInitialFragment.visibility = if (isButtonVisible) View.VISIBLE else View.INVISIBLE

        btnToInitialFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main, fragmentInitial)
                .addToBackStack(
                    INITIAL_FRAGMENT
                ).commit()
            btnToInitialFragment.visibility = View.INVISIBLE
            isButtonVisible = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isButtonVisible", isButtonVisible)
    }
}

const val FRAGMENT_A = "FRAGMENT_A"
const val INITIAL_FRAGMENT = "INITIAL_FRAGMENT"
const val FRAGMENT_B = "FRAGMENT_B"
const val FRAGMENT_C = "FRAGMENT_C"