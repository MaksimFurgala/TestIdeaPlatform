package com.example.testideaplatform.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MinusIcon: ImageVector
    get() {
        if (_MinusIcon != null) {
            return _MinusIcon!!
        }
        _MinusIcon = ImageVector.Builder(
            name = "MinusSvgrepoCom",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6f, 12f)
                lineTo(18f, 12f)
            }
        }.build()

        return _MinusIcon!!
    }

@Suppress("ObjectPropertyName")
private var _MinusIcon: ImageVector? = null
//public val Icons.Sharp.MinusCircle: ImageVector
//    get() {
//        if (_addCircle != null) {
//            return _addCircle!!
//        }
//        _addCircle = materialIcon(name = "Sharp.MinusCircle") {
//            materialPath {
////                moveTo(3.0f, 18.0f)
////                horizontalLineToRelative(18.0f)
////                verticalLineToRelative(-2.0f)
////                lineTo(3.0f, 16.0f)
////                verticalLineToRelative(2.0f)
////                close()
//                moveTo(3.0f, 13.0f)
//                horizontalLineToRelative(16.0f)
//                verticalLineToRelative(-2.0f)
//                lineTo(6.0f, 11.0f)
//                verticalLineToRelative(2.0f)
//                close()
////                moveTo(3.0f, 6.0f)
////                verticalLineToRelative(2.0f)
////                horizontalLineToRelative(18.0f)
////                lineTo(21.0f, 6.0f)
////                lineTo(3.0f, 6.0f)
////                close()
//            }
//        }
//        return _addCircle!!
//    }
//
//private var _addCircle: ImageVector? = null