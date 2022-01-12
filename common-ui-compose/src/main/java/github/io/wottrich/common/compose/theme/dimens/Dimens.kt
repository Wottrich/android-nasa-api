package github.io.wottrich.common.compose.theme.dimens

import androidx.compose.ui.unit.dp

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object Dimens {
    /**
     * Sizes starting in 4dp until 15dp
     */
    object Small {
        /**
         * Size 4dp
         */
        val S = 4.dp

        /**
         * Size 8dp
         */
        val M = 8.dp

        /**
         * Size 12dp
         */
        val L = 12.dp

        /**
         * Extras sizes is used when SML not support all needs
         */
        object Extras {
            /**
             * Size 14dp
             */
            val Lx = 14.dp
        }
    }

    /**
     * Sizes starting in 16dp until 27dp
     */
    object Medium {
        /**
         * Size 16dp
         */
        val S = 16.dp

        /**
         * Size 18dp
         */
        val M = 18.dp

        /**
         * Size 24dp
         */
        val L = 24.dp

        /**
         * Extras sizes is used when SML not support all needs
         */
        object Extras {
            /**
             * Size 20dp
             */
            val xL = 20.dp
        }
    }

    /**
     * Sizes starting in 28dp until infinite
     */
    object Large {

        /**
         * Size 28dp
         */
        val S = 28.dp

        /**
         * Size 32dp
         */
        val M = 32.dp

        /**
         * Size 36dp
         */
        val L = 36.dp
    }
}