package com.example.testideaplatform.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testideaplatform.R
import com.example.testideaplatform.commons.DataConverter
import com.example.testideaplatform.domain.entity.ProductItem
import com.example.testideaplatform.ui.theme.OrangeRed
import com.example.testideaplatform.ui.theme.Purple
import com.example.testideaplatform.ui.theme.TestIdeaPlatformTheme

/**
 * Карточка товара.
 *
 * @param modifier - modifier
 * @param productItem - товар
 * @param onUpdateClickListener - колбек обновления элемента
 * @param onDeleteClickListener - колбек удаления элемента
 * @receiver
 * @receiver
 */
@Composable
fun ItemCard(
    modifier: Modifier,
    productItem: ProductItem,
    onUpdateClickListener: (ProductItem) -> Unit,
    onDeleteClickListener: (ProductItem) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // region Шапка
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = productItem.name,
                modifier = Modifier
                    .weight(1f),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )

            // Редактирование.
            ActionIcon(
                modifier = Modifier,
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(R.string.edit_icon_content_description),
                tint = Purple,
                onItemClickListener = {
                    onUpdateClickListener(productItem)
                })

            // Удаление.
            ActionIcon(
                modifier = Modifier,
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.delete_icon_content_description),
                tint = OrangeRed,
                onItemClickListener = {
                    onDeleteClickListener(productItem)
                })
        }
        // endregion

        // region Теги
        Tags(
            modifier = Modifier.padding(horizontal = 8.dp),
            tags = DataConverter.getItemTagsFromString(productItem.tags)
        )
        // endregion

        // region Дополнительная информация
        AdditionalInfo(
            modifier = modifier.padding(start = 6.dp, end = 6.dp, bottom = 6.dp),
            productItem = productItem
        )
        // endregion
    }
}

/**
 * Иконка действия (редактирование/удаление элемента).
 *
 * @param modifier - modifier
 * @param imageVector - иконка
 * @param contentDescription - описание
 * @param tint - цвет
 * @param onItemClickListener - колбек клика
 * @receiver
 */
@Composable
fun ActionIcon(
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    tint: Color = MaterialTheme.colorScheme.onSecondary,
    onItemClickListener: () -> Unit,
) {
    Icon(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onItemClickListener()
            },
        tint = tint,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    modifier: Modifier,
    tags: List<String>
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(align = Alignment.Top),
        horizontalArrangement = Arrangement.Start
    ) {
        tags.forEach {
            AssistChip(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .align(alignment = Alignment.CenterVertically),
                onClick = { },
                label = {
                    Text(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        text = it
                    )
                }
            )
        }
    }
}

/**
 * Дополнительная информация.
 *
 * @param modifier - modifier
 * @param productItem - элемент
 * @param textColor - цвет текста
 */
@Composable
fun AdditionalInfo(
    modifier: Modifier,
    productItem: ProductItem,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = modifier) {
            Text(text = "На складе", color = textColor)
            Text(text = productItem.amount.toString(), color = textColor)
        }
        Column(modifier = modifier) {
            Text(
                text = "Дата добавления",
                color = textColor
            )
            Text(
                text = DataConverter.timestampToStringDate(productItem.time),
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun ItemCardPreviewLightTheme() {
    TestIdeaPlatformTheme(darkTheme = false, dynamicColor = false) {
        ItemCard(
            productItem = ProductItem(
                id = 1,
                name = "Samsung Galaxy S21",
                tags = "[\"abc\", \"def\"]",
                time = 12,
                amount = 1
            ), modifier = Modifier.padding(4.dp),
            onUpdateClickListener = {

            },
            onDeleteClickListener = {

            }
        )
    }
}

@Preview
@Composable
fun ItemCardPreviewDarkTheme() {
    TestIdeaPlatformTheme(darkTheme = true, dynamicColor = false) {
        ItemCard(
            productItem = ProductItem(
                id = 1,
                name = "Samsung Galaxy S21",
                tags = "[\"abc\", \"def\"]",
                time = 12,
                amount = 1
            ), modifier = Modifier.padding(4.dp),
            onUpdateClickListener = {

            },
            onDeleteClickListener = {

            }
        )
    }
}
