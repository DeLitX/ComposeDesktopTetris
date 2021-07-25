package models.figures

import Position
import androidx.compose.ui.graphics.Color

class LFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 2),
        ),
        listOf(
            Position(0, 2),
            Position(1, 2),
            Position(2, 2),
            Position(2, 1),
        ),
        listOf(
            Position(1, 0),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        ),
        listOf(
            Position(0, 1),
            Position(0, 0),
            Position(1, 0),
            Position(2, 0),
        ),
    ),
    cellSize,
    color,
) {
    override fun copy(color: Color): LFigure {
        return LFigure(color,cellSize)
    }
}