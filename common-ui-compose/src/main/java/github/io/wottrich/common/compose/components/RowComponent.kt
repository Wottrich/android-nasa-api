package github.io.wottrich.common.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import github.io.wottrich.common.compose.theme.dimens.Dimens

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun RowComponent(
    modifier: Modifier = Modifier,
    leftIconContent: @Composable (RowScope.() -> Unit)? = null,
    rightIconContent: @Composable (RowScope.() -> Unit)? = null,
    rightContent: @Composable (ColumnScope.() -> Unit)? = null,
    leftContent: @Composable ColumnScope.() -> Unit,
) {

    val boxModifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.surface)

    Box(
        modifier = boxModifier
    ) {
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Small.L)
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftContent(
                iconContent = leftIconContent,
                content = leftContent
            )
            RightContent(
                iconContent = rightIconContent,
                content = rightContent
            )
        }
    }
}

@Composable
private fun RowScope.LeftContent(
    iconContent: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit)? = null
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconContent?.invoke(this)
        content?.let {
            Spacer(modifier = Modifier.width(Dimens.Small.M))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                it()
            }
        }
    }
}

@Composable
private fun RowScope.RightContent(
    iconContent: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit)? = null
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content?.let {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                it()
            }
            Spacer(modifier = Modifier.width(Dimens.Small.M))
        }
        iconContent?.invoke(this)
    }
}

@Preview(showBackground = true)
@Composable
fun RowComponentPreview() {
    RowComponent(
        leftContent = {
            Text(text = "Text")
        }
    )
}