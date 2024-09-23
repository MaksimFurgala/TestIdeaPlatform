package com.example.testideaplatform.presentation.dialogs

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testideaplatform.R
import com.example.testideaplatform.domain.entity.ProductItem
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
    onConfirmRequest: (ProductItem) -> Unit,
    onDismissRequest: () -> Unit,
) {
    when (dialogState) {
        is DialogState.Delete -> {
            ItemDialog(
                productItem = dialogState.currentProductItem,
                title = "Удаление товара",
                image = Icons.Filled.Warning,
                iconTitleContentDescription = stringResource(R.string.title_delete_dialog),
                content = {
                    Text(text = stringResource(R.string.content_delete_dialog))
                },
                buttonLabels = arrayOf(
                    stringResource(R.string.dialog_confirm_button_label),
                    stringResource(
                        R.string.dialog_dismiss_button_label
                    )
                ),
                onConfirmRequest = {
                    onConfirmRequest(it)
                },
                onDismissRequest = onDismissRequest
            )
        }

        is DialogState.Edit -> {
            var currentAmountItemState by remember {
                mutableIntStateOf(dialogState.currentProductItem.amount)
            }

            ItemDialog(
                productItem = dialogState.currentProductItem,
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
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,//MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,//MaterialTheme.colorScheme.onPrimary,
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
                            text = currentAmountItemState.toString(),
                            fontSize = 20.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_icon_content_description),
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,//MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,//MaterialTheme.colorScheme.onPrimary,
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
 * @param productItem - тек. товар
 * @param title - заголовок
 * @param image - иконка
 * @param content - контент
 * @param onConfirmRequest - колбек подтверждения
 * @param onDismissRequest - колбек отмены
 * @param iconTitleContentDescription - описание иконки
 * @param buttonLabels - надписи для кнопок
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun ItemDialog(
    productItem: ProductItem,
    title: String,
    image: ImageVector,
    content: @Composable () -> Unit,
    onConfirmRequest: (ProductItem) -> Unit,
    onDismissRequest: () -> Unit,
    iconTitleContentDescription: String? = null,
    buttonLabels: Array<String> = emptyArray(),
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            Icon(
                imageVector = image,
                contentDescription = iconTitleContentDescription,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        title = {
            Text(text = title, color = MaterialTheme.colorScheme.onPrimary)
        },
        text = content,
        confirmButton = {
            Button(onClick = {
                onConfirmRequest(productItem)
            }) {
                Text(
                    text = buttonLabels.firstOrNull()
                        ?: stringResource(R.string.dialog_confirm_button_label_deffault),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text(
                    text = buttonLabels.lastOrNull()
                        ?: stringResource(R.string.dialog_dismiss_button_label_default),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    )
}