package models.figures

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import drawBorder
import models.cells.RelativeCell

abstract class TetrisFigure(rotatePatterns:List<List<RelativeCell>>) : Figure() {
    protected var _rotateState = 0
    protected abstract val _rotateStatesCount: Int
    private val _rotatePatterns: List<List<RelativeCell>> =rotatePatterns
    override var pattern: List<RelativeCell> = _rotatePatterns[0]
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


    abstract fun widthOfState(rotateState: Int): Int
    abstract fun heightOfState(rotateState: Int): Int
}