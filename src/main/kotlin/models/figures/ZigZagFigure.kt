package models.figures

import Position
import androidx.compose.ui.graphics.Color

class ZigZagFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            Position(0, 0),
            Position(1, 0),
            Position(1, 1),
            Position(2, 1),
        ),
        listOf(
            Position(1, 0),
            Position(1, 1),
            Position(0, 1),
            Position(0, 2),
        ),
    ),
    cellSize,
    color,
) {
    override fun copy(color: Color): ZigZagFigure {
        return ZigZagFigure(color, cellSize)
    }
}