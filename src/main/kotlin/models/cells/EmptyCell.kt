package models.cells

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import drawBorder

class EmptyCell(val cellSize: Float) : Cell() {
    override fun paint(topLeft: Offset, drawScope: DrawScope) {
        drawScope.drawRect(color = Color(0x00000000), topLeft = topLeft, size = Size(cellSize, cellSize))
        drawScope.drawBorder(topLeft,cellSize)
    }
}