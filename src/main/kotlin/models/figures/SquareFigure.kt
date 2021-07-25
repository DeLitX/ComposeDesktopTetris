package models.figures

import Position
import androidx.compose.ui.graphics.Color

class SquareFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )
    ),
    cellSize,
    color,
) {
    override fun copy(color: Color): SquareFigure {
        return SquareFigure(color, cellSize)
    }
}