package models.figures

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.RelativeCell

abstract class Figure {
    abstract var pattern: List<RelativeCell>
        protected set
    abstract var currentWidth: Int
        protected set
    abstract var currentHeight: Int
        protected set
    abstract fun paint(topLeft: Offset, drawScope: DrawScope)
    abstract fun rotatePositions(): List<RelativeCell>
    abstract fun rotate()
}