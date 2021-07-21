package models.figures

import Position
import models.RelativeClass
import models.cells.RelativeCell

class RelativeFigure(
    relativePosition: Position,
    figure: Figure
) : RelativeClass<Figure>(relativePosition, figure) {
    override fun <L> addPosition(anotherClass: RelativeClass<L>): RelativeFigure {
        return RelativeFigure(
            position + anotherClass.position,
            item
        )

    }

    override fun addPosition(anotherPosition: Position): RelativeFigure {
        return RelativeFigure(
            anotherPosition + anotherPosition,
            item
        )
    }

    fun getAbsoluteCellsPosition(relativeCells:List<RelativeCell> =item.pattern): List<RelativeCell> {
        return relativeCells.map { it.addPosition(position) }
    }
}
