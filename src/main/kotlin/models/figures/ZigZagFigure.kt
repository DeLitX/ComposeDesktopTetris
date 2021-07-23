package models.figures

import Position
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.RelativeCell
import models.cells.TetrisCell

class ZigZagFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(2, 1), TetrisCell(_color = color, _size = cellSize)),
        ),
        listOf(
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 2), TetrisCell(_color = color, _size = cellSize)),
        ),
    )
) {
    override val _rotateStatesCount: Int = 2
}