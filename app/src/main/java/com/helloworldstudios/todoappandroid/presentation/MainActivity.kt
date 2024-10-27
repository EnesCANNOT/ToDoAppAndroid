package com.helloworldstudios.todoappandroid.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.helloworldstudios.todoappandroid.R
import com.helloworldstudios.todoappandroid.databinding.ActivityMainBinding
import com.helloworldstudios.todoappandroid.presentation.view.ui.CalendarFragment
import com.helloworldstudios.todoappandroid.presentation.view.ui.HomeFragment
import com.helloworldstudios.todoappandroid.presentation.view.ui.ProfileFragment
import com.helloworldstudios.todoappandroid.presentation.view.ui.StatsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.nav_calendar -> {
                    loadFragment(CalendarFragment())
                    true
                }

                R.id.nav_stats -> {
                    loadFragment(StatsFragment())
                    true
                }

                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    println("Else")
                    true
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            println("FAB selected")
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }
}