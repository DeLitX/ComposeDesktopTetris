package models.figures

import Position
import androidx.compose.ui.graphics.Color
import models.cells.RelativeCell
import models.cells.TetrisCell

class StickFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 2), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 3), TetrisCell(_color = color, _size = cellSize)),
        ),
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(2, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(3, 0), TetrisCell(_color = color, _size = cellSize)),
        ),
    )
) {
    override val _rotateStatesCount = 2
}