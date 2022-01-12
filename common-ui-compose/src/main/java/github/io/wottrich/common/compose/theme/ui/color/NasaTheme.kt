package github.io.wottrich.common.compose.theme.ui.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 07/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object NasaTheme {

    val colors: NasaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNasaColors.current

}