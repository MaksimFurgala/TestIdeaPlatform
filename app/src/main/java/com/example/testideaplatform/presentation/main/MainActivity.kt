package com.example.testideaplatform.presentation.main

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.testideaplatform.R
import com.example.testideaplatform.ui.theme.TestIdeaPlatformTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val window: Window = this.window
            TestIdeaPlatformTheme {
                window.navigationBarColor = MaterialTheme.colorScheme.surface.toArgb()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors()
                                .copy(containerColor = MaterialTheme.colorScheme.surface),
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.top_app_bar_title),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            })
                    }
                ) { innerPadding ->
                    MainScreen(paddingValues = innerPadding)
                }
            }
        }
    }
}
