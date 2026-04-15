package com.example.cigarsandwhiskey.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//private val DarkColorScheme = darkColorScheme(
//    surface = lushForestGreenDark,
////    primary = Purple80,
//    primary = earthForestDark,
////    secondary = PurpleGrey80,
//    secondary = earthForestMediumDark,
////    tertiary = Pink80,
//    tertiary = earthForestMedium, // special emphasis elements (such as buttons)
////    background = Color.Black,
////    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
////    onTertiary = Color.White,
//    onTertiary = Color.White, // text color for special emphasis elements (buttons)
////    onBackground = Color(0xFF1C1B1F),
//    onBackground = Color.Red, // unknown
////    onSurface = Color(0xFF1C1B1F),
//    onSurface = lushForestGrassLight, // text on non-special elements
//)

private val DarkColorScheme = darkColorScheme(
    background = lushForestGreenDark,
    surface = lushForestGreenDark,

    primary = earthForestDark,
    secondary = earthForestMediumDark,
    tertiary = earthForestMedium, // special emphasis elements (such as buttons)

    onPrimary = Color(0xFFECDFDF),
    onSecondary = Color(0xFFECDFDF),
    onTertiary = Color(0xFFECDFDF),

    onBackground = Color.Black,
    onSurface = Color.Black, // text on non-special elements
)


private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CigarsAndWhiskeyTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = true,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}