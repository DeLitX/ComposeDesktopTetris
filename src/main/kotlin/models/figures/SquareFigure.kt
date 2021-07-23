package models.figures

import Position
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.RelativeCell
import models.cells.TetrisCell

class SquareFigure(
    val color: Color = Color.Red,
    val cellSize: Float,
) : TetrisFigure(
    listOf(
        listOf(
            RelativeCell(relativePosition = Position(0, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(0, 1), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 0), TetrisCell(_color = color, _size = cellSize)),
            RelativeCell(relativePosition = Position(1, 1), TetrisCell(_color = color, _size = cellSize)),
        )
    )
) {
    override var currentWidth: Int = widthOfState(0)
    override var currentHeight: Int = heightOfState(0)
    override val _rotateStatesCount: Int = 1

    override fun widthOfState(rotateState: Int): Int = 2
    override fun heightOfState(rotateState: Int): Int = 2

}