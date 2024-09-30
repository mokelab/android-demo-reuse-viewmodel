package com.mokelab.demo.feature.mokera

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mokelab.demo.core.model.Mokera
import com.mokelab.demo.core.model.MokeraType

@Composable
fun MokeraListScreen(
    viewModel: MokeraListViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    MokeraListScreen(
        uiState = uiState,
        load = {
            viewModel.load()
        },
    )
}

@Composable
private fun MokeraListScreen(
    uiState: MokeraListViewModel.UiState,
    load: () -> Unit,
) {
    Scaffold { innerPadding ->
        when (uiState) {
            is MokeraListViewModel.UiState.Initial,
            is MokeraListViewModel.UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                ) {
                    CircularProgressIndicator()
                }
            }

            is MokeraListViewModel.UiState.Success -> {
                LazyColumn(
                    contentPadding = innerPadding,
                ) {
                    items(uiState.items) { item ->
                        MokeraListItem(item)
                    }
                }
            }

            is MokeraListViewModel.UiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                ) {
                    Text("Error!")
                }
            }
        }
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            MokeraListViewModel.UiState.Initial -> {
                load()
            }

            MokeraListViewModel.UiState.Loading,
            is MokeraListViewModel.UiState.Success,
            is MokeraListViewModel.UiState.Error -> {
            }
        }
    }
}

@Composable
private fun MokeraListItem(
    item: Mokera,
) {
    ListItem(
        leadingContent = {
            Image(
                painter = painterResource(R.drawable.mokera), contentDescription = "Mokera",
                modifier = Modifier.size(32.dp),
            )
        },
        headlineContent = {
            Text(text = item.name)
        },
        supportingContent = {
            Text(text = item.type.toLabel())
        }
    )
    HorizontalDivider()
}

private fun MokeraType.toLabel(): String {
    return when (this) {
        MokeraType.MelonSoda -> "Melon Soda"
        MokeraType.Coffee -> "Coffee"
        MokeraType.OrangeJuice -> "Orange Juice"
        MokeraType.Milk -> "Milk"
    }
}