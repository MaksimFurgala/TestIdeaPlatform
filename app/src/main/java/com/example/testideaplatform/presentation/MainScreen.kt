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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

        else -> {

        }
    }

}

/**
 * Коллекция товаров.
 *
 * @param viewModel - viewModel
 * @param paddingValues - paddingValues
 * @param items - список товары
 */
@Composable
private fun ItemsCollection(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    items: List<Item>
) {
    // State для диалоговых окон.
    var dialogState: DialogState by remember {
        mutableStateOf(DialogState.Initial)
    }

    CurrentDialog(
        dialogState = dialogState,
        onConfirmRequest = {
            when (dialogState) {
                is DialogState.Delete -> {
                    viewModel.deleteItem(it)
                    dialogState = DialogState.Initial
                    it
                }

                is DialogState.Edit -> {
                    viewModel.updateItem(it)
                    dialogState = DialogState.Initial
                    it
                }

                DialogState.Initial -> {
                    it
                }
            }
        },
        onDismissRequest = {
            dialogState = DialogState.Initial
        }
    )

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            horizontal = 8.dp, vertical = 16.dp
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
                    dialogState = DialogState.Edit(currentItem = it)
                },
                onDeleteClickListener = {
                    dialogState = DialogState.Delete(currentItem = it)
                }
            )
        }
    }
}


