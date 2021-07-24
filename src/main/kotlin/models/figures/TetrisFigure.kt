package models.figures

import Position
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.RelativeCell
import models.cells.TetrisCell

abstract class TetrisFigure(rotatePatterns: List<List<RelativeCell>>) : Figure() {
    protected var _rotateState = 0
    protected val _rotateStatesCount: Int = rotatePatterns.size
    private val _rotatePatterns: List<List<RelativeCell>> = rotatePatterns
    override var pattern: List<RelativeCell> = _rotatePatterns[0]

    private val heightsMap = mutableMapOf<Int, Int>()
    private val widthsMap = mutableMapOf<Int, Int>()
    override var currentHeight: Int = heightOfState(0)
    override var currentWidth: Int = widthOfState(0)

    constructor(positions: List<List<Position>>, cellsSize: Float, color: Color) : this(List(positions.size) { t ->
        List(positions[t].size) { k ->
            RelativeCell(positions[t][k], TetrisCell(cellsSize, color))
        }
    })

    protected open fun getNextRotateState(): Int {
        return (_rotateState + 1) % _rotateStatesCount
    }

    override fun paint(topLeft: Offset, drawScope: DrawScope) {
        for (i in pattern) {
            val offset = Offset(topLeft.x + i.position.x, topLeft.y + i.position.y)
            i.item.paint(offset, drawScope)
        }
    }

    override fun rotatePositions(): List<RelativeCell> {
        return _rotatePatterns[getNextRotateState()]
    }

    override fun rotate() {
        _rotateState = getNextRotateState()
        pattern = _rotatePatterns[_rotateState]
        currentWidth = widthOfState(_rotateState)
        currentHeight = heightOfState(_rotateState)
    }


    fun widthOfState(rotateState: Int): Int {
        var width = widthsMap[rotateState]
        if (width == null) {
            width = calculateWidth(_rotatePatterns[rotateState])
            widthsMap[rotateState] = width
        }
        return width
    }

    fun heightOfState(rotateState: Int): Int {
        var height = heightsMap[rotateState]
        if (height == null) {
            height = calculateHeight(_rotatePatterns[rotateState])
            heightsMap[rotateState] = height
        }
        return height
    }

    private fun calculateHeight(cells: List<RelativeCell>): Int {
        return cells.maxOf { it.position.y } + 1
    }

    private fun calculateWidth(cells: List<RelativeCell>): Int {
        return cells.maxOf { it.position.x } + 1
    }
}