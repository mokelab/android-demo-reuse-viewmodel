package com.mokelab.demo.viewmodel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    toViewSystem: () -> Unit,
    toCompose: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            ListItem(
                headlineContent = {
                    Text("View System")
                },
                modifier = Modifier.clickable {
                    toViewSystem()
                },
            )

            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Compose")
                },
                modifier = Modifier.clickable {
                    toCompose()
                },
            )

            HorizontalDivider()
        }
    }
}