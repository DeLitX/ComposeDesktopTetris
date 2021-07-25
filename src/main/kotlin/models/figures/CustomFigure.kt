package models.figures

import Position
import androidx.compose.ui.graphics.Color

class CustomFigure(
    val color: Color,
    val cellSize: Float,
    val positions: List<List<Position>>
) : TetrisFigure(positions, cellSize, color) {
    override fun copy(color: Color): CustomFigure {
        return CustomFigure(color, cellSize, positions)
    }
}