package com.example.testideaplatform.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testideaplatform.domain.entity.Item

@Composable
fun MainScreen(paddingValues: PaddingValues) {
    val viewModel: MainViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState(initial = MainScreenState.Initial)

    when (val currentState = screenState.value) {
        is MainScreenState.Items -> {
            ItemsCollection(
                viewModel = viewModel,
                paddingValues = paddingValues,
                items = currentState.items
            )
        }

        MainScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        MainScreenState.Initial -> {

        }
    }

}

@Composable
private fun ItemsCollection(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    items: List<Item>
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = items,
            key = { it.id }
        ) { item ->
            ItemCard(
                modifier = Modifier,
                item = item,
                onUpdateClickListener = {
                    // TODO: диалоговое окно!!!
                    viewModel.updateItem(it)
                },
                onDeleteClickListener = {
                    // TODO: диалоговое окно!!!
                    viewModel.deleteItem(it)
                }
            )
        }
    }
}