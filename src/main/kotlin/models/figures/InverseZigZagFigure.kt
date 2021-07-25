package models.figures

import Position
import androidx.compose.ui.graphics.Color

class InverseZigZagFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            Position(1, 0),
            Position(2, 0),
            Position(0, 1),
            Position(1, 1),
        ),
        listOf(
            Position(1, 1),
            Position(1, 2),
            Position(0, 0),
            Position(0, 1),
        ),
    ),
    cellSize,
    color,
) {
    override fun copy(color: Color): TetrisFigure {
        return InverseZigZagFigure(color, cellSize)
    }
}