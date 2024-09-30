package com.mokelab.demo.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mokelab.demo.feature.mokera.ComposeMokeraListActivity
import com.mokelab.demo.feature.mokera.ViewMokeraListActivity
import com.mokelab.demo.viewmodel.ui.theme.ViewModelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelTheme {
                MainScreen(
                    toViewSystem = {
                        val intent = Intent(this, ViewMokeraListActivity::class.java)
                        startActivity(intent)
                    },
                    toCompose = {
                        val intent = Intent(this, ComposeMokeraListActivity::class.java)
                        startActivity(intent)
                    },
                )
            }
        }
    }
}
