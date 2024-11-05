package com.easyapps.jetutilslib.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import com.easyapps.jetutilslib.utils.*

fun getTypography(isCompact: Boolean, isExtended: Boolean): Typography {
    return when {
        isCompact -> getCompactTypography()
        isExtended -> getExtendedTypography()
        else -> getMediumTypography()
    }
}

private fun getCompactTypography(): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            fontFamily = ubuntuFontFamily,
            fontWeight = FontWeight.Normal,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        ),
        labelSmall = TextStyle(
            fontFamily = ubuntuFontFamily,
            lineHeight = 12.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Light,
            fontSize = 11.sp,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        ),
        titleLarge = TextStyle(
            fontFamily = ubuntuFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        )
    )
}

private fun getMediumTypography(): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontSize = 18.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.5.sp,
            fontFamily = ubuntuFontFamily,
            fontWeight = FontWeight.Normal,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        ),
        labelSmall = TextStyle(
            fontFamily = ubuntuFontFamily,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Light,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        ),
        titleLarge = TextStyle(
            fontFamily = ubuntuFontFamily,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight.Medium,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph
        )
    )
}

private fun getExtendedTypography(): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Normal,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph,
            fontFamily = ubuntuFontFamily
        ),
        titleLarge = TextStyle(
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight.Medium,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph,
            fontFamily = ubuntuFontFamily
        ),
        labelSmall = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Light,
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph,
            fontFamily = ubuntuFontFamily
        )
    )
}

