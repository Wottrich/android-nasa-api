package github.io.wottrich.common.compose.components

import github.io.wottrich.common.compose.theme.ui.color.NasaTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun TextOneLine(
    modifier: Modifier = Modifier,
    primary: @Composable () -> Unit,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        StyledText(textStyle = MaterialTheme.typography.h6, content = primary)
    }
}

@Composable
fun TextTwoLine(
    modifier: Modifier = Modifier,
    primary: @Composable () -> Unit,
    secondary: @Composable () -> Unit,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        StyledText(textStyle = MaterialTheme.typography.h6, content = primary)
        StyledText(
            textStyle = MaterialTheme.typography.subtitle1,
            alpha = ContentAlpha.medium,
            content = secondary
        )
    }
}

@Composable
fun StyledText(
    textStyle: TextStyle,
    alpha: Float = LocalContentAlpha.current,
    contentColor: Color = NasaTheme.colors.onPrimary,
    content: @Composable (() -> Unit)
) {
    CompositionLocalProvider(
        LocalContentAlpha provides alpha,
        LocalContentColor provides contentColor,
    ) {
        ProvideTextStyle(textStyle, content)
    }
}