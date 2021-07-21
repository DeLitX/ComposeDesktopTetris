package models.figures

import Position
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.RelativeCell
import models.cells.TetrisCell

class TFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(2, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
        ),
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 2), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
        ),
        listOf(
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(2, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
        ),
        listOf(
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 2), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
        ),
    )
) {
    override var currentWidth: Int = 3
    override var currentHeight: Int = 2
    override val _rotateStatesCount: Int = 4

    override fun widthOfState(rotateState: Int): Int = when (rotateState) {
        0 -> {
            3
        }
        1 -> {
            2
        }
        2 -> {
            3
        }
        3 -> {
            2
        }
        else -> {
            throw IllegalArgumentException()
        }
    }

    override fun heightOfState(rotateState: Int): Int = when (rotateState) {
        0 -> {
            2
        }
        1 -> {
            3
        }
        2 -> {
            2
        }
        3 -> {
            3
        }
        else -> {
            throw IllegalArgumentException()
        }
    }


}