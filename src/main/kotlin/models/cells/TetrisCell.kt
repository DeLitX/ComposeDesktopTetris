package models.cells

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import drawBorder

class TetrisCell(private val _size: Float, private val _color: Color) : Cell() {

    override fun paint(topLeft: Offset, drawScope: DrawScope) {
        drawScope.drawRect(
            color = _color,
            topLeft = topLeft,
            size = Size(_size, _size)
        )
        drawScope.drawBorder(topLeft, _size)
    }
}