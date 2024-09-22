package com.example.testideaplatform.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testideaplatform.R
import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.ui.theme.MinusIcon


/**
 * Текущее диалоговое окно, в зависимости от State'а диалогового окна.
 *
 * @param dialogState - state диалогового окна
 * @param onConfirmRequest - колбек действия "Подтверждение"
 * @param onDismissRequest - колбек действия "Отмена"
 * @receiver
 * @receiver
 */
@Composable
fun CurrentDialog(
    dialogState: DialogState,
    onConfirmRequest: (Item) -> Item,
    onDismissRequest: () -> Unit
) {
    when (dialogState) {
        is DialogState.Delete -> {
            ItemDialog(
                item = dialogState.currentItem,
                title = "Удаление товара",
                image = Icons.Filled.Warning,
                iconTitleContentDescription = "Удаление товара",
                content = {
                    Text(text = "Вы действительно хотите удалить выбранный товар?")
                },
                buttonLabels = arrayOf("Да", "Нет"),
                onConfirmRequest = {
                    onConfirmRequest(it)
                },
                onDismissRequest = onDismissRequest
            )
        }

        is DialogState.Edit -> {
            var currentAmountItemState by remember {
                mutableIntStateOf(dialogState.currentItem.amount)
            }

            ItemDialog(
                item = dialogState.currentItem,
                title = stringResource(R.string.title_edit_dialog),
                image = Icons.Filled.Settings,
                iconTitleContentDescription = stringResource(R.string.title_edit_dialog),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = MinusIcon,
                            contentDescription = stringResource(R.string.minus_icon_content_description),
                            tint = MaterialTheme.colorScheme.onPrimary,//Color.White
                            modifier = Modifier
                                .border(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    shape = CircleShape
                                )
                                .clickable {
                                    if (currentAmountItemState > 0) {
                                        currentAmountItemState--
                                    }
                                }
                        )
                        Text(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                            text = currentAmountItemState.toString()
                        )
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Plus",
                            tint = Color.White,
                            modifier = Modifier
                                .border(
                                    width = 1.5.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .clickable {
                                    currentAmountItemState++
                                }
                        )
                    }
                },
                onConfirmRequest = {
                    onConfirmRequest(it.copy(amount = currentAmountItemState))
                },
                onDismissRequest = onDismissRequest
            )
        }

        DialogState.Initial -> {

        }
    }

}

/**
 * Диалоговое окно для тек. товара в списке.
 *
 * @param item
 * @param title
 * @param image
 * @param content
 * @param onConfirmRequest
 * @param onDismissRequest
 * @param iconTitleContentDescription
 * @param buttonLabels
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun ItemDialog(
    item: Item,
    title: String,
    image: ImageVector,
    content: @Composable () -> Unit,
    onConfirmRequest: (Item) -> Item,
    onDismissRequest: () -> Unit,
    iconTitleContentDescription: String? = null,
    buttonLabels: Array<String> = emptyArray()
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            Icon(
                imageVector = image,
                contentDescription = iconTitleContentDescription
            )
        },
        title = {
            Text(text = title)
        },
        text = content,
        confirmButton = {
            Button(onClick = {
                onConfirmRequest(item)
            }) {
                Text(text = buttonLabels.firstOrNull() ?: "Принять")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text(text = buttonLabels.lastOrNull() ?: "Отмена")
            }
        }
    )
}