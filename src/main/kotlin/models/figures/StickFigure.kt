package models.figures

import Position
import androidx.compose.ui.graphics.Color

class StickFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(0, 3),
        ),
        listOf(
             Position(0, 0),
             Position(1, 0),
             Position(2, 0),
             Position(3, 0),
        ),
    ),
    cellSize,
    color,
)