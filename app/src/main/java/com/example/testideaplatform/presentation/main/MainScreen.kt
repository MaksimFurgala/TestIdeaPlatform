package com.example.testideaplatform.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testideaplatform.R
import com.example.testideaplatform.domain.entity.ProductItem
import com.example.testideaplatform.presentation.dialogs.CurrentDialog
import com.example.testideaplatform.presentation.dialogs.DialogState
import com.example.testideaplatform.ui.theme.Purple40

/**
 * Главный экран приложения.
 *
 * @param paddingValues
 */
@Composable
fun MainScreen(paddingValues: PaddingValues) {
    val viewModel: MainViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState(initial = MainScreenState.Initial)

    when (val currentState = screenState.value) {
        is MainScreenState.Items -> {
            ItemsCollection(
                viewModel = viewModel,
                paddingValues = paddingValues,
                productItems = currentState.productItems
            )
        }

        // Прелоудер.
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
 * Поле для поиска товара.
 *
 * @param value - тек. значение
 * @param onValueChange - колбек события изменения текста
 * @param modifier - modifier
 * @receiver
 */
@Composable
private fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Тек. фокус в приложении.
    val focusManager = LocalFocusManager.current

    // Строка поиска.
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
        }),
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Purple40,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(R.string.search_text_field_placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_text_field_placeholder)
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.clear_value_search_text_field),
                    modifier = Modifier.clickable {
                        onValueChange("")
                    })
            }
        }
    )
}

/**
 * Коллекция товаров.
 *
 * @param viewModel - viewModel
 * @param paddingValues - paddingValues
 * @param productItems - список товары
 */
@Composable
private fun ItemsCollection(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    productItems: List<ProductItem>
) {
    // State для диалоговых окон.
    var dialogState: DialogState by remember {
        mutableStateOf(DialogState.Initial)
    }

    // State для поиска товара по наименованию.
    var searchTextState by remember {
        mutableStateOf("")
    }

    // region Диалоговое окно
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
    // endregion

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            horizontal = 8.dp, vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            SearchField(
                value = searchTextState,
                onValueChange = {
                    searchTextState = it
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            // Отбираем элементы в список, с учетом того установлен ли фильтр в строке поиска.
            items = productItems.filter {
                searchTextState.isBlank() || it.name.contains(
                    searchTextState,
                    true
                )
            },
            key = { it.id }
        ) { item ->
            ItemCard(
                modifier = Modifier,
                productItem = item,
                onUpdateClickListener = {
                    dialogState = DialogState.Edit(currentProductItem = it)
                },
                onDeleteClickListener = {
                    dialogState = DialogState.Delete(currentProductItem = it)
                }
            )
        }
    }
}


