package github.io.wottrich.common.compose.theme.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import github.io.wottrich.common.compose.theme.ui.color.LocalNasaColors
import github.io.wottrich.common.compose.theme.ui.color.NasaColors
import github.io.wottrich.common.compose.theme.ui.color.darkColors
import github.io.wottrich.common.compose.theme.ui.color.lightColors

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 07/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun NasaTheme(
    colors: NasaColors = getColorsBySystem(),
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        colors.copy()
    }.apply {
        updateColorsFrom(colors)
    }
    CompositionLocalProvider(
        LocalNasaColors provides rememberedColors,
    ) {
        MaterialTheme(
            colors = LocalNasaColors.current.toMaterialTheme(),
            content = content
        )
    }
}

fun NasaColors.toMaterialTheme(): Colors {
    return Colors(
        primary,
        primaryVariant,
        secondary,
        secondaryVariant,
        background,
        surface,
        error,
        onPrimary,
        onSecondary,
        onBackground,
        onSurface,
        onError,
        isLight
    )
}

@Composable
fun getColorsBySystem(): NasaColors {
    return if (isSystemInDarkTheme()) {
        darkColors()
    } else {
        lightColors()
    }
}