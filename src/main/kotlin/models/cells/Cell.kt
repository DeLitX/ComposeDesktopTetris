package models.cells

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

abstract class Cell {
    abstract fun paint(topLeft: Offset,drawScope: DrawScope)
}